package com.bftcom.mediastorage.repository.jdbc;

import com.bftcom.mediastorage.model.entity.BaseEntity;
import com.bftcom.mediastorage.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@Repository
public abstract class JdbcCrudRepository<T extends BaseEntity> implements CrudRepository<T> {

    protected JdbcTemplate jdbcTemplate;

    private String sqlFindById;
    private String sqlSave;
    private String sqlUpdate;
    private String sqlDelete;

    private JdbcCrudRepository() {}

    public JdbcCrudRepository(
            String sqlFindById,
            String sqlSave,
            String sqlUpdate,
            String sqlDelete) {
        this.sqlFindById = sqlFindById;
        this.sqlSave = sqlSave;
        this.sqlUpdate = sqlUpdate;
        this.sqlDelete = sqlDelete;
    }

    @Autowired
    private void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
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

    @Override
    public void update(T entity) {
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sqlUpdate);
            setPreparedUpdateStatementValues(preparedStatement, entity);
            return preparedStatement;
        });
    }

    @Override
    public void delete(T entity) {
        jdbcTemplate.update(
                sqlDelete,
                entity.getId());
    }

    protected abstract T mapRowToModel(ResultSet row, int rowNum)
            throws SQLException;

    protected abstract void setPreparedSaveStatementValues(PreparedStatement preparedStatement, T model)
            throws SQLException;

    protected abstract void setPreparedUpdateStatementValues(PreparedStatement preparedStatement, T entity)
            throws SQLException;

    protected static class ParametersSearchSqlBuilder {

        private boolean paginated = false;

        private final StringBuilder sqlBuilder;

        private final List<Object> queryParams = new ArrayList<>();

        public ParametersSearchSqlBuilder(String selectParams, String tableName) {
            sqlBuilder = new StringBuilder()
                    .append(" SELECT ")
                    .append(selectParams)
                    .append(" FROM ")
                    .append(tableName)
                    .append(" WHERE 1=1 ");
        }

        public void addStatement(String statement, Object... params) {
            sqlBuilder.append(statement);
            queryParams.addAll(Arrays.asList(params));
        }

        public void addCondition(String condition, Object... params) {
            if (paginated) {
                throw new RuntimeException("Нельзя добавлять условия после добавления страниц!");
            }

            addStatement(" AND " + condition, params);
        }

        public void addSearchStringCondition(String fieldName, String searchString) {
            if (StringUtils.hasText(searchString)) {
                addCondition(" LOWER(" + fieldName + ") LIKE LOWER(?) ", "%" + searchString + "%");
            }
        }

        public void addPagination(int pageIndex, int pageSize) {
            int offset = pageIndex * pageSize;
            addStatement(" OFFSET ? LIMIT ? ", offset, pageSize);
            paginated = true;
        }

        public String getQuery() {
            System.out.println(sqlBuilder.toString());
            return sqlBuilder.toString();
        }

        public Object[] getQueryParams() {
            return queryParams.toArray();
        }
    }
}
