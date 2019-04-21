package ealerte.project.demo.Controller;

import ealerte.project.demo.Repository.AlertSRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AlertSController {
    private AlertSRepository alertSRepository;

    @RequestMapping("/alertsS")
    public String getAlertsS(Model model){
        model.addAttribute("alertsS",alertSRepository.findAll());
        return "alertsS";
    }

}
