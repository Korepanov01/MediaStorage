package com.bftcom.mediastorage.repository.jdbc;

import com.bftcom.mediastorage.model.entity.UserRole;
import com.bftcom.mediastorage.repository.UserRoleRepository;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcUserRoleRepository extends JdbcCrudRepository<UserRole> implements UserRoleRepository {

    private static final String TABLE_NAME = "\"public.user_role\"";
    private static final String ID_FIELD = "id";
    private static final String[] OTHER_FIELDS = {"role_id", "user_id"};

    public JdbcUserRoleRepository() {
        super(TABLE_NAME, ID_FIELD, List.of(OTHER_FIELDS));
    }

    @Override
    protected UserRole mapRowToModel(@NonNull ResultSet row, int rowNum) throws SQLException {
        return new UserRole(
                row.getLong("id"),
                row.getLong("role_id"),
                row.getLong("user_id"));
    }

    @Override
    protected void setPreparedSaveStatementValues(@NonNull PreparedStatement preparedStatement, @NonNull UserRole userRole)
            throws SQLException {
        preparedStatement.setLong(1, userRole.getRoleId());
        preparedStatement.setLong(2, userRole.getUserId());
    }

    @Override
    protected void setPreparedUpdateStatementValues(@NonNull PreparedStatement preparedStatement, @NonNull UserRole userRole)
            throws SQLException {
        preparedStatement.setLong(1, userRole.getRoleId());
        preparedStatement.setLong(2, userRole.getUserId());
        preparedStatement.setLong(3, userRole.getId());
    }

    @Override
    public Optional<UserRole> findByUserAndRole(@NonNull Long userId, @NonNull Long roleId) {
        return this.new ParametersSearcher()
                .addEqualsCondition("role_id", roleId)
                .addEqualsCondition("user_id", userId)
                .findUniqueByParameters(this::mapRowToModel);
    }

    @Override
    public boolean isExists(@NonNull Long userId, @NonNull Long roleId) {
        return findByUserAndRole(userId, roleId).isPresent();
    }
}