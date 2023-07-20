package com.bftcom.mediastorage.data.repository.jdbc;

import com.bftcom.mediastorage.data.entity.Category;
import com.bftcom.mediastorage.data.repository.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcCategoryRepository extends JdbcCrudRepository<Category, Long> implements CategoryRepository {

    private static final String SQL_FIND_BY_ID =
            "SELECT id, name, parent_category_id " +
                    "FROM \"public.category\" " +
                    "WHERE id = ?";

    private static final String SQL_FIND_ALL =
            "SELECT id, name, parent_category_id " +
                    "FROM \"public.category\"";

    private static final String SQL_SAVE =
            "INSERT INTO \"public.category\"(name, parent_category_id) VALUES(?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE \"public.category\" " +
                    "SET name = ?, parent_category_id = ? " +
                    "WHERE id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM \"public.category\" " +
                    "WHERE id = ?";

    public JdbcCategoryRepository() {
        super(SQL_FIND_BY_ID, SQL_FIND_ALL, SQL_SAVE, SQL_UPDATE, SQL_DELETE);
    }

    @Override
    protected Category mapRowToModel(ResultSet row, int rowNum) throws SQLException {
        return new Category(
                row.getLong("id"),
                row.getString("name"),
                row.getLong("parent_category_id"));
    }

    @Override
    protected void setPreparedSaveStatementValues(PreparedStatement preparedStatement, Category category)
            throws SQLException {
        preparedStatement.setString(1, category.getName());
        preparedStatement.setLong(2, category.getParentCategoryId());
    }

    @Override
    protected void setPreparedUpdateStatementValues(PreparedStatement preparedStatement, Category entity)
            throws SQLException {
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setLong(2, entity.getParentCategoryId());
        preparedStatement.setLong(3, entity.getId());
    }
}