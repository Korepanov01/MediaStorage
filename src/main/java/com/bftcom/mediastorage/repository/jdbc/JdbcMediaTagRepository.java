package com.bftcom.mediastorage.repository.jdbc;

import com.bftcom.mediastorage.model.entity.MediaTag;
import com.bftcom.mediastorage.repository.MediaTagRepository;
import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcMediaTagRepository extends JdbcCrudRepository<MediaTag> implements MediaTagRepository {

    private static final String TABLE_NAME = "\"public.media_tag\"";
    private static final String ID_FIELD = "id";
    private static final String[] OTHER_FIELDS = {"media_id", "tag_id"};

    public JdbcMediaTagRepository() {
        super(TABLE_NAME, ID_FIELD, List.of(OTHER_FIELDS));
    }

    @Override
    protected MediaTag mapRowToModel(@NonNull ResultSet row, int rowNum) throws SQLException {
        return new MediaTag(
                row.getLong("id"),
                row.getLong("media_id"),
                row.getLong("tag_id"));
    }

    @Override
    protected void setPreparedSaveStatementValues(@NonNull PreparedStatement preparedStatement, @NonNull MediaTag mediaTag)
            throws SQLException {
        preparedStatement.setLong(1, mediaTag.getMediaId());
        preparedStatement.setLong(2, mediaTag.getTagId());
    }

    @Override
    protected void setPreparedUpdateStatementValues(@NonNull PreparedStatement preparedStatement, @NonNull MediaTag entity)
            throws SQLException {
        preparedStatement.setLong(1, entity.getMediaId());
        preparedStatement.setLong(2, entity.getTagId());
        preparedStatement.setLong(3, entity.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public Integer mediaTagsCount(@NonNull Long mediaId) {
        return this.new ParametersSearcher().count().where()
                .addEqualsCondition("media_id", mediaId)
                .getCount();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MediaTag> findByMediaIdTagId(@NonNull Long mediaId, @NonNull Long tagId) {
        return this.new ParametersSearcher().select().where()
                .tryAddEqualsCondition("media_id", mediaId)
                .tryAddEqualsCondition("tag_id", tagId)
                .findUniqueByParameters(this::mapRowToModel);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByMediaIdTagId(@NonNull Long mediaId, @NonNull Long tagId) {
        return findByMediaIdTagId(mediaId, tagId).isPresent();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isExists(@NonNull Long mediaId, @NonNull Long tagId) {
        return findByMediaIdTagId(mediaId, tagId).isPresent();
    }
}