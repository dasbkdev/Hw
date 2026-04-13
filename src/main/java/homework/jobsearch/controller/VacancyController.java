package homework.jobsearch.controller;

import homework.jobsearch.dto.VacancyDto;
import homework.jobsearch.model.User;
import homework.jobsearch.model.Vacancy;
import homework.jobsearch.service.CategoryService;
import homework.jobsearch.service.UserService;
import homework.jobsearch.service.VacancyService;
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
public class VacancyController {
    private final VacancyService vacancyService;
    private final CategoryService categoryService;
    private final UserService userService;

    @GetMapping("/vacancies")
    public String vacancies(@RequestParam(required = false) Long categoryId,
                            Authentication authentication, Model model) {
        if (categoryId == null) {
            model.addAttribute("vacancies", vacancyService.getAllActiveVacancies());
        } else {
            model.addAttribute("vacancies", vacancyService.getVacanciesByCategory(categoryId));
        }
        model.addAttribute("categories", categoryService.getAllCategories());
        addAuthAttributes(authentication, model);
        return "vacancies";
    }

    @GetMapping("/vacancies/{id}")
    public String vacancyDetails(@PathVariable Long id, Authentication authentication, Model model) {
        model.addAttribute("vacancy", vacancyService.getVacancyById(id).orElse(null));
        addAuthAttributes(authentication, model);
        return "vacancy-details";
    }

    @GetMapping("/my-vacancies")
    public String myVacancies(Authentication authentication, Model model) {
        User user = userService.getUserByEmail(authentication.getName()).orElse(null);
        model.addAttribute("vacancies", user == null ? java.util.List.of() : vacancyService.getVacanciesByAuthorId(user.getId()));
        addAuthAttributes(authentication, model);
        return "my-vacancies";
    }

    @GetMapping("/vacancies/create")
    public String createVacancyForm(Authentication authentication, Model model) {
        model.addAttribute("vacancyDto", new VacancyDto());
        model.addAttribute("categories", categoryService.getAllCategories());
        addAuthAttributes(authentication, model);
        return "create-vacancy";
    }

    @PostMapping("/vacancies/create")
    public String createVacancy(@Valid VacancyDto dto,
                                BindingResult bindingResult,
                                Authentication authentication,
                                Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            addAuthAttributes(authentication, model);
            return "create-vacancy";
        }

        User user = userService.getUserByEmail(authentication.getName()).orElse(null);
        if (user != null) {
            vacancyService.save(dto, user.getId());
        }

        return "redirect:/my-vacancies";
    }


    @GetMapping("/vacancies/{id}/edit")
    public String editVacancyForm(@PathVariable Long id, Authentication authentication, Model model) {
        Vacancy vacancy = vacancyService.getVacancyById(id).orElse(null);
        if (vacancy == null) {
            return "redirect:/my-vacancies";
        }

        VacancyDto dto = new VacancyDto();
        dto.setName(vacancy.getName());
        dto.setDescription(vacancy.getDescription());
        dto.setCategoryId(vacancy.getCategoryId());
        dto.setSalary(vacancy.getSalary());
        dto.setExpFrom(vacancy.getExpFrom());
        dto.setExpTo(vacancy.getExpTo());

        model.addAttribute("vacancyDto", dto);
        model.addAttribute("vacancyId", id);
        model.addAttribute("categories", categoryService.getAllCategories());
        addAuthAttributes(authentication, model);
        return "edit-vacancy";
    }

    @PostMapping("/vacancies/{id}/edit")
    public String editVacancy(@PathVariable Long id,
                              @Valid VacancyDto dto,
                              BindingResult bindingResult,
                              Authentication authentication,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("vacancyId", id);
            model.addAttribute("categories", categoryService.getAllCategories());
            addAuthAttributes(authentication, model);
            return "edit-vacancy";
        }

        vacancyService.update(id, dto);
        return "redirect:/my-vacancies";
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