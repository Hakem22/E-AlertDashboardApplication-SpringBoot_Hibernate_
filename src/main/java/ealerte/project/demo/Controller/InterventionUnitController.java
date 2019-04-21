package ealerte.project.demo.Controller;

import ealerte.project.demo.Repository.InterventionUnitRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InterventionUnitController  {
    private InterventionUnitRepository interventionUnitRepository;

    @RequestMapping("/interventionUnits")
    public String getInterventionUnits(Model model){
        model.addAttribute("interventionUnits", interventionUnitRepository.findAll());
        return "interventionUnits";
    }

}
