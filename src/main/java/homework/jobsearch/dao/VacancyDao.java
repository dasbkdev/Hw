package homework.jobsearch.dao;

import homework.jobsearch.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VacancyDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Vacancy> getAllActiveVacancies() {
        String sql = "select * from vacancies where is_active = true order by update_time desc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public List<Vacancy> getVacanciesByCategory(Long categoryId) {
        String sql = "select * from vacancies where category_id = ? and is_active = true order by update_time desc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), categoryId);
    }

    public Optional<Vacancy> getVacancyById(Long id) {
        String sql = "select * from vacancies where id = ?";
        try {
            Vacancy vacancy = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Vacancy.class), id);
            return Optional.ofNullable(vacancy);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public List<Vacancy> getVacanciesByAuthorId(Long authorId) {
        String sql = "select * from vacancies where author_id = ? order by update_time desc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), authorId);
    }

    public void save(Vacancy vacancy) {
        String sql = """
                insert into vacancies(name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
                values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        jdbcTemplate.update(
                sql,
                vacancy.getName(),
                vacancy.getDescription(),
                vacancy.getCategoryId(),
                vacancy.getSalary(),
                vacancy.getExpFrom(),
                vacancy.getExpTo(),
                vacancy.getIsActive(),
                vacancy.getAuthorId(),
                vacancy.getCreatedDate(),
                vacancy.getUpdateTime()
        );
    }
}