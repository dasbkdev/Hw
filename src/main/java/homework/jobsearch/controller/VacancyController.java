package homework.jobsearch.controller;

import homework.jobsearch.service.CategoryService;
import homework.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}