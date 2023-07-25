package com.bftcom.mediastorage.repository.jdbc;

import com.bftcom.mediastorage.model.entity.Role;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
import com.bftcom.mediastorage.repository.RoleRepository;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcRoleRepository extends JdbcCrudRepository<Role> implements RoleRepository {

    private static final String TABLE_NAME = "\"public.role\"";
    private static final String ID_FIELD = "id";
    private static final String[] OTHER_FIELDS = {"name"};

    private static final String SQL_FIND_BY_NAME =
            "SELECT id, name " +
                    "FROM \"public.role\" " +
                    "WHERE name = ?";

    public JdbcRoleRepository() {
        super(TABLE_NAME, ID_FIELD, List.of(OTHER_FIELDS));
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
        ParametersSearchSqlBuilder builder = new ParametersSearchSqlBuilder(
                "id, name",
                "\"public.role\"");

        builder.addSearchStringCondition("name", parameters.getSearchString());
        builder.addPagination(parameters.getPageIndex(), parameters.getPageSize());

        return jdbcTemplate.query(builder.getQuery(), this::mapRowToModel, builder.getQueryParams());
    }
}