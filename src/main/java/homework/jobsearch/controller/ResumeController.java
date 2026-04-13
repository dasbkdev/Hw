package homework.jobsearch.controller;

import homework.jobsearch.dto.ResumeDto;
import homework.jobsearch.model.Resume;
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
    public String resumes(@RequestParam(required = false) Long categoryId,
                          Authentication authentication, Model model) {
        if (categoryId == null) {
            model.addAttribute("resumes", resumeService.getAllActiveResumes());
        } else {
            model.addAttribute("resumes", resumeService.getResumesByCategory(categoryId));
        }
        model.addAttribute("categories", categoryService.getAllCategories());
        addAuthAttributes(authentication, model);
        return "resumes";
    }

    @GetMapping("/resumes/{id}")
    public String resumeDetails(@PathVariable Long id, Authentication authentication, Model model) {
        model.addAttribute("resume", resumeService.getResumeById(id).orElse(null));
        addAuthAttributes(authentication, model);
        return "resume-details";
    }

    @GetMapping("/my-resumes")
    public String myResumes(Authentication authentication, Model model) {
        User user = userService.getUserByEmail(authentication.getName()).orElse(null);
        model.addAttribute("resumes", user == null ? java.util.List.of() : resumeService.getResumesByApplicantId(user.getId()));
        addAuthAttributes(authentication, model);
        return "my-resumes";
    }

    @GetMapping("/resumes/create")
    public String createResumeForm(Authentication authentication, Model model) {
        model.addAttribute("resumeDto", new ResumeDto());
        model.addAttribute("categories", categoryService.getAllCategories());
        addAuthAttributes(authentication, model);
        return "create-resume";
    }

    @PostMapping("/resumes/create")
    public String createResume(@Valid ResumeDto dto,
                               BindingResult bindingResult,
                               Authentication authentication,
                               Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            addAuthAttributes(authentication, model);
            return "create-resume";
        }

        User user = userService.getUserByEmail(authentication.getName()).orElse(null);
        if (user != null) {
            resumeService.save(dto, user.getId());
        }

        return "redirect:/my-resumes";
    }


    @GetMapping("/resumes/{id}/edit")
    public String editResumeForm(@PathVariable Long id, Authentication authentication, Model model) {
        Resume resume = resumeService.getResumeById(id).orElse(null);
        if (resume == null) {
            return "redirect:/my-resumes";
        }

        ResumeDto dto = new ResumeDto();
        dto.setName(resume.getName());
        dto.setCategoryId(resume.getCategoryId());
        dto.setSalary(resume.getSalary());

        model.addAttribute("resumeDto", dto);
        model.addAttribute("resumeId", id);
        model.addAttribute("categories", categoryService.getAllCategories());
        addAuthAttributes(authentication, model);
        return "edit-resume";
    }

    @PostMapping("/resumes/{id}/edit")
    public String editResume(@PathVariable Long id,
                             @Valid ResumeDto dto,
                             BindingResult bindingResult,
                             Authentication authentication,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("resumeId", id);
            model.addAttribute("categories", categoryService.getAllCategories());
            addAuthAttributes(authentication, model);
            return "edit-resume";
        }

        resumeService.update(id, dto);
        return "redirect:/my-resumes";
    }

    private void addAuthAttributes(Authentication authentication, Model model) {
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
        model.addAttribute("isAuthenticated", isAuthenticated);
        if (isAuthenticated) {
            String role = authentication.getAuthorities().iterator().next().getAuthority();
            model.addAttribute("isEmployer", "EMPLOYER".equals(role));
            model.addAttribute("isApplicant", "APPLICANT".equals(role));
        } else {
            model.addAttribute("isEmployer", false);
            model.addAttribute("isApplicant", false);
        }
    }
}