package com.bftcom.mediastorage.repository.jdbc;

import com.bftcom.mediastorage.model.entity.FileType;
import com.bftcom.mediastorage.repository.FileTypeRepository;
import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcFileTypeRepository extends JdbcCrudRepository<FileType> implements FileTypeRepository {

    private static final String TABLE_NAME = "\"public.file_type\"";
    private static final String ID_FIELD = "id";
    private static final String[] OTHER_FIELDS = {"name"};

    public JdbcFileTypeRepository() {
        super(TABLE_NAME, ID_FIELD, List.of(OTHER_FIELDS));
    }

    @Override
    protected FileType mapRowToModel(@NonNull ResultSet row, int rowNum) throws SQLException {
        return new FileType(
                row.getLong("id"),
                row.getString("name"));
    }

    @Override
    protected void setPreparedSaveStatementValues(@NonNull PreparedStatement preparedStatement, @NonNull FileType fileType)
            throws SQLException {
        preparedStatement.setString(1, fileType.getName());
    }

    @Override
    protected void setPreparedUpdateStatementValues(@NonNull PreparedStatement preparedStatement, @NonNull FileType entity)
            throws SQLException {
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setLong(2, entity.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FileType> findByName(@NonNull String name) {
        return findByUniqueField("name", name);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(@NonNull String name) {
        return findByName(name).isPresent();
    }

    @Override
    @Transactional(readOnly = true)
    public List<FileType> findBySearchString(@NonNull String searchString) {
        return this.new ParametersSearcher().select().where()
                .addSearchStringCondition("name", searchString)
                .findByParameters(this::mapRowToModel);
    }
}