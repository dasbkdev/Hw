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
}