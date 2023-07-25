package com.bftcom.mediastorage.repository.jdbc;

import com.bftcom.mediastorage.model.entity.Role;
import com.bftcom.mediastorage.model.entity.Tag;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
import com.bftcom.mediastorage.repository.RoleRepository;
import org.postgresql.util.PSQLException;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcRoleRepository extends JdbcCrudRepository<Role> implements RoleRepository {

    private static final String SQL_FIND_BY_ID =
            "SELECT id, name " +
                    "FROM \"public.role\" " +
                    "WHERE id = ?";

    private static final String SQL_FIND_ALL =
            "SELECT id, name " +
                    "FROM \"public.role\"";

    private static final String SQL_SAVE =
            "INSERT INTO \"public.role\"(name) VALUES(?)";

    private static final String SQL_UPDATE =
            "UPDATE \"public.role\" " +
                    "SET name = ? " +
                    "WHERE id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM \"public.role\" " +
                    "WHERE id = ?";

    private static final String SQL_FIND_BY_NAME =
            "SELECT id, name " +
                    "FROM \"public.role\" " +
                    "WHERE name = ?";

    public JdbcRoleRepository() {
        super(SQL_FIND_BY_ID, SQL_FIND_ALL, SQL_SAVE, SQL_UPDATE, SQL_DELETE);
    }

    @Override
    protected Role mapRowToModel(ResultSet row, int rowNum) throws SQLException {
        return new Role(
                row.getLong("id"),
                row.getString("name"));
    }

    @Override
    protected void setPreparedSaveStatementValues(PreparedStatement preparedStatement, Role role)
            throws SQLException {
        preparedStatement.setString(1, role.getName());
    }

    @Override
    protected void setPreparedUpdateStatementValues(PreparedStatement preparedStatement, Role role)
            throws SQLException {
        preparedStatement.setString(1, role.getName());
        preparedStatement.setLong(2, role.getId());
    }

    @Override
    public Optional<Role> findByName(String name) {
        List<Role> results = jdbcTemplate.query(
                SQL_FIND_BY_NAME,
                this::mapRowToModel,
                name);
        return results.size() == 0 ?
                Optional.empty() :
                Optional.of(results.get(0));
    }

    @Override
    public List<Role> findByParameters(SearchStringParameters parameters) {
        StringBuilder sqlBuilder = new StringBuilder(
                "SELECT id, name " +
                        "FROM \"public.role\" WHERE 1=1");
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
}