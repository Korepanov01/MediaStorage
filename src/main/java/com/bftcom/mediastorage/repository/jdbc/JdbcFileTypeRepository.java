package com.bftcom.mediastorage.repository.jdbc;

import com.bftcom.mediastorage.repository.FileTypeRepository;
import com.bftcom.mediastorage.model.entity.FileType;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcFileTypeRepository extends JdbcCrudRepository<FileType> implements FileTypeRepository {

    private static final String SQL_FIND_BY_ID =
            "SELECT id, type " +
                    "FROM \"public.file_type\" " +
                    "WHERE id = ?";

    private static final String SQL_SAVE =
            "INSERT INTO \"public.file_type\"(type) VALUES(?)";

    private static final String SQL_UPDATE =
            "UPDATE \"public.file_type\" " +
                    "SET type = ? " +
                    "WHERE id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM \"public.file_type\" " +
                    "WHERE id = ?";

    public JdbcFileTypeRepository() {
        super(SQL_FIND_BY_ID, SQL_SAVE, SQL_UPDATE, SQL_DELETE);
    }

    @Override
    protected FileType mapRowToModel(ResultSet row, int rowNum) throws SQLException {
        return new FileType(
                row.getLong("id"),
                row.getString("type"));
    }

    @Override
    protected void setPreparedSaveStatementValues(PreparedStatement preparedStatement, FileType fileType)
            throws SQLException {
        preparedStatement.setString(1, fileType.getType());
    }

    @Override
    protected void setPreparedUpdateStatementValues(PreparedStatement preparedStatement, FileType entity)
            throws SQLException {
        preparedStatement.setString(1, entity.getType());
        preparedStatement.setLong(2, entity.getId());
    }
}