package com.bftcom.mediastorage.repository.jdbc;

import com.bftcom.mediastorage.model.entity.MediaType;
import com.bftcom.mediastorage.repository.MediaTypeRepository;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcMediaTypeRepository extends JdbcCrudRepository<MediaType> implements MediaTypeRepository {

    private static final String SQL_FIND_BY_ID =
            "SELECT id, name " +
                    "FROM \"public.media_type\" " +
                    "WHERE id = ?";

    private static final String SQL_SAVE =
            "INSERT INTO \"public.media_type\"(name) VALUES(?)";

    private static final String SQL_UPDATE =
            "UPDATE \"public.media_type\" " +
                    "SET name = ? " +
                    "WHERE id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM \"public.media_type\" " +
                    "WHERE id = ?";

    public JdbcMediaTypeRepository() {
        super(SQL_FIND_BY_ID, SQL_SAVE, SQL_UPDATE, SQL_DELETE);
    }

    @Override
    protected MediaType mapRowToModel(ResultSet row, int rowNum) throws SQLException {
        return new MediaType(
                row.getLong("id"),
                row.getString("name"));
    }

    @Override
    protected void setPreparedSaveStatementValues(PreparedStatement preparedStatement, MediaType mediaType)
            throws SQLException {
        preparedStatement.setString(1, mediaType.getName());
    }

    @Override
    protected void setPreparedUpdateStatementValues(PreparedStatement preparedStatement, MediaType mediaType)
            throws SQLException {
        preparedStatement.setString(1, mediaType.getName());
        preparedStatement.setLong(2, mediaType.getId());
    }
}