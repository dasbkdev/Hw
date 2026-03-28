package homework.jobsearch.service;

import homework.jobsearch.dao.VacancyDao;
import homework.jobsearch.dto.VacancyDto;
import homework.jobsearch.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VacancyService {
    private final VacancyDao vacancyDao;

    public List<Vacancy> getAllActiveVacancies() {
        return vacancyDao.getAllActiveVacancies();
    }

    public List<Vacancy> getVacanciesByCategory(Long categoryId) {
        return vacancyDao.getVacanciesByCategory(categoryId);
    }

    public Optional<Vacancy> getVacancyById(Long id) {
        return vacancyDao.getVacancyById(id);
    }

    public List<Vacancy> getVacanciesByAuthorId(Long authorId) {
        return vacancyDao.getVacanciesByAuthorId(authorId);
    }

    public void save(VacancyDto dto, Long authorId) {
        Vacancy vacancy = new Vacancy();
        vacancy.setName(dto.getName());
        vacancy.setDescription(dto.getDescription());
        vacancy.setCategoryId(dto.getCategoryId());
        vacancy.setSalary(dto.getSalary());
        vacancy.setExpFrom(dto.getExpFrom());
        vacancy.setExpTo(dto.getExpTo());
        vacancy.setIsActive(dto.getIsActive());
        vacancy.setAuthorId(authorId);
        vacancy.setCreatedDate(LocalDateTime.now());
        vacancy.setUpdateTime(LocalDateTime.now());
        vacancyDao.save(vacancy);
    }
}