package ealerte.project.demo.Controller;

import ealerte.project.demo.Repository.CitizenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


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

    /**
     * Custom handler for displaying a citizen.
     *
     * @param citizenId the ID of the citizen to display
     * @return a ModelMap with the model attributes for the view
     */
    @GetMapping("/citizens/{citizenId}")
    public ModelAndView showCitizen(@PathVariable("citizenId") long citizenId){
        ModelAndView mav=new ModelAndView();
        mav.addObject(this.citizenRepository.findById(citizenId));
        return mav;
    }

}
