package com.bftcom.mediastorage.repository.jdbc;

import com.bftcom.mediastorage.model.entity.MediaFile;
import com.bftcom.mediastorage.model.searchparameters.MediaFilesSearchParameters;
import com.bftcom.mediastorage.repository.MediaFileRepository;
import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    protected MediaFile mapRowToModel(@NonNull ResultSet row, int rowNum) throws SQLException {
        return new MediaFile(
                row.getLong("id"),
                row.getLong("media_id"),
                row.getLong("file_id"),
                row.getLong("file_type_id"));
    }

    @Override
    protected void setPreparedSaveStatementValues(@NonNull PreparedStatement preparedStatement, @NonNull MediaFile mediaFile)
            throws SQLException {
        preparedStatement.setLong(1, mediaFile.getMediaId());
        preparedStatement.setLong(2, mediaFile.getFileId());
        preparedStatement.setLong(3, mediaFile.getFileTypeId());
    }

    @Override
    protected void setPreparedUpdateStatementValues(@NonNull PreparedStatement preparedStatement, @NonNull MediaFile mediaFile)
            throws SQLException {
        preparedStatement.setLong(1, mediaFile.getMediaId());
        preparedStatement.setLong(2, mediaFile.getFileId());
        preparedStatement.setLong(3, mediaFile.getFileTypeId());
        preparedStatement.setLong(4, mediaFile.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MediaFile> findByParameters(@NonNull MediaFilesSearchParameters parameters) {
        return (parameters.getType() != null
                ? new ParametersSearcher("JOIN \"public.file_type\" ON \"public.media_file\".file_type_id = \"public.file_type\".id")
                    .addSearchStringCondition("\"public.file_type\".name", parameters.getType())
                : new ParametersSearcher())
                .tryAddEqualsCondition("media_id", parameters.getMediaId())
                .findByParameters(parameters.getPageIndex(), parameters.getPageSize(), this::mapRowToModel);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MediaFile> findByMediaIdAndFileType(@NonNull Long mediaId, @NonNull String fileType) {
        return this.new ParametersSearcher("JOIN \"public.file_type\" ON \"public.media_file\".file_type_id = \"public.file_type\".id")
                .addEqualsCondition("\"public.file_type\".name", fileType)
                .addEqualsCondition("media_id", mediaId)
                .findByParameters(this::mapRowToModel);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MediaFile> findByMediaId(@NonNull Long mediaId) {
        return this.new ParametersSearcher()
                .addEqualsCondition("media_id", mediaId)
                .findByParameters(this::mapRowToModel);
    }
}