package com.bftcom.mediastorage.data.repository;

import com.bftcom.mediastorage.data.model.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 JdbcRepository is an abstract class that serves as a base implementation for data access using JDBC.
 Provides common CRUD operations for a specific model extending the BaseModel class.

 @param <T> The type of model that extends the BaseModel class.

 @see com.bftcom.mediastorage.data.model.BaseModel
 @see IRepository
 */
@Repository
public abstract class JdbcRepository<T extends BaseModel> implements IRepository<T, Long> {

    private JdbcTemplate jdbcTemplate;

    private String sqlFindById;
    private String sqlFindAll;
    private String sqlSave;
    private String sqlUpdate;
    private String sqlDelete;

    private JdbcRepository() {}

    /**
     Constructor for JdbcRepository that initializes SQL queries.
     @param sqlFindById The SQL query to retrieve a specific model by its ID.
     @param sqlFindAll The SQL query to retrieve all models of a specific type.
     @param sqlSave The SQL query to save a new model into the database.
     @param sqlUpdate The SQL query to update an existing model in the database.
     @param sqlDelete The SQL query to delete a model from the database.
     */
    public JdbcRepository(
            String sqlFindById,
            String sqlFindAll,
            String sqlSave,
            String sqlUpdate,
            String sqlDelete) {
        this.sqlFindById = sqlFindById;
        this.sqlFindAll = sqlFindAll;
        this.sqlSave = sqlSave;
        this.sqlUpdate = sqlUpdate;
        this.sqlDelete = sqlDelete;
    }

    @Autowired
    private void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     Retrieves a model by its ID from the database.
     @param id The ID of the model to retrieve.
     @return An Optional containing the retrieved model, or empty if not found.
     */
    @Override
    public Optional<T> findById(Long id) {
        List<T> results = jdbcTemplate.query(
                sqlFindById,
                this::mapRowToModel,
                id);
        return results.size() == 0 ?
                Optional.empty() :
                Optional.of(results.get(0));
    }

    /**
     Retrieves all models of a specific type from the database.
     @return A list of all models of the specified type.
     */
    @Override
    public List<T> findAll() {
        return jdbcTemplate.query(
                sqlFindAll,
                this::mapRowToModel);
    }

    /**
     Saves a new model into the database.
     @param model The model to be saved.
     @return The saved model with its updated ID.
     */
    @Override
    public T save(T model) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sqlSave,
                    Statement.RETURN_GENERATED_KEYS);
            setPreparedSaveStatementValues(preparedStatement, model);
            return preparedStatement;
        }, generatedKeyHolder);


        long id = (long) Objects.requireNonNull(generatedKeyHolder.getKeys()).get("id");

        model.setId(id);

        return model;
    }

    /**
     Updates an existing model in the database.
     @param model The model to be updated.
     */
    @Override
    public void update(T model) {
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sqlUpdate);
            setPreparedUpdateStatementValues(preparedStatement, model);
            return preparedStatement;
        });
    }

    /**

     Deletes a model from the database.
     @param model The model to be deleted.
     */
    @Override
    public void delete(T model) {
        jdbcTemplate.update(
                sqlDelete,
                model.getId());
    }

    /**

     Abstract method to map a ResultSet row to a model object.
     @param row The ResultSet row to be mapped.
     @param rowNum The row number within the ResultSet.
     @return The mapped model object.
     @throws SQLException If a database access error occurs or other errors.
     */
    protected abstract T mapRowToModel(ResultSet row, int rowNum)
            throws SQLException;

    /**
     Abstract method to set prepared statement values for saving a model.
     @param preparedStatement The PreparedStatement to set values on.
     @param model The model to be saved.
     @throws SQLException If a database access error occurs or other errors.
     */
    protected abstract void setPreparedSaveStatementValues(PreparedStatement preparedStatement, T model)
            throws SQLException;

    /**
     Abstract method to set prepared statement values for updating a model.
     @param preparedStatement The PreparedStatement to set values on.
     @param model The model to be updated.
     @throws SQLException If a database access error occurs or other errors.
     */
    protected abstract void setPreparedUpdateStatementValues(PreparedStatement preparedStatement, T model)
            throws SQLException;
}
