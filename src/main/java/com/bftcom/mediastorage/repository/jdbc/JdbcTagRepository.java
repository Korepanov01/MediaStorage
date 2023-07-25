package com.bftcom.mediastorage.repository.jdbc;

import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
import com.bftcom.mediastorage.repository.TagRepository;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTagRepository extends JdbcCrudRepository<Tag> implements TagRepository {

    private static final String SQL_FIND_BY_ID =
            "SELECT id, name " +
                    "FROM \"public.tag\" " +
                    "WHERE id=?";

    private static final String SQL_SAVE =
            "INSERT INTO \"public.tag\"(name) VALUES(?)";

    private static final String SQL_UPDATE =
            "UPDATE \"public.tag\" " +
                    "SET name = ? " +
                    "WHERE id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM \"public.tag\" " +
                    "WHERE id = ?";

    private static final String SQL_FIND_BY_NAME =
            "SELECT id, name FROM \"public.tag\" WHERE name = ? LIMIT 1";

    public JdbcTagRepository() {
        super(SQL_FIND_BY_ID, SQL_SAVE, SQL_UPDATE, SQL_DELETE);
    }

    @Override
    protected Tag mapRowToModel(ResultSet row, int rowNum) throws SQLException {
        return new Tag(
                row.getLong("id"),
                row.getString("name"));
    }

    @Override
    protected void setPreparedSaveStatementValues(PreparedStatement preparedStatement, Tag tag)
            throws SQLException {
        preparedStatement.setString(1, tag.getName());
    }

    @Override
    protected void setPreparedUpdateStatementValues(PreparedStatement preparedStatement, Tag entity)
            throws SQLException {
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setLong(2, entity.getId());
    }

    @Override
    public List<Tag> findByParameters(SearchStringParameters parameters) {
        ParametersSearchSqlBuilder builder = new ParametersSearchSqlBuilder("id, name", "\"public.tag\"");

        builder.addSearchStringCondition("name", parameters.getSearchString());

        builder.addPagination(parameters.getPageIndex(), parameters.getPageSize());

        return jdbcTemplate.query(builder.getQuery(), this::mapRowToModel, builder.getQueryParams());
    }

    @Override
    public Optional<Tag> findByName(String name) {
        List<Tag> results = jdbcTemplate.query(
                SQL_FIND_BY_NAME,
                this::mapRowToModel,
                name);

        return results.size() == 0 ?
                Optional.empty() :
                Optional.of(results.get(0));
    }
}