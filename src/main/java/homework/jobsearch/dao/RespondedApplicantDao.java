package homework.jobsearch.dao;

import homework.jobsearch.model.RespondedApplicant;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RespondedApplicantDao {
    private final JdbcTemplate jdbcTemplate;

    public List<RespondedApplicant> getResponsesByVacancyId(Long vacancyId) {
        String sql = "select * from responded_applicants where vacancy_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(RespondedApplicant.class), vacancyId);
    }

    public List<RespondedApplicant> getResponsesByResumeId(Long resumeId) {
        String sql = "select * from responded_applicants where resume_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(RespondedApplicant.class), resumeId);
    }
}