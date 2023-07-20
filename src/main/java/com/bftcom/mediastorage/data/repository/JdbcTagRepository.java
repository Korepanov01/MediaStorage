package com.bftcom.mediastorage.data.repository;

import com.bftcom.mediastorage.data.entity.Tag;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcTagRepository extends JdbcRepository<Tag> {

    private static final String SQL_FIND_BY_ID =
            "SELECT id, name " +
            "FROM \"public.tag\" " +
            "WHERE id=?";

    private static final String SQL_FIND_ALL =
            "SELECT id, name " +
            "FROM \"public.tag\"";

    private static final String SQL_SAVE =
            "INSERT INTO \"public.tag\"(name) VALUES(?)";

    private static final String SQL_UPDATE =
            "UPDATE \"public.tag\" " +
            "SET name = ? " +
            "WHERE id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM \"public.tag\" " +
            "WHERE id = ?";

    public JdbcTagRepository() {
        super(SQL_FIND_BY_ID, SQL_FIND_ALL, SQL_SAVE, SQL_UPDATE, SQL_DELETE);
    }

    @Override
    protected Tag mapRowToModel(ResultSet row, int rowNum) throws SQLException {
        return new Tag(
                row.getLong("id"),
                row.getString("name"));
    }

    @Override
    protected void setPreparedSaveStatementValues(PreparedStatement preparedStatement, Tag tag)
            throws SQLException {
        preparedStatement.setString(1, tag.getName());
    }

    @Override
    protected void setPreparedUpdateStatementValues(PreparedStatement preparedStatement, Tag tag)
            throws SQLException {
        preparedStatement.setString(1, tag.getName());
        preparedStatement.setLong(2, tag.getId());
    }
}
