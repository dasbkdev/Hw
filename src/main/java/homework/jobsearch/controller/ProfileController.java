package homework.jobsearch.controller;

import homework.jobsearch.dto.EditProfileDto;
import homework.jobsearch.model.User;
import homework.jobsearch.service.ResumeService;
import homework.jobsearch.service.UserService;
import homework.jobsearch.service.VacancyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/profile/edit")
    public String editProfileForm(Authentication authentication, Model model) {

        if (authentication == null) {
            return "redirect:/login";
        }

        User user = userService.getUserByEmail(authentication.getName()).orElse(null);
        if (user == null) {
            return "redirect:/profile";
        }

        EditProfileDto dto = new EditProfileDto();
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setAge(user.getAge());
        dto.setPhoneNumber(user.getPhoneNumber());

        model.addAttribute("editProfileDto", dto);
        return "edit-profile";
    }

    @PostMapping("/profile/edit")
    public String editProfile(@Valid EditProfileDto editProfileDto,
                              BindingResult bindingResult,
                              Authentication authentication) {

        if (bindingResult.hasErrors()) {
            return "edit-profile";
        }

        User user = userService.getUserByEmail(authentication.getName()).orElse(null);
        if (user != null) {
            userService.update(user.getId(), editProfileDto);
        }

        return "redirect:/profile";
    }
}