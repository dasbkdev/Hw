package homework.jobsearch.controller;

import homework.jobsearch.dto.ResumeDto;
import homework.jobsearch.model.User;
import homework.jobsearch.service.CategoryService;
import homework.jobsearch.service.ResumeService;
import homework.jobsearch.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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
    private final UserService userService;

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
    public String myResumes(Authentication authentication, Model model) {
        User user = userService.getUserByEmail(authentication.getName()).orElse(null);
        model.addAttribute("resumes", user == null ? java.util.List.of() : resumeService.getResumesByApplicantId(user.getId()));
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
                               Authentication authentication,
                               Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "create-resume";
        }

        User user = userService.getUserByEmail(authentication.getName()).orElse(null);
        if (user != null) {
            resumeService.save(dto, user.getId());
        }

        return "redirect:/my-resumes";
    }
}