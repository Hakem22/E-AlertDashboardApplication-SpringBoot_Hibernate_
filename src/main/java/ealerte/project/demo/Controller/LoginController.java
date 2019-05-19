package ealerte.project.demo.Controller;

import ealerte.project.demo.Model.Acteur;
import ealerte.project.demo.Model.Admin;
import ealerte.project.demo.Repository.ActeurRepository;
import ealerte.project.demo.Repository.AdminRepository;
import ealerte.project.demo.Repository.AgentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    private ActeurRepository acteurRepository;

    public LoginController(ActeurRepository acteurRepository) {
        this.acteurRepository=acteurRepository;
    }

    @GetMapping("/auth/{username}")
    public Acteur login(@PathVariable String username) {
        Acteur acteur = acteurRepository.findActeurByUsername(username);
        return  acteur;
    }


}
