package homework.jobsearch.repository;

import homework.jobsearch.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    List<Resume> findByIsActiveTrueOrderByUpdateTimeDesc();

    List<Resume> findByCategoryIdAndIsActiveTrueOrderByUpdateTimeDesc(Long categoryId);

    List<Resume> findByApplicantIdOrderByUpdateTimeDesc(Long applicantId);
}