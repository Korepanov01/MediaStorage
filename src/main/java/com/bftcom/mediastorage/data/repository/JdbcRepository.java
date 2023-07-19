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

@Repository
public abstract class JdbcRepository<T extends BaseModel> implements IRepository<T, Long> {

    private JdbcTemplate jdbcTemplate;

    private String sqlFindById;
    private String sqlFindAll;
    private String sqlSave;
    private String sqlUpdate;
    private String sqlDelete;

    public JdbcRepository() {}

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
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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

    @Override
    public List<T> findAll() {
        return jdbcTemplate.query(
                sqlFindAll,
                this::mapRowToModel);
    }

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

    @Override
    public void update(T model) {
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sqlUpdate);
            setPreparedUpdateStatementValues(preparedStatement, model);
            return preparedStatement;
        });
    }

    @Override
    public void delete(T model) {
        jdbcTemplate.update(
                sqlDelete,
                model.getId());
    }

    protected abstract T mapRowToModel(ResultSet row, int rowNum)
            throws SQLException;

    protected abstract void setPreparedSaveStatementValues(PreparedStatement preparedStatement, T model)
            throws SQLException;

    protected abstract void setPreparedUpdateStatementValues(PreparedStatement preparedStatement, T model)
            throws SQLException;
}
