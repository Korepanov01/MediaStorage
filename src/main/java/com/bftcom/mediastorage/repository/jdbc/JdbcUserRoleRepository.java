package com.bftcom.mediastorage.repository.jdbc;

import com.bftcom.mediastorage.model.entity.UserRole;
import com.bftcom.mediastorage.repository.UserRoleRepository;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcUserRoleRepository extends JdbcCrudRepository<UserRole, Long> implements UserRoleRepository {

    private static final String SQL_FIND_BY_ID =
            "SELECT id, role_id, user_id " +
                    "FROM \"public.user_role\" " +
                    "WHERE id = ?";

    private static final String SQL_FIND_ALL =
            "SELECT id, role_id, user_id " +
                    "FROM \"public.user_role\"";

    private static final String SQL_SAVE =
            "INSERT INTO \"public.user_role\"(role_id, user_id) VALUES(?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE \"public.user_role\" " +
                    "SET role_id = ?, user_id = ? " +
                    "WHERE id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM \"public.user_role\" " +
                    "WHERE id = ?";

    public JdbcUserRoleRepository() {
        super(SQL_FIND_BY_ID, SQL_FIND_ALL, SQL_SAVE, SQL_UPDATE, SQL_DELETE);
    }

    @Override
    protected UserRole mapRowToModel(ResultSet row, int rowNum) throws SQLException {
        return new UserRole(
                row.getLong("id"),
                row.getLong("role_id"),
                row.getLong("user_id"));
    }

    @Override
    protected void setPreparedSaveStatementValues(PreparedStatement preparedStatement, UserRole userRole)
            throws SQLException {
        preparedStatement.setLong(1, userRole.getRoleId());
        preparedStatement.setLong(2, userRole.getUserId());
    }

    @Override
    protected void setPreparedUpdateStatementValues(PreparedStatement preparedStatement, UserRole userRole)
            throws SQLException {
        preparedStatement.setLong(1, userRole.getRoleId());
        preparedStatement.setLong(2, userRole.getUserId());
        preparedStatement.setLong(3, userRole.getId());
    }
}