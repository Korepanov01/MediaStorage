package com.bftcom.mediastorage.data.repository;

import com.bftcom.mediastorage.data.entity.Media;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Repository
public class JdbcMediaRepository extends JdbcCrudRepository<Media, Long> {

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
}