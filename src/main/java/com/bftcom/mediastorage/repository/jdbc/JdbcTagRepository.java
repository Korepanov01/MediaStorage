package com.bftcom.mediastorage.repository.jdbc;

import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.model.searchparameters.TagSearchParameters;
import com.bftcom.mediastorage.repository.TagRepository;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTagRepository extends JdbcCrudRepository<Tag> implements TagRepository {

    private static final String TABLE_NAME = "\"public.tag\"";
    private static final String ID_FIELD = "id";
    private static final String[] OTHER_FIELDS = {"name"};

    public JdbcTagRepository() {
        super(TABLE_NAME, ID_FIELD, List.of(OTHER_FIELDS));
    }

    @Override
    protected Tag mapRowToModel(@NonNull ResultSet row, int rowNum) throws SQLException {
        return new Tag(
                row.getLong("id"),
                row.getString("name"));
    }

    @Override
    protected void setPreparedSaveStatementValues(@NonNull PreparedStatement preparedStatement, @NonNull Tag tag)
            throws SQLException {
        preparedStatement.setString(1, tag.getName());
    }

    @Override
    protected void setPreparedUpdateStatementValues(@NonNull PreparedStatement preparedStatement, @NonNull Tag entity)
            throws SQLException {
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setLong(2, entity.getId());
    }

    @Override
    public List<Tag> findByParameters(@NonNull TagSearchParameters parameters) {
        return (parameters.getMediaId() == null
                ? this.new ParametersSearcher()
                : this.new ParametersSearcher("\"public.media_tag\" ON \"public.tag\".id = \"public.media_tag\".tag_id"))
                .tryAddSearchStringCondition("name", parameters.getSearchString())
                .tryAddEqualsCondition("\"public.media_tag\".media_id", parameters.getMediaId())
                .findByParameters(parameters.getPageIndex(), parameters.getPageSize(), this::mapRowToModel);
    }

    @Override
    public Optional<Tag> findByName(@NonNull String name) {
        return this.findByUniqueField("name", name);
    }
}