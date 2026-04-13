package homework.jobsearch.dao;

import homework.jobsearch.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public List<User> getAllApplicants() {
        String sql = "select * from users where account_type = 'APPLICANT'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> getAllEmployers() {
        String sql = "select * from users where account_type = 'EMPLOYER'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public Optional<User> getUserById(Long id) {
        String sql = "select * from users where id = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
            return Optional.ofNullable(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<User> getUserByEmail(String email) {
        String sql = "select * from users where email = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), email);
            return Optional.ofNullable(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public void save(User user) {
        String sql = """
                insert into users(name, surname, age, email, password, phone_number, avatar, account_type)
                values (?, ?, ?, ?, ?, ?, ?, ?)
                """;
        jdbcTemplate.update(
                sql,
                user.getName(),
                user.getSurname(),
                user.getAge(),
                user.getEmail(),
                user.getPassword(),
                user.getPhoneNumber(),
                user.getAvatar(),
                user.getAccountType()
        );
    }

    public void saveWithRole(User user, String role) {
        String sql = """
                insert into users(name, surname, age, email, password, phone_number, avatar, account_type, enabled, role_id)
                values (?, ?, ?, ?, ?, ?, ?, ?, ?, (select id from roles where role = ?))
                """;
        jdbcTemplate.update(
                sql,
                user.getName(),
                user.getSurname(),
                user.getAge(),
                user.getEmail(),
                user.getPassword(),
                user.getPhoneNumber(),
                user.getAvatar(),
                user.getAccountType(),
                user.getEnabled(),
                role
        );
    }


    public void update(Long id, String name, String surname, Integer age, String phoneNumber, String avatar) {
        String sql = """
                update users
                set name = ?, surname = ?, age = ?, phone_number = ?, avatar = ?
                where id = ?
                """;
        jdbcTemplate.update(sql, name, surname, age, phoneNumber, avatar, id);
    }
}