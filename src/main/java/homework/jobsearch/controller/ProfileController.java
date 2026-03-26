package homework.jobsearch.controller;

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
    public String profile(Model model) {
        model.addAttribute("user", userService.getUserById(2L).orElse(null));
        model.addAttribute("resumes", resumeService.getResumesByApplicantId(2L));
        return "profile";
    }
}