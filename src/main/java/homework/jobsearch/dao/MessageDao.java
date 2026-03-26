package homework.jobsearch.dao;

import homework.jobsearch.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MessageDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Message> getMessagesByResponseId(Long responseId) {
        String sql = "select * from messages where responded_applicants = ? order by timestamp";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Message.class), responseId);
    }
}