package ealerte.project.demo.Controller;

import ealerte.project.demo.Repository.CitizenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CitizenController {

    private CitizenRepository citizenRepository;

    public CitizenController(CitizenRepository citizenRepository) {
        this.citizenRepository = citizenRepository;
    }

    @RequestMapping("/citizens")
    public String getCitizens(Model model) {

        model.addAttribute("citizens", citizenRepository.findAll());

        return "citizens";
    }
}
