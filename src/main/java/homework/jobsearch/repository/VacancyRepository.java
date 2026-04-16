package homework.jobsearch.repository;

import homework.jobsearch.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    List<Vacancy> findByIsActiveTrueOrderByUpdateTimeDesc();

    List<Vacancy> findByCategoryIdAndIsActiveTrueOrderByUpdateTimeDesc(Long categoryId);

    List<Vacancy> findByAuthorIdOrderByUpdateTimeDesc(Long authorId);
}