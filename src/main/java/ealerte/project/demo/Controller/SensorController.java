package ealerte.project.demo.Controller;

import ealerte.project.demo.Repository.SensorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SensorController {
    private SensorRepository sensorRepository;

    public SensorController(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @RequestMapping("/sensors")
    public String getSensors(Model model){
        model.addAttribute("sensors", sensorRepository.findAll());
        return "sensors";
    }

    @GetMapping("/sensors/{sensorId}")
    public ModelAndView showSensor(@PathVariable("sensorId") long sensorId){
        ModelAndView mav=new ModelAndView();
        mav.addObject(this.sensorRepository.findById(sensorId));
        return mav;
    }
}
