package ealerte.project.demo.Controller;

import ealerte.project.demo.Repository.SensorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
