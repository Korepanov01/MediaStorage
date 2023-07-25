package com.bftcom.mediastorage.repository.jdbc;

import com.bftcom.mediastorage.model.entity.MediaFile;
import com.bftcom.mediastorage.repository.MediaFileRepository;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcMediaFileRepository extends JdbcCrudRepository<MediaFile> implements MediaFileRepository {

    private static final String TABLE_NAME = "\"public.media_file\"";
    private static final String ID_FIELD = "id";
    private static final String[] OTHER_FIELDS = {"media_id", "file_id", "file_type_id"};

    public JdbcMediaFileRepository() {
        super(TABLE_NAME, ID_FIELD, List.of(OTHER_FIELDS));
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