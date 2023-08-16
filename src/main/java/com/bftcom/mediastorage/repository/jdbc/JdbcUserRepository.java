package com.bftcom.mediastorage.repository.jdbc;

import com.bftcom.mediastorage.model.entity.User;
import com.bftcom.mediastorage.model.searchparameters.SearchStringParameters;
import com.bftcom.mediastorage.repository.UserRepository;
import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    protected User mapRowToModel(@NonNull ResultSet row, int rowNum) throws SQLException {
        return new User(
                row.getLong("id"),
                row.getString("name"),
                row.getString("password_hash"),
                row.getString("email"));
    }

    @Override
    protected void setPreparedSaveStatementValues(@NonNull PreparedStatement preparedStatement, @NonNull User user)
            throws SQLException {
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPasswordHash());
        preparedStatement.setString(3, user.getEmail());
    }

    @Override
    protected void setPreparedUpdateStatementValues(@NonNull PreparedStatement preparedStatement, @NonNull User user)
            throws SQLException {
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPasswordHash());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setLong(4, user.getId());
    }

    @Override
    public List<User> findByParameters(@NonNull SearchStringParameters parameters) {
        return this.new ParametersSearcher().select().where()
                .tryAddSearchStringCondition("name", parameters.getSearchString())
                .findByParameters(parameters.getPageIndex(), parameters.getPageSize(), this::mapRowToModel);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByEmail(@NonNull String email) {
        return findByUniqueField("email", email);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(@NonNull String name) {
        return existsByField("name", name);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(@NonNull String email) {
        return existsByField("email", email);
    }

    @Override
    @Transactional
    public void updateEmail(@NonNull String email, @NonNull Long id) {
        updateField("email", preparedStatement -> {
            try {
                preparedStatement.setString(1, email);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }, id);
    }

    @Override
    @Transactional
    public void updateName(@NonNull String name, @NonNull Long id) {
        updateField("name", preparedStatement -> {
            try {
                preparedStatement.setString(1, name);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByName(@NonNull String name) {
        return findByUniqueField("name", name);
    }
}