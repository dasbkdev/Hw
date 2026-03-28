package homework.jobsearch.controller;

import homework.jobsearch.dto.ResumeDto;
import homework.jobsearch.service.CategoryService;
import homework.jobsearch.service.ResumeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/my-resumes")
    public String myResumes(Model model) {
        model.addAttribute("resumes", resumeService.getResumesByApplicantId(2L));
        return "my-resumes";
    }

    @GetMapping("/resumes/create")
    public String createResumeForm(Model model) {
        model.addAttribute("resumeDto", new ResumeDto());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "create-resume";
    }

    @PostMapping("/resumes/create")
    public String createResume(@Valid ResumeDto dto,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "create-resume";
        }
        resumeService.save(dto, 2L);
        return "redirect:/my-resumes";
    }
}