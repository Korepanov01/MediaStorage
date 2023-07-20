package com.bftcom.mediastorage.data.repository.jdbc;

import com.bftcom.mediastorage.data.entity.Role;
import com.bftcom.mediastorage.data.repository.RoleRepository;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcRoleRepository extends JdbcCrudRepository<Role, Long> implements RoleRepository {

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
}