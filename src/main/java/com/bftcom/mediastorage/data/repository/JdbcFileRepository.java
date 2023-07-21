package com.bftcom.mediastorage.data.repository;

import com.bftcom.mediastorage.model.entity.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class JdbcFileRepository implements IRepository<File, Long> {

    private final JdbcTemplate jdbcTemplate;

    private final String SQL_FIND_BY_ID =
            "SELECT id, path, size, extension " +
                    "FROM \"public.file\" " +
                    "WHERE id=?";

    private final String SQL_FIND_ALL =
            "SELECT id, path, size, extension " +
                    "FROM \"public.file\"";

    private final String SQL_SAVE =
            "INSERT INTO \"public.file\"(path, size, extension) VALUES(?, ?, ?)";

    private final String SQL_UPDATE =
            "UPDATE \"public.file\" " +
                    "SET path = ?, size = ?, extension = ? " +
                    "WHERE id = ?";

    private final String SQL_DELETE =
            "DELETE FROM \"public.file\" " +
                    "WHERE id = ?";

    @Autowired
    public JdbcFileRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<File> findById(Long id) {
        List<File> results = jdbcTemplate.query(
                SQL_FIND_BY_ID,
                this::mapRowToFile,
                id);
        return results.size() == 0 ?
                Optional.empty() :
                Optional.of(results.get(0));
    }

    @Override
    public List<File> findAll() {
        return jdbcTemplate.query(
                SQL_FIND_ALL,
                this::mapRowToFile);
    }

    @Override
    public File save(File file) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    SQL_SAVE,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, file.getPath());
            preparedStatement.setShort(2, file.getSize());
            preparedStatement.setString(3, file.getExtension());
            return preparedStatement;
        }, generatedKeyHolder);

        long id = (long) Objects.requireNonNull(generatedKeyHolder.getKeys()).get("id");
        file.setId(id);

        return file;
    }

    @Override
    public void update(File file) {
        jdbcTemplate.update(
                SQL_UPDATE,
                file.getPath(),
                file.getSize(),
                file.getExtension(),
                file.getId());
    }

    @Override
    public void delete(File file) {
        jdbcTemplate.update(
                SQL_DELETE,
                file.getId());
    }

    private File mapRowToFile(ResultSet row, int rowNum)
            throws SQLException {
        return new File(
                row.getLong("id"),
                row.getString("path"),
                row.getShort("size"),
                row.getString("extension"));
    }
}