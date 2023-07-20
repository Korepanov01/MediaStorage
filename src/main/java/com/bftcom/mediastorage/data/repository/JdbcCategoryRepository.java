package com.bftcom.mediastorage.data.repository;

import com.bftcom.mediastorage.data.entity.Category;
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
public class JdbcCategoryRepository implements IRepository<Category, Long> {

    private final JdbcTemplate jdbcTemplate;

    private final String SQL_FIND_BY_ID =
            "SELECT id, name, parent_category_id " +
            "FROM \"public.category\" " +
            "WHERE id=?";

    private final String SQL_FIND_ALL =
            "SELECT id, name, parent_category_id " +
            "FROM \"public.category\"";

    private final String SQL_SAVE =
            "INSERT INTO \"public.category\"(name, parent_category_id) VALUES(?, ?)";

    private final String SQL_UPDATE =
            "UPDATE \"public.category\" " +
            "SET name = ?, parent_category_id = ? " +
            "WHERE id = ?";

    private final String SQL_DELETE =
            "DELETE FROM \"public.category\" " +
            "WHERE id = ?";

    @Autowired
    public JdbcCategoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Category> findById(Long id) {
        List<Category> results = jdbcTemplate.query(
                SQL_FIND_BY_ID,
                this::mapRowToCategory,
                id);
        return results.size() == 0 ?
                Optional.empty() :
                Optional.of(results.get(0));
    }

    @Override
    public List<Category> findAll() {
        return jdbcTemplate.query(
                SQL_FIND_ALL,
                this::mapRowToCategory);
    }

    @Override
    public Category save(Category category) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    SQL_SAVE,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setLong(2, category.getParentCategoryId());
            return preparedStatement;
        }, generatedKeyHolder);

        long id = (long) Objects.requireNonNull(generatedKeyHolder.getKeys()).get("id");
        category.setId(id);

        return category;
    }

    @Override
    public void update(Category category) {
        jdbcTemplate.update(
                SQL_UPDATE,
                category.getName(),
                category.getParentCategoryId(),
                category.getId());
    }

    @Override
    public void delete(Category category) {
        jdbcTemplate.update(
                SQL_DELETE,
                category.getId());
    }

    private Category mapRowToCategory(ResultSet row, int rowNum)
            throws SQLException {
        return new Category(
                row.getLong("id"),
                row.getString("name"),
                row.getLong("parent_category_id"));
    }
}