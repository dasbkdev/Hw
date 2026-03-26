package homework.jobsearch.service;
import homework.jobsearch.dao.ResumeDao;
import homework.jobsearch.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResumeService {
    private final ResumeDao resumeDao;

    public List<Resume> getAllActiveResumes() {
        return resumeDao.getAllActiveResumes();
    }

    public List<Resume> getResumesByCategory(Long categoryId) {
        return resumeDao.getResumesByCategory(categoryId);
    }

    public List<Resume> getResumesByApplicantId(Long applicantId) {
        return resumeDao.getResumesByApplicantId(applicantId);
    }

    public Optional<Resume> getResumeById(Long id) {
        return resumeDao.getResumeById(id);
    }
}