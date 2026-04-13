package homework.jobsearch.service;

import homework.jobsearch.dao.ResumeDao;
import homework.jobsearch.dto.ResumeDto;
import homework.jobsearch.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public void save(ResumeDto dto, Long applicantId) {
        Resume resume = new Resume();
        resume.setApplicantId(applicantId);
        resume.setName(dto.getName());
        resume.setCategoryId(dto.getCategoryId());
        resume.setSalary(dto.getSalary());
        resume.setIsActive(true);
        resume.setCreatedDate(LocalDateTime.now());
        resume.setUpdateTime(LocalDateTime.now());
        resumeDao.save(resume);
    }


    public void update(Long id, ResumeDto dto) {
        resumeDao.update(id, dto.getName(), dto.getCategoryId(), dto.getSalary());
    }
}