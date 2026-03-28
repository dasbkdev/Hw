package homework.jobsearch.service;

import homework.jobsearch.dao.UserDao;
import homework.jobsearch.dto.RegisterDto;
import homework.jobsearch.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;

    public List<User> getAllApplicants() {
        return userDao.getAllApplicants();
    }

    public List<User> getAllEmployers() {
        return userDao.getAllEmployers();
    }

    public Optional<User> getUserById(Long id) {
        return userDao.getUserById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    public void register(RegisterDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setAge(dto.getAge());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setAvatar("default-avatar.png");
        user.setAccountType(dto.getAccountType());
        userDao.save(user);
    }
}