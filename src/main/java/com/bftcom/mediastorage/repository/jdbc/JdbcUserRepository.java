package com.bftcom.mediastorage.repository.jdbc;

import com.bftcom.mediastorage.model.entity.User;
import com.bftcom.mediastorage.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcUserRepository extends JdbcCrudRepository<User, Long> implements UserRepository {

    private static final String SQL_FIND_BY_ID =
            "SELECT id, name, password_hash, email " +
                    "FROM \"public.user\" " +
                    "WHERE id = ?";

    private static final String SQL_FIND_ALL =
            "SELECT id, name, password_hash, email " +
                    "FROM \"public.user\"";

    private static final String SQL_SAVE =
            "INSERT INTO \"public.user\"(name, password_hash, email) VALUES(?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE \"public.user\" " +
                    "SET name = ?, password_hash = ?, email = ? " +
                    "WHERE id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM \"public.user\" " +
                    "WHERE id = ?";

    public JdbcUserRepository() {
        super(SQL_FIND_BY_ID, SQL_FIND_ALL, SQL_SAVE, SQL_UPDATE, SQL_DELETE);
    }

    @Override
    protected User mapRowToModel(ResultSet row, int rowNum) throws SQLException {
        return new User(
                row.getLong("id"),
                row.getString("name"),
                row.getString("password_hash"),
                row.getString("email"));
    }

    @Override
    protected void setPreparedSaveStatementValues(PreparedStatement preparedStatement, User user)
            throws SQLException {
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPasswordHash());
        preparedStatement.setString(3, user.getEmail());
    }

    @Override
    protected void setPreparedUpdateStatementValues(PreparedStatement preparedStatement, User user)
            throws SQLException {
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPasswordHash());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setLong(4, user.getId());
    }
}