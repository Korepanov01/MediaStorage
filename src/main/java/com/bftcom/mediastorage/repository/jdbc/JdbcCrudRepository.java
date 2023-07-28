package com.bftcom.mediastorage.repository.jdbc;

import com.bftcom.mediastorage.model.entity.BaseEntity;
import com.bftcom.mediastorage.repository.CrudRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Repository
public abstract class JdbcCrudRepository<Entity extends BaseEntity> implements CrudRepository<Entity> {

    protected JdbcTemplate jdbcTemplate;

    private List<String> fields;

    @Getter(AccessLevel.PROTECTED)
    private String sqlSelectFrom;

    private String sqlSave;
    private String sqlUpdate;
    private String sqlDelete;

    private JdbcCrudRepository() {}

    public JdbcCrudRepository(
            @NonNull String tableName,
            @NonNull String idFiled,
            @NonNull List<String> otherFields) {
        this.fields = new ArrayList<>();
        this.fields.add(tableName + ".id");
        this.fields.addAll(
                otherFields.stream()
                        .map(field -> tableName + "." + field)
                        .collect(Collectors.toList()));

        this.sqlSelectFrom = String.format(
                "SELECT %s FROM %s",
                String.join(", ", fields),
                tableName);

        this.sqlSave = String.format(
                "INSERT INTO %s (%s) VALUES(%s)",
                tableName,
                String.join(", ", otherFields),
                String.join(", ", Collections.nCopies(otherFields.size(), "?")));

        this.sqlUpdate = String.format(
                "UPDATE %s SET %s WHERE %s = ?",
                tableName,
                otherFields.stream().map(field -> field + " = ?").collect(Collectors.joining(", ")),
                idFiled);

        this.sqlDelete = String.format(
                "DELETE FROM %s WHERE %s = ?",
                tableName,
                idFiled);
    }

    @Autowired
    private void setJdbcTemplate(@NonNull JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    protected List<Entity> findByField(@NonNull String fieldName, @NonNull Object field) {
        String sql = this.sqlSelectFrom + " WHERE " + fieldName + " = ?";

        return jdbcTemplate.query(
                sql,
                this::mapRowToModel,
                field);
    }

    protected Optional<Entity> findByUniqueField(@NonNull String fieldName, @NonNull Object field) {
        List<Entity> results = findByField(fieldName, field);

        return results.isEmpty() ?
                Optional.empty() :
                Optional.of(results.get(0));
    }

    @Override
    public Optional<Entity> findById(@NonNull Long id) {
        return findByUniqueField(fields.get(0), id);
    }

    @Override
    public boolean isExists(@NonNull Long id) {
        return findById(id).isEmpty();
    }

    @Override
    public Entity save(@NonNull @Valid Entity entity) {
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
    public List<Entity> saveAll(@NonNull List<Entity> entities) {
        entities.forEach(this::save);
        return entities;
    }

    @Override
    public void update(@NonNull Entity entity) {
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sqlUpdate);
            setPreparedUpdateStatementValues(preparedStatement, entity);
            return preparedStatement;
        });
    }

    @Override
    public void delete(@NonNull Entity entity) {
        jdbcTemplate.update(
                sqlDelete,
                entity.getId());
    }

    protected abstract Entity mapRowToModel(@NonNull ResultSet row, int rowNum)
            throws SQLException;

    protected abstract void setPreparedSaveStatementValues(@NonNull PreparedStatement preparedStatement, @NonNull Entity model)
            throws SQLException;

    protected abstract void setPreparedUpdateStatementValues(@NonNull PreparedStatement preparedStatement, @NonNull Entity entity)
            throws SQLException;

    protected class ParametersSearcher {

        private final StringBuilder sqlBuilder;

        private final List<Object> queryParams = new ArrayList<>();

        public ParametersSearcher() {
            this(null);
        }

        public ParametersSearcher(String join) {
            sqlBuilder = new StringBuilder(sqlSelectFrom);
            if (join != null) {
                sqlBuilder.append(" JOIN ").append(join);
            }
            sqlBuilder.append(" WHERE 1=1");
        }

        private void addStatement(@NonNull String statement, @NonNull Object... params) {
            sqlBuilder.append(" ").append(statement);
            queryParams.addAll(Arrays.asList(params));
        }

        public void addCondition(@NonNull String condition,@NonNull Object... params) {
            addStatement("AND " + condition, params);
        }

        public void addEqualsCondition(@NonNull String fieldName,@NonNull Object param) {
            addCondition(fieldName + " = ?", param);
        }

        public void addSearchStringCondition(@NonNull String fieldName, String searchString) {
            if (StringUtils.hasText(searchString)) {
                addCondition("LOWER(" + fieldName + ") LIKE LOWER(?)", "%" + searchString + "%");
            }
        }

        private void addPagination(int pageIndex, int pageSize) {
            int offset = pageIndex * pageSize;
            addStatement("OFFSET ? LIMIT ?", offset, pageSize);
        }

        public List<Entity> findByParameters(RowMapper<Entity> rowMapper) {
            String sql = sqlBuilder.toString();

            log.debug("Сгенерирован запрос: " + sql);

            return jdbcTemplate.query(sql, rowMapper, queryParams.toArray());
        }

        public List<Entity> findByParameters(int pageIndex, int pageSize, RowMapper<Entity> rowMapper) {
            addPagination(pageIndex, pageSize);

            return findByParameters(rowMapper);
        }
    }
}
