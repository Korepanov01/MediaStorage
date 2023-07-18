package com.bftcom.mediastorage.data.repository;

import com.bftcom.mediastorage.data.model.Tag;
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
public class JdbcTagRepository implements IRepository<Tag, Long> {

    private final JdbcTemplate jdbcTemplate;

    private final String SQL_FIND_BY_ID =
            "SELECT id, name " +
            "FROM \"public.tag\" " +
            "WHERE id=?";

    private final String SQL_FIND_ALL =
            "SELECT id, name " +
            "FROM \"public.tag\"";

    private final String SQL_SAVE =
            "INSERT INTO \"public.tag\"(name) VALUES(?)";

    private final String SQL_UPDATE =
            "UPDATE \"public.tag\" " +
            "SET name = ? " +
            "WHERE id = ?";

    private final String SQL_DELETE =
            "DELETE \"public.tag\" " +
            "WHERE id = ?";

    @Autowired
    public JdbcTagRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Optional<Tag> findById(Long id) {
        List<Tag> results = jdbcTemplate.query(
                SQL_FIND_BY_ID,
                this::mapRowToTag,
                id);
        return results.size() == 0 ?
                Optional.empty() :
                Optional.of(results.get(0));
    }

    @Override
    public List<Tag> findAll() {
        return jdbcTemplate.query(
                SQL_FIND_ALL,
                this::mapRowToTag);
    }

    @Override
    public Tag save(Tag tag) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    SQL_SAVE,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, tag.getName());
            return preparedStatement;
        }, generatedKeyHolder);


        long id = (long)Objects.requireNonNull(generatedKeyHolder.getKeys()).get("id");

        tag.setId(id);

        return tag;
    }

    @Override
    public void update(Tag tag) {
        jdbcTemplate.update(
                SQL_UPDATE,
                tag.getName(),
                tag.getId());
    }

    @Override
    public void delete(Tag tag) {
        jdbcTemplate.update(
                SQL_DELETE,
                tag.getId());
    }

    private Tag mapRowToTag(ResultSet row, int rowNum)
            throws SQLException {
        return new Tag(
                row.getLong("id"),
                row.getString("name"));
    }
}
