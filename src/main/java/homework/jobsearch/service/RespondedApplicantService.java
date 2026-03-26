package homework.jobsearch.service;

import homework.jobsearch.dao.RespondedApplicantDao;
import homework.jobsearch.model.RespondedApplicant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RespondedApplicantService {
    private final RespondedApplicantDao respondedApplicantDao;

    public List<RespondedApplicant> getResponsesByVacancyId(Long vacancyId) {
        return respondedApplicantDao.getResponsesByVacancyId(vacancyId);
    }

    public List<RespondedApplicant> getResponsesByResumeId(Long resumeId) {
        return respondedApplicantDao.getResponsesByResumeId(resumeId);
    }
}