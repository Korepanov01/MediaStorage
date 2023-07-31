package com.bftcom.mediastorage.repository.jdbc;

import com.bftcom.mediastorage.model.entity.Role;
import com.bftcom.mediastorage.model.searchparameters.RoleSearchParameters;
import com.bftcom.mediastorage.repository.RoleRepository;
import lombok.NonNull;
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

    public JdbcRoleRepository() {
        super(TABLE_NAME, ID_FIELD, List.of(OTHER_FIELDS));
    }

    @Override
    protected Role mapRowToModel(@NonNull ResultSet row, int rowNum) throws SQLException {
        return new Role(
                row.getLong("id"),
                row.getString("name"));
    }

    @Override
    protected void setPreparedSaveStatementValues(@NonNull PreparedStatement preparedStatement, @NonNull Role role)
            throws SQLException {
        preparedStatement.setString(1, role.getName());
    }

    @Override
    protected void setPreparedUpdateStatementValues(@NonNull PreparedStatement preparedStatement, @NonNull Role role)
            throws SQLException {
        preparedStatement.setString(1, role.getName());
        preparedStatement.setLong(2, role.getId());
    }

    @Override
    public Optional<Role> findByName(@NonNull String name) {
        return this.findByUniqueField("name", name);
    }

    @Override
    public List<Role> findByParameters(@NonNull RoleSearchParameters parameters) {
        return (parameters.getUserId() != null
                ? this.new ParametersSearcher("\"public.user_role\" ON \"public.role\".id = \"public.user_role\".role_id")
                .addEqualsCondition("\"public.user_role\".user_id", parameters.getUserId())
                : this.new ParametersSearcher())
                .tryAddSearchStringCondition("name", parameters.getSearchString())
                .findByParameters(parameters.getPageIndex(), parameters.getPageSize(), this::mapRowToModel);
    }

    @Override
    public List<Role> findByUserId(@NonNull Long userId) {
        return this.new ParametersSearcher(String.format("\"public.user_role\" ur ON %s.id = ur.role_id", TABLE_NAME))
                .addEqualsCondition("ur.\"user_id\"", userId)
                .findByParameters(this::mapRowToModel);
    }
}