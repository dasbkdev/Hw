package homework.jobsearch.controller;

import homework.jobsearch.service.CategoryService;
import homework.jobsearch.service.VacancyService;   
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final VacancyService vacancyService;
    private final CategoryService categoryService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("vacancies", vacancyService.getAllActiveVacancies());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "index";
    }
}