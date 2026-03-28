package homework.jobsearch.controller;

import homework.jobsearch.dto.VacancyDto;
import homework.jobsearch.service.CategoryService;
import homework.jobsearch.service.VacancyService;
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
public class VacancyController {
    private final VacancyService vacancyService;
    private final CategoryService categoryService;

    @GetMapping("/vacancies")
    public String vacancies(@RequestParam(required = false) Long categoryId, Model model) {
        if (categoryId == null) {
            model.addAttribute("vacancies", vacancyService.getAllActiveVacancies());
        } else {
            model.addAttribute("vacancies", vacancyService.getVacanciesByCategory(categoryId));
        }
        model.addAttribute("categories", categoryService.getAllCategories());
        return "vacancies";
    }

    @GetMapping("/vacancies/{id}")
    public String vacancyDetails(@PathVariable Long id, Model model) {
        model.addAttribute("vacancy", vacancyService.getVacancyById(id).orElse(null));
        return "vacancy-details";
    }

    @GetMapping("/my-vacancies")
    public String myVacancies(Model model) {
        model.addAttribute("vacancies", vacancyService.getVacanciesByAuthorId(1L));
        return "my-vacancies";
    }

    @GetMapping("/vacancies/create")
    public String createVacancyForm(Model model) {
        model.addAttribute("vacancyDto", new VacancyDto());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "create-vacancy";
    }

    @PostMapping("/vacancies/create")
    public String createVacancy(@Valid VacancyDto dto,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "create-vacancy";
        }
        vacancyService.save(dto, 1L);
        return "redirect:/my-vacancies";
    }
}