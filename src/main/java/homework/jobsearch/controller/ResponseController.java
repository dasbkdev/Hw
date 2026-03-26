package homework.jobsearch.controller;

import homework.jobsearch.service.MessageService;
import homework.jobsearch.service.RespondedApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ResponseController {
    private final RespondedApplicantService respondedApplicantService;
    private final MessageService messageService;

    @GetMapping("/responses/{vacancyId}")
    public String responses(@PathVariable Long vacancyId, Model model) {
        model.addAttribute("responses", respondedApplicantService.getResponsesByVacancyId(vacancyId));
        return "responses";
    }

    @GetMapping("/chat/{responseId}")
    public String chat(@PathVariable Long responseId, Model model) {
        model.addAttribute("messages", messageService.getMessagesByResponseId(responseId));
        model.addAttribute("responseId", responseId);
        return "chat";
    }
}