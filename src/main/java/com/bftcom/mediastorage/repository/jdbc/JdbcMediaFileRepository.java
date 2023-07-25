package com.bftcom.mediastorage.repository.jdbc;

import com.bftcom.mediastorage.repository.MediaFileRepository;
import com.bftcom.mediastorage.model.entity.MediaFile;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcMediaFileRepository extends JdbcCrudRepository<MediaFile> implements MediaFileRepository {

    private static final String SQL_FIND_BY_ID =
            "SELECT id, media_id, file_id, file_type_id " +
                    "FROM \"public.media_file\" " +
                    "WHERE id = ?";

    private static final String SQL_FIND_ALL =
            "SELECT id, media_id, file_id, file_type_id " +
                    "FROM \"public.media_file\"";

    private static final String SQL_SAVE =
            "INSERT INTO \"public.media_file\"(media_id, file_id, file_type_id) VALUES(?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE \"public.media_file\" " +
                    "SET media_id = ?, file_id = ?, file_type_id = ? " +
                    "WHERE id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM \"public.media_file\" " +
                    "WHERE id = ?";

    public JdbcMediaFileRepository() {
        super(SQL_FIND_BY_ID, SQL_FIND_ALL, SQL_SAVE, SQL_UPDATE, SQL_DELETE);
    }

    @Override
    protected MediaFile mapRowToModel(ResultSet row, int rowNum) throws SQLException {
        return new MediaFile(
                row.getLong("id"),
                row.getLong("media_id"),
                row.getLong("file_id"),
                row.getLong("file_type_id"));
    }

    @Override
    protected void setPreparedSaveStatementValues(PreparedStatement preparedStatement, MediaFile mediaFile)
            throws SQLException {
        preparedStatement.setLong(1, mediaFile.getMediaId());
        preparedStatement.setLong(2, mediaFile.getFileId());
        if (mediaFile.getFileTypeId() != null) {
            preparedStatement.setLong(3, mediaFile.getFileTypeId());
        } else {
            preparedStatement.setNull(3, java.sql.Types.BIGINT);
        }
    }

    @Override
    protected void setPreparedUpdateStatementValues(PreparedStatement preparedStatement, MediaFile mediaFile)
            throws SQLException {
        preparedStatement.setLong(1, mediaFile.getMediaId());
        preparedStatement.setLong(2, mediaFile.getFileId());
        if (mediaFile.getFileTypeId() != null) {
            preparedStatement.setLong(3, mediaFile.getFileTypeId());
        } else {
            preparedStatement.setNull(3, java.sql.Types.BIGINT);
        }
        preparedStatement.setLong(4, mediaFile.getId());
    }
}