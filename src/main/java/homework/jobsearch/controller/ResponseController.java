package homework.jobsearch.controller;

import homework.jobsearch.service.MessageService;
import homework.jobsearch.service.RespondedApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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
    public String responses(@PathVariable Long vacancyId, Authentication authentication, Model model) {
        model.addAttribute("responses", respondedApplicantService.getResponsesByVacancyId(vacancyId));
        addAuthAttributes(authentication, model);
        return "responses";
    }

    @GetMapping("/chat/{responseId}")
    public String chat(@PathVariable Long responseId, Authentication authentication, Model model) {
        model.addAttribute("messages", messageService.getMessagesByResponseId(responseId));
        model.addAttribute("responseId", responseId);
        addAuthAttributes(authentication, model);
        return "chat";
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