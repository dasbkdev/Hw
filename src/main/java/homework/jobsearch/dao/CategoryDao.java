package homework.jobsearch.dao;

import homework.jobsearch.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Category> getAllCategories() {
        String sql = "select * from categories order by name";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class));
    }
}