package ealerte.project.demo.Controller;

import ealerte.project.demo.Repository.AlertCRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AlertCController {
    private AlertCRepository alertCRepository;

    @RequestMapping("/alertsC")
    public String getAlertsC(Model model){
        model.addAttribute("alertsC",alertCRepository.findAll());
        return "alertsC";
    }
}
