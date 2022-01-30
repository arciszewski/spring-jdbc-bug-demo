package demo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JdbcRepo implements DataRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(String key, String value) {
        jdbcTemplate.update("INSERT INTO TEST (KEY, VALUE) VALUES (?, ?)", key, value);
    }

    @Override
    public Optional<String> getFromList(String key) {
        return jdbcTemplate.query("SELECT VALUE FROM TEST WHERE KEY = ?",
                        (rs, i) -> rs.getString("VALUE"), key)
                .stream()
                .findFirst();
    }

    @Override
    public Optional<String> getFromStream(String key) {
        return jdbcTemplate.queryForStream("SELECT VALUE FROM TEST WHERE KEY = ?",
                (rs, i) -> rs.getString("VALUE"), key)
                .findFirst();
    }
}
