package homework.jobsearch.controller;

import homework.jobsearch.service.CategoryService;
import homework.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final VacancyService vacancyService;
    private final CategoryService categoryService;

    @GetMapping("/")
    public String index(Authentication authentication, Model model) {
        model.addAttribute("vacancies", vacancyService.getAllActiveVacancies());
        model.addAttribute("categories", categoryService.getAllCategories());
        addAuthAttributes(authentication, model);
        return "index";
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

