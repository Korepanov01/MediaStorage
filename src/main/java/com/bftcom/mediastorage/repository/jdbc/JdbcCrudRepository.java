package com.bftcom.mediastorage.repository.jdbc;

import com.bftcom.mediastorage.model.entity.BaseEntity;
import com.bftcom.mediastorage.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 JdbcRepository is an abstract class that serves as a base implementation for data access using JDBC.
 Provides common CRUD operations for a specific model extending the BaseModel class.

 @param <T> The type of model that extends the BaseModel class.

 @see BaseEntity
 @see CrudRepository
 */
@Repository
public abstract class JdbcCrudRepository<T extends BaseEntity> implements CrudRepository<T> {

    protected JdbcTemplate jdbcTemplate;

    private String sqlFindById;
    private String sqlFindAll;
    private String sqlSave;
    private String sqlUpdate;
    private String sqlDelete;

    private JdbcCrudRepository() {}

    /**
     Constructor for JdbcRepository that initializes SQL queries.
     @param sqlFindById The SQL query to retrieve a specific model by its ID.
     @param sqlFindAll The SQL query to retrieve all models of a specific type.
     @param sqlSave The SQL query to save a new model into the database.
     @param sqlUpdate The SQL query to update an existing model in the database.
     @param sqlDelete The SQL query to delete a model from the database.
     */
    public JdbcCrudRepository(
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
     Saves a new entity into the database.
     @param entity The entity to be saved.
     @return The saved entity with its updated ID.
     */
    @Override
    public T save(T entity) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sqlSave,
                    Statement.RETURN_GENERATED_KEYS);
            setPreparedSaveStatementValues(preparedStatement, entity);
            return preparedStatement;
        }, generatedKeyHolder);


        long id = (long) Objects.requireNonNull(generatedKeyHolder.getKeys()).get("id");

        entity.setId(id);

        return entity;
    }

    /**
     Updates an existing entity in the database.
     @param entity The entity to be updated.
     */
    @Override
    public void update(T entity) {
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sqlUpdate);
            setPreparedUpdateStatementValues(preparedStatement, entity);
            return preparedStatement;
        });
    }

    /**
     Deletes a entity from the database.
     @param entity The entity to be deleted.
     */
    @Override
    public void delete(T entity) {
        jdbcTemplate.update(
                sqlDelete,
                entity.getId());
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
     Abstract method to set prepared statement values for updating a entity.
     @param preparedStatement The PreparedStatement to set values on.
     @param entity The entity to be updated.
     @throws SQLException If a database access error occurs or other errors.
     */
    protected abstract void setPreparedUpdateStatementValues(PreparedStatement preparedStatement, T entity)
            throws SQLException;

    protected class WhereConstructor {
        private List<String> whereParams = new ArrayList<>();

        public void addParam(String param) {
            whereParams.add(param);
        }

        public String getWhere() {
            if (whereParams.isEmpty()) {
                return "";
            }

            return "WHERE" + String.join(" AND ", whereParams) + "\n";
        }
    }
}
