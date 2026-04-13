package homework.jobsearch.controller;

import homework.jobsearch.dto.RegisterDto;
import homework.jobsearch.service.UserService;
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
public class AuthController {
    private final UserService userService;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("registerDto", new RegisterDto());
        model.addAttribute("isAuthenticated", false);
        model.addAttribute("isEmployer", false);
        model.addAttribute("isApplicant", false);
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid RegisterDto registerDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("isAuthenticated", false);
            model.addAttribute("isEmployer", false);
            model.addAttribute("isApplicant", false);
            return "register";
        }
        userService.register(registerDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("isAuthenticated", false);
        model.addAttribute("isEmployer", false);
        model.addAttribute("isApplicant", false);
        return "login";
    }
}