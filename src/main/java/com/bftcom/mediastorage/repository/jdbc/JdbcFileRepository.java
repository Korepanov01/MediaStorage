package com.bftcom.mediastorage.repository.jdbc;

import com.bftcom.mediastorage.model.entity.File;
import com.bftcom.mediastorage.repository.FileRepository;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcFileRepository extends JdbcCrudRepository<File> implements FileRepository {

    private static final String TABLE_NAME = "\"public.file\"";
    private static final String ID_FIELD = "id";
    private static final String[] OTHER_FIELDS = {"name", "data"};

    public JdbcFileRepository() {
        super(TABLE_NAME, ID_FIELD, List.of(OTHER_FIELDS));
    }

    @Override
    protected File mapRowToModel(@NonNull ResultSet row, int rowNum) throws SQLException {
        return new File(
                row.getLong("id"),
                row.getString("name"),
                row.getBytes("data"));
    }

    @Override
    protected void setPreparedSaveStatementValues(@NonNull PreparedStatement preparedStatement, @NonNull File file)
            throws SQLException {
        preparedStatement.setString(1, file.getName());
        preparedStatement.setBytes(2, file.getData());
    }

    @Override
    protected void setPreparedUpdateStatementValues(@NonNull PreparedStatement preparedStatement, @NonNull File file)
            throws SQLException {
        preparedStatement.setString(1, file.getName());
        preparedStatement.setBytes(2, file.getData());
        preparedStatement.setLong(3, file.getId());
    }
}