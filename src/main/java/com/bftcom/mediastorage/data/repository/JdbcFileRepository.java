package com.bftcom.mediastorage.data.repository;

import com.bftcom.mediastorage.data.entity.File;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcFileRepository extends JdbcCrudRepository<File, Long> {

    private static final String SQL_FIND_BY_ID =
            "SELECT id, path, size, extension " +
                    "FROM \"public.file\" " +
                    "WHERE id = ?";

    private static final String SQL_FIND_ALL =
            "SELECT id, path, size, extension " +
                    "FROM \"public.file\"";

    private static final String SQL_SAVE =
            "INSERT INTO \"public.file\"(path, size, extension) VALUES(?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE \"public.file\" " +
                    "SET path = ?, size = ?, extension = ? " +
                    "WHERE id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM \"public.file\" " +
                    "WHERE id = ?";

    public JdbcFileRepository() {
        super(SQL_FIND_BY_ID, SQL_FIND_ALL, SQL_SAVE, SQL_UPDATE, SQL_DELETE);
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