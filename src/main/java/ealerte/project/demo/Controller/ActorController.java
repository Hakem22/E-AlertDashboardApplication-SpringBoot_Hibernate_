package ealerte.project.demo.Controller;

import ealerte.project.demo.Model.Acteur;
import ealerte.project.demo.Repository.ActeurRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ActorController {
    private ActeurRepository acteurRepository;

    public ActorController(ActeurRepository acteurRepository) {
        this.acteurRepository = acteurRepository;
    }


    @GetMapping("/actors")
    public List<Acteur> getActeurs() {
        return (List<Acteur>) acteurRepository.findAll();
    }



    @GetMapping("/actor/{username}")
    public Acteur retrieveAdmin(@PathVariable String username) {

       return  acteurRepository.findActeurByUsername(username);

    }



    @DeleteMapping("/actor/{id}")
    public void deleteActor(@PathVariable long id) {
        acteurRepository.deleteById(id);
    }

}

