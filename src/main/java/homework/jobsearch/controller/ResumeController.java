package homework.jobsearch.controller;

import homework.jobsearch.service.CategoryService;
import homework.jobsearch.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;
    private final CategoryService categoryService;

    @GetMapping("/resumes")
    public String resumes(@RequestParam(required = false) Long categoryId, Model model) {
        if (categoryId == null) {
            model.addAttribute("resumes", resumeService.getAllActiveResumes());
        } else {
            model.addAttribute("resumes", resumeService.getResumesByCategory(categoryId));
        }
        model.addAttribute("categories", categoryService.getAllCategories());
        return "resumes";
    }

    @GetMapping("/resumes/{id}")
    public String resumeDetails(@PathVariable Long id, Model model) {
        model.addAttribute("resume", resumeService.getResumeById(id).orElse(null));
        return "resume-details";
    }
}