package com.bftcom.mediastorage.repository.jdbc;

import com.bftcom.mediastorage.model.entity.Category;
import com.bftcom.mediastorage.model.parameters.CategorySearchParameters;
import com.bftcom.mediastorage.repository.CategoryRepository;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcCategoryRepository extends JdbcCrudRepository<Category> implements CategoryRepository {

    private static final String TABLE_NAME = "\"public.category\"";
    private static final String ID_FIELD = "id";
    private static final String[] OTHER_FIELDS = {"name", "parent_category_id"};

    public JdbcCategoryRepository() {
        super(TABLE_NAME, ID_FIELD, List.of(OTHER_FIELDS));
    }

    @Override
    protected Category mapRowToModel(@NonNull ResultSet row, int rowNum) throws SQLException {
        return new Category(
                row.getLong("id"),
                row.getString("name"),
                row.getLong("parent_category_id"));
    }

    @Override
    protected void setPreparedSaveStatementValues(@NonNull PreparedStatement preparedStatement, @NonNull Category category)
            throws SQLException {
        preparedStatement.setString(1, category.getName());
        if (category.getParentCategoryId() != null) {
            preparedStatement.setLong(2, category.getParentCategoryId());
        } else {
            preparedStatement.setNull(2, java.sql.Types.BIGINT);
        }
    }

    @Override
    protected void setPreparedUpdateStatementValues(@NonNull PreparedStatement preparedStatement, @NonNull Category category)
            throws SQLException {
        preparedStatement.setString(1, category.getName());
        if (category.getParentCategoryId() != null) {
            preparedStatement.setLong(2, category.getParentCategoryId());
        } else {
            preparedStatement.setNull(2, java.sql.Types.BIGINT);
        }
        preparedStatement.setLong(3, category.getId());
    }

    @Override
    public Optional<Category> findByName(@NonNull String name) {
        return findByUniqueField("name", name);
    }

    @Override
    public List<Category> findByParameters(@NonNull CategorySearchParameters parameters) {
        ParametersSearchSqlBuilder builder = this.new ParametersSearchSqlBuilder();

        builder.addSearchStringCondition("name", parameters.getSearchString());

        if (parameters.getParentCategoryId() != null) {
            builder.addCondition("parent_category_id = ?", parameters.getParentCategoryId());
        }

        builder.addPagination(parameters.getPageIndex(), parameters.getPageSize());

        return jdbcTemplate.query(builder.getQuery(), this::mapRowToModel, builder.getQueryParams());
    }
}