package com.bftcom.mediastorage.data.repository;

import com.bftcom.mediastorage.data.entity.MediaTag;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcMediaTagRepository extends JdbcRepository<MediaTag> {

    private static final String SQL_FIND_BY_ID =
            "SELECT id, media_id, tag_id " +
            "FROM \"public.media_tag\" " +
            "WHERE id=?";

    private static final String SQL_FIND_ALL =
            "SELECT id, media_id, tag_id " +
            "FROM \"public.media_tag\"";

    private static final String SQL_SAVE =
            "INSERT INTO \"public.media_tag\"(media_id, tag_id) VALUES(?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE \"public.media_tag\" " +
            "SET media_id = ?, tag_id = ? " +
            "WHERE id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM \"public.media_tag\" " +
            "WHERE id = ?";

    public JdbcMediaTagRepository() {
        super(SQL_FIND_BY_ID, SQL_FIND_ALL, SQL_SAVE, SQL_UPDATE, SQL_DELETE);
    }

    @Override
    protected MediaTag mapRowToModel(ResultSet row, int rowNum) throws SQLException {
        return new MediaTag(
                row.getLong("id"),
                row.getLong("media_id"),
                row.getLong("tag_id"));
    }

    @Override
    protected void setPreparedSaveStatementValues(PreparedStatement preparedStatement, MediaTag mediaTag)
            throws SQLException {
        preparedStatement.setLong(1, mediaTag.getMediaId());
        preparedStatement.setLong(2, mediaTag.getTagId());
    }

    @Override
    protected void setPreparedUpdateStatementValues(PreparedStatement preparedStatement, MediaTag mediaTag)
            throws SQLException {
        preparedStatement.setLong(1, mediaTag.getMediaId());
        preparedStatement.setLong(2, mediaTag.getTagId());
        preparedStatement.setLong(3, mediaTag.getId());
    }
}