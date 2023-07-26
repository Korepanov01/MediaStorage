package com.bftcom.mediastorage.repository.jdbc;

import com.bftcom.mediastorage.model.entity.Media;
import com.bftcom.mediastorage.model.parameters.MediaSearchParameters;
import com.bftcom.mediastorage.repository.MediaRepository;
import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Repository
public class JdbcMediaRepository extends JdbcCrudRepository<Media> implements MediaRepository {

    private static final String TABLE_NAME = "\"public.media\"";
    private static final String ID_FIELD = "id";
    private static final String[] OTHER_FIELDS = {
            "user_id",
            "category_id",
            "name",
            "description",
            "media_type_id",
            "created_at",
            "edited_at"
    };

    private static final String SQL_FIND_RANDOM =
            "SELECT id, user_id, category_id, name, description, media_type_id, created_at, edited_at " +
                    "FROM \"public.media\" " +
                    "ORDER BY RANDOM()\n" +
                    "LIMIT ?;";

    public JdbcMediaRepository() {
        super(TABLE_NAME, ID_FIELD, List.of(OTHER_FIELDS));
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
    public List<Media> findRandom(int limit) {
        return jdbcTemplate.query(
                this.getSqlSelectFrom() + " ORDER BY RANDOM() LIMIT ?",
                this::mapRowToModel,
                limit);
    }

    @Override
    public List<Media> findByParameters(@NonNull MediaSearchParameters parameters) {
        ParametersSearchSqlBuilder builder = this.new ParametersSearchSqlBuilder();

        if (parameters.getCategoryId() != null) {
            builder.addCondition("category_id = ?", parameters.getCategoryId());
        }

        if (parameters.getTagIds() != null && !parameters.getTagIds().isEmpty()) {
            builder.addCondition("id IN (SELECT m.id FROM \"public.media\" m " +
                            "INNER JOIN \"public.media_tag\" mt ON m.id = mt.media_id " +
                            "WHERE mt.tag_id IN (" + String.join(", ", Collections.nCopies(parameters.getTagIds().size(), "?")) + "))",
                    parameters.getTagIds().toArray());
        }

        if (parameters.getSearchString() != null && StringUtils.hasText(parameters.getSearchString())) {
            builder.addSearchStringCondition("name", parameters.getSearchString());
        }

        builder.addPagination(parameters.getPageIndex(), parameters.getPageSize());

        return jdbcTemplate.query(builder.getQuery(), this::mapRowToModel, builder.getQueryParams());
    }
}