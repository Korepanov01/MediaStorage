package com.bftcom.mediastorage.repository.jdbc;

import com.bftcom.mediastorage.model.entity.MediaType;
import com.bftcom.mediastorage.repository.MediaTypeRepository;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcMediaTypeRepository extends JdbcCrudRepository<MediaType> implements MediaTypeRepository {

    private static final String TABLE_NAME = "\"public.media_type\"";
    private static final String ID_FIELD = "id";
    private static final String[] OTHER_FIELDS = {"name"};

    public JdbcMediaTypeRepository() {
        super(TABLE_NAME, ID_FIELD, List.of(OTHER_FIELDS));
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