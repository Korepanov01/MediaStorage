package com.bftcom.mediastorage.repository.jdbc;

import com.bftcom.mediastorage.model.entity.File;
import com.bftcom.mediastorage.repository.FileRepository;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcFileRepository extends JdbcCrudRepository<File> implements FileRepository {

    private static final String TABLE_NAME = "\"public.file\"";
    private static final String ID_FIELD = "id";
    private static final String[] OTHER_FIELDS = {"path", "size", "extension"};

    public JdbcFileRepository() {
        super(TABLE_NAME, ID_FIELD, List.of(OTHER_FIELDS));
    }

    @Override
    protected File mapRowToModel(ResultSet row, int rowNum) throws SQLException {
        return new File(
                row.getLong("id"),
                row.getString("path"),
                row.getShort("size"),
                row.getString("extension"));
    }

    @Override
    protected void setPreparedSaveStatementValues(PreparedStatement preparedStatement, File file)
            throws SQLException {
        preparedStatement.setString(1, file.getPath());
        preparedStatement.setShort(2, file.getSize());
        preparedStatement.setString(3, file.getExtension());
    }

    @Override
    protected void setPreparedUpdateStatementValues(PreparedStatement preparedStatement, File entity)
            throws SQLException {
        preparedStatement.setString(1, entity.getPath());
        preparedStatement.setShort(2, entity.getSize());
        preparedStatement.setString(3, entity.getExtension());
        preparedStatement.setLong(4, entity.getId());
    }
}