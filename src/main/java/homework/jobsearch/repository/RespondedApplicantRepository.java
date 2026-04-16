package homework.jobsearch.repository;

import homework.jobsearch.model.RespondedApplicant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespondedApplicantRepository extends JpaRepository<RespondedApplicant, Long> {

    List<RespondedApplicant> findByVacancyId(Long vacancyId);

    List<RespondedApplicant> findByResumeId(Long resumeId);
}