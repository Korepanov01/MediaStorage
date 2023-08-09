package com.bftcom.mediastorage.repository.jdbc;

import com.bftcom.mediastorage.model.entity.Media;
import com.bftcom.mediastorage.model.searchparameters.MediaSearchParameters;
import com.bftcom.mediastorage.repository.MediaRepository;
import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.ListUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public JdbcMediaRepository() {
        super(TABLE_NAME, ID_FIELD, List.of(OTHER_FIELDS));
        this.setSqlUpdate(String.format(
                "UPDATE %s SET %s WHERE %s = ?",
                TABLE_NAME,
                Arrays.stream(OTHER_FIELDS).skip(1).map(field -> field + " = ?").collect(Collectors.joining(", ")),
                ID_FIELD));
    }

    @Override
    protected Media mapRowToModel(@NonNull ResultSet row, int rowNum) throws SQLException {
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
    protected void setPreparedSaveStatementValues(@NonNull PreparedStatement preparedStatement, @NonNull Media media)
            throws SQLException {
        preparedStatement.setLong(1, media.getUserId());
        preparedStatement.setLong(2, media.getCategoryId());
        preparedStatement.setString(3, media.getName());
        if (media.getDescription() != null) {
            preparedStatement.setString(4, media.getDescription());
        } else {
            preparedStatement.setNull(4, Types.VARCHAR);
        }
        preparedStatement.setLong(5, media.getMediaTypeId());
        preparedStatement.setObject(6, media.getCreatedAt());
        preparedStatement.setObject(7, media.getEditedAt());
    }

    @Override
    protected void setPreparedUpdateStatementValues(@NonNull PreparedStatement preparedStatement, @NonNull Media media)
            throws SQLException {
        preparedStatement.setLong(1, media.getCategoryId());
        preparedStatement.setString(2, media.getName());
        if (media.getDescription() != null) {
            preparedStatement.setString(3, media.getDescription());
        } else {
            preparedStatement.setNull(3, Types.VARCHAR);
        }
        preparedStatement.setLong(4, media.getMediaTypeId());
        preparedStatement.setObject(5, media.getCreatedAt());
        preparedStatement.setObject(6, media.getEditedAt());
        preparedStatement.setLong(7, media.getId());
    }

    private final static String CATEGORY_RECURSIVE = "WITH RECURSIVE category_recursive AS (\n" +
            "    SELECT id\n" +
            "    FROM \"public.category\"\n" +
            "    WHERE id = ?\n" +

            "    UNION\n" +

            "    SELECT c.id\n" +
            "    FROM \"public.category\" c\n" +
            "    JOIN category_recursive cr ON c.parent_category_id = cr.id\n)";

    @Override
    public List<Media> findByParameters(@NonNull MediaSearchParameters parameters) {
        String join = "";
        List<Object> beforeParams = new ArrayList<>();

        if (parameters.getCategoryId() != null) {
            join += " JOIN category_recursive cr ON \"public.media\".category_id = cr.id ";
            beforeParams.add(parameters.getCategoryId());
        }

        if (parameters.getTagIds() != null && !parameters.getTagIds().isEmpty()) {
            join += " JOIN \"public.media_tag\" mt ON \"public.media\".id = mt.media_id";
            beforeParams.addAll(parameters.getTagIds());
        }
        ParametersSearcher searcher = !join.equals("") ? this.new ParametersSearcher(join) : this.new ParametersSearcher();

        if (parameters.getCategoryId() != null) {
            searcher.addBefore(CATEGORY_RECURSIVE);
        }

        searcher.addStatement("", beforeParams.toArray());

        if (parameters.getTagIds() != null && !parameters.getTagIds().isEmpty()) {
            searcher.addCondition("mt.tag_id IN (" + String.join(", ", Collections.nCopies(parameters.getTagIds().size(), "?")) + ")");
        }

        if (!ListUtils.isEmpty(parameters.getTypeIds()))
            searcher.addCondition("media_type_id IN (" + String.join(", ", Collections.nCopies(parameters.getTypeIds().size(), "?")) + ")", parameters.getTypeIds().toArray());

        searcher.tryAddEqualsCondition("user_id", parameters.getUserId());

        return searcher
                .tryAddSearchStringCondition("name", parameters.getSearchString())
                .findByParameters(parameters.getPageIndex(), parameters.getPageSize(), this::mapRowToModel, parameters.getRandomOrder());
    }
}