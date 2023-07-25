package com.bftcom.mediastorage.repository.jdbc;

import com.bftcom.mediastorage.model.entity.User;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
import com.bftcom.mediastorage.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcUserRepository extends JdbcCrudRepository<User> implements UserRepository {

    private static final String TABLE_NAME = "\"public.user\"";
    private static final String ID_FIELD = "id";
    private static final String[] OTHER_FIELDS = {"name", "password_hash", "email"};

    public JdbcUserRepository() {
        super(TABLE_NAME, ID_FIELD, List.of(OTHER_FIELDS));
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

    @Override
    public List<User> findByParameters(SearchStringParameters parameters) {
        ParametersSearchSqlBuilder builder = new ParametersSearchSqlBuilder("id, name, password_hash, email", "\"public.tag\"");

        builder.addSearchStringCondition("name", parameters.getSearchString());

        builder.addPagination(parameters.getPageIndex(), parameters.getPageSize());

        return jdbcTemplate.query(builder.getQuery(), this::mapRowToModel, builder.getQueryParams());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return findByUniqueField("email", email);
    }
}