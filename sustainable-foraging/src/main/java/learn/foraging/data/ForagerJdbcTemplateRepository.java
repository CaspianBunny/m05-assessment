package learn.foraging.data;

import learn.foraging.models.Forager;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

//comment hakeem

public class ForagerJdbcTemplateRepository implements ForagerRepository {
    // this is a comment

    private final JdbcTemplate jdbcTemplate;

    public ForagerJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Forager> findAll() {
        final String sql = """
                select forager_id, first_name, last_name, state_abbr
                from forager;
                """;
        return jdbcTemplate.query(sql, new ForagerMapper());
    }
    @Override
    public Forager findById(int id) {
        final String sql = """
                select forager_id, first_name, last_name, state_abbr
                from forager
                where forager_id = ?;
                """;
        return jdbcTemplate.query(sql, new ForagerMapper(), id).stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Forager> findByLastName(String lastNamePrefix) {
        final String sql = """
                select forager_id, first_name, last_name, state_abbr
                from forager
                where last_name like ?;
                """;
        return jdbcTemplate.query(sql, new ForagerMapper(), lastNamePrefix + "%");
    }

    @Override
    public List<Forager> findByState(String stateAbbr) {
        final String sql = """
                select forager_id, first_name, last_name, state_abbr
                from forager
                where state_abbr = ?;
                """;
        return jdbcTemplate.query(sql, new ForagerMapper(), stateAbbr);
    }

    @Override
    public Forager add(Forager forager) {
        final String sql = """
                insert into forager (first_name, last_name, state_abbr)
                values (?, ?, ?);
                """;
        int rowsAffected = jdbcTemplate.update(sql,
                forager.getFirstName(),
                forager.getLastName(),
                forager.getState());
        if (rowsAffected <= 0) {
            return null;
        }
        return forager;
    }

    @Override
    public boolean update(Forager forager) {
        return false;
    }

    @Override
    public Forager findByFirstAndLastName(String firstName, String lastName) {
        return null;
    }
}
