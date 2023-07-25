package com.bftcom.mediastorage.repository.jdbc;

import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
import com.bftcom.mediastorage.repository.TagRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTagRepository extends JdbcCrudRepository<Tag> implements TagRepository {

    private static final String SQL_FIND_BY_ID =
            "SELECT id, name " +
                    "FROM \"public.tag\" " +
                    "WHERE id=?";

    private static final String SQL_FIND_ALL =
            "SELECT id, name " +
                    "FROM \"public.tag\"";

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
        super(SQL_FIND_BY_ID, SQL_FIND_ALL, SQL_SAVE, SQL_UPDATE, SQL_DELETE);
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
        StringBuilder sqlBuilder = new StringBuilder(
                "SELECT id, name " +
                        "FROM \"public.tag\" WHERE 1=1");
        List<Object> queryParams = new ArrayList<>();

        if (parameters.getSearchString() != null && StringUtils.hasText(parameters.getSearchString())) {
            sqlBuilder.append(" AND LOWER(name) LIKE LOWER(?)");
            queryParams.add("%" + parameters.getSearchString() + "%");
        }

        int offset = parameters.getPageIndex() * parameters.getPageSize();
        sqlBuilder.append(" OFFSET ? LIMIT ?");
        queryParams.add(offset);
        queryParams.add(parameters.getPageSize());

        return jdbcTemplate.query(sqlBuilder.toString(), this::mapRowToModel, queryParams.toArray());
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