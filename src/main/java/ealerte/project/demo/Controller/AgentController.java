package ealerte.project.demo.Controller;

import ealerte.project.demo.Repository.AgentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AgentController {
    private AgentRepository agentRepository;

    @RequestMapping("/agents")
    public String getAgents(Model model){
        model.addAttribute("agents", agentRepository.findAll());
        return "agents";
    }

    @GetMapping("/agent/{agentId}")
    public ModelAndView showAgent(@PathVariable("agentId") long agentId){
        ModelAndView mav=new ModelAndView();
        mav.addObject(this.agentRepository.findById(agentId));
        return mav;
    }
}
