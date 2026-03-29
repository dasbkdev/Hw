package homework.jobsearch.controller;

import homework.jobsearch.model.User;
import homework.jobsearch.service.ResumeService;
import homework.jobsearch.service.UserService;
import homework.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final ResumeService resumeService;
    private final VacancyService vacancyService;

    @GetMapping("/profile")
    public String profile(Authentication authentication, Model model) {

        if (authentication == null) {
            return "redirect:/login";
        }

        String email = authentication.getName();
        User user = userService.getUserByEmail(email).orElse(null);

        model.addAttribute("user", user);

        if (user != null && "APPLICANT".equals(user.getAccountType())) {
            model.addAttribute("resumes",
                    resumeService.getResumesByApplicantId(user.getId()));
        }

        if (user != null && "EMPLOYER".equals(user.getAccountType())) {
            model.addAttribute("vacancies",
                    vacancyService.getVacanciesByAuthorId(user.getId()));
        }

        return "profile";
    }
}