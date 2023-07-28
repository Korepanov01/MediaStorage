package com.bftcom.mediastorage.repository.jdbc;

import com.bftcom.mediastorage.model.entity.FileType;
import com.bftcom.mediastorage.model.parameters.SearchStringParameters;
import com.bftcom.mediastorage.repository.FileTypeRepository;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

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
                row.getString("type"));
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
    public Optional<FileType> findByName(@NonNull String name) {
        return findByUniqueField("name", name);
    }

    @Override
    public List<FileType> findByParameters(@NonNull SearchStringParameters parameters) {
        return this.new ParametersSearcher()
                .addSearchStringCondition("name", parameters.getSearchString())
                .findByParameters(parameters.getPageIndex(), parameters.getPageSize(), this::mapRowToModel);
    }
}