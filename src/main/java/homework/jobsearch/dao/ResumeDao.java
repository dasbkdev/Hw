package homework.jobsearch.dao;

import homework.jobsearch.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ResumeDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Resume> getAllActiveResumes() {
        String sql = "select * from resumes where is_active = true order by update_time desc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }

    public List<Resume> getResumesByCategory(Long categoryId) {
        String sql = "select * from resumes where category_id = ? and is_active = true order by update_time desc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), categoryId);
    }

    public List<Resume> getResumesByApplicantId(Long applicantId) {
        String sql = "select * from resumes where applicant_id = ? order by update_time desc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), applicantId);
    }

    public Optional<Resume> getResumeById(Long id) {
        String sql = "select * from resumes where id = ?";
        try {
            Resume resume = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Resume.class), id);
            return Optional.ofNullable(resume);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public void save(Resume resume) {
        String sql = """
                insert into resumes(applicant_id, name, category_id, salary, is_active, created_date, update_time)
                values (?, ?, ?, ?, ?, ?, ?)
                """;
        jdbcTemplate.update(
                sql,
                resume.getApplicantId(),
                resume.getName(),
                resume.getCategoryId(),
                resume.getSalary(),
                resume.getIsActive(),
                resume.getCreatedDate(),
                resume.getUpdateTime()
        );
    }
}