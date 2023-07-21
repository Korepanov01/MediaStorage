package com.bftcom.mediastorage.repository.jdbc;

import com.bftcom.mediastorage.model.entity.Category;
import com.bftcom.mediastorage.model.entity.Media;
import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.repository.MediaRepository;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcMediaRepository extends JdbcCrudRepository<Media, Long> implements MediaRepository {

    private static final String SQL_FIND_BY_ID =
            "SELECT id, user_id, category_id, name, description, media_type_id, created_at, edited_at " +
                    "FROM \"public.media\" " +
                    "WHERE id=?";

    private static final String SQL_FIND_ALL =
            "SELECT id, user_id, category_id, name, description, media_type_id, created_at, edited_at " +
                    "FROM \"public.media\"";

    private static final String SQL_SAVE =
            "INSERT INTO \"public.media\"(user_id, category_id, name, description, media_type_id, created_at, edited_at) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE \"public.media\" " +
                    "SET user_id = ?, category_id = ?, name = ?, description = ?, media_type_id = ?, edited_at = ? " +
                    "WHERE id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM \"public.media\" " +
                    "WHERE id = ?";

    private static final String SQL_FIND_BY_TAG =
            "SELECT m.id, m.user_id, m.category_id, m.name, m.description, m.media_type_id, m.created_at, m.edited_at " +
                    "FROM \"public.media\" m " +
                    "INNER JOIN \"public.media_tag\" mt ON m.id = mt.media_id " +
                    "INNER JOIN \"public.tag\" t ON mt.tag_id = t.id " +
                    "WHERE t.id = ?";

    private static final String SQL_FIND_BY_CATEGORY =
            "SELECT id, user_id, category_id, name, description, media_type_id, created_at, edited_at " +
                    "FROM \"public.media\" " +
                    "WHERE category_id = ?";

    private static final String SQL_FIND_BY_NAME =
            "SELECT id, user_id, category_id, name, description, media_type_id, created_at, edited_at " +
                    "FROM \"public.media\" " +
                    "WHERE LOWER(name) LIKE LOWER(?)";

    private static final String SQL_FIND_RANDOM =
            "SELECT id, user_id, category_id, name, description, media_type_id, created_at, edited_at " +
                    "FROM \"public.media\" " +
                    "ORDER BY RANDOM()\n" +
                    "LIMIT ?;";

    public JdbcMediaRepository() {
        super(SQL_FIND_BY_ID, SQL_FIND_ALL, SQL_SAVE, SQL_UPDATE, SQL_DELETE);
    }

    @Override
    protected Media mapRowToModel(ResultSet row, int rowNum) throws SQLException {
        return new Media(
                row.getLong("id"),
                row.getLong("user_id"),
                row.getLong("category_id"),
                row.getString("name"),
                row.getString("description"),
                row.getLong("media_type_id"),
                row.getObject("created_at", LocalDateTime.class),
                row.getObject("edited_at", LocalDateTime.class));
    }

    @Override
    protected void setPreparedSaveStatementValues(PreparedStatement preparedStatement, Media media)
            throws SQLException {
        preparedStatement.setLong(1, media.getUserId());
        preparedStatement.setLong(2, media.getCategoryId());
        preparedStatement.setString(3, media.getName());
        preparedStatement.setString(4, media.getDescription());
        preparedStatement.setLong(5, media.getMediaTypeId());
        preparedStatement.setObject(6, media.getCreatedAt());
        preparedStatement.setObject(7, media.getEditedAt());
    }

    @Override
    protected void setPreparedUpdateStatementValues(PreparedStatement preparedStatement, Media entity)
            throws SQLException {
        preparedStatement.setLong(1, entity.getUserId());
        preparedStatement.setLong(2, entity.getCategoryId());
        preparedStatement.setString(3, entity.getName());
        preparedStatement.setString(4, entity.getDescription());
        preparedStatement.setLong(5, entity.getMediaTypeId());
        preparedStatement.setObject(6, entity.getEditedAt());
        preparedStatement.setLong(7, entity.getId());
    }

    @Override
    public List<Media> findByTag(Tag tag) {
        return jdbcTemplate.query(
                SQL_FIND_BY_TAG,
                this::mapRowToModel,
                tag.getId());
    }

    @Override
    public List<Media> findByCategory(Category category) {
        return jdbcTemplate.query(
                SQL_FIND_BY_CATEGORY,
                this::mapRowToModel,
                category.getId());
    }

    @Override
    public List<Media> findByName(String name) {
        return jdbcTemplate.query(
                SQL_FIND_BY_NAME,
                this::mapRowToModel,
                "%" + name + "%");
    }

    @Override
    public List<Media> findRandom(int maxCount) {
        return jdbcTemplate.query(
                SQL_FIND_RANDOM,
                this::mapRowToModel,
                maxCount);
    }
}