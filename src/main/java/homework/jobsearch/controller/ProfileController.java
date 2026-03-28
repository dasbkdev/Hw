package homework.jobsearch.controller;

import homework.jobsearch.model.User;
import homework.jobsearch.service.ResumeService;
import homework.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ProfileController {
    private final UserService userService;
    private final ResumeService resumeService;

    @GetMapping("/profile")
    public String profile(String email, Model model) {
        User user = userService.getUserByEmail(email).orElse(null);
        model.addAttribute("user", user);

        if (user != null && "APPLICANT".equals(user.getAccountType())) {
            model.addAttribute("resumes", resumeService.getResumesByApplicantId(user.getId()));
        }

        return "profile";
    }
}