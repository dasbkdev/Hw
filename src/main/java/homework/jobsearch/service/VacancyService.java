package homework.jobsearch.service;

import homework.jobsearch.dao.VacancyDao;
import homework.jobsearch.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}