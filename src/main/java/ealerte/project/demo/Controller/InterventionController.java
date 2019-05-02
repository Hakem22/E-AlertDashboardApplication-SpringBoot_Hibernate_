package ealerte.project.demo.Controller;

import ealerte.project.demo.Model.Intervention;
import ealerte.project.demo.Model.InterventionKey;
import ealerte.project.demo.Repository.InterventionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class InterventionController {
    private InterventionRepository interventionRepository;

    public InterventionController(InterventionRepository interventionRepository) {
        this.interventionRepository = interventionRepository;
    }

    @GetMapping("/interventions")
    public List<Intervention> getInterventions() {
        return (List<Intervention>) interventionRepository.findAll();
    }



    @PostMapping("/intervention")
    public void addIntervention(@RequestBody Intervention intervention){

        interventionRepository.save(intervention);
    }
    @GetMapping("/intervention/{id}")
    public Intervention retrieveIntervention(@PathVariable InterventionKey id) {
       // Optional<Intervention> intervention=interventionRepository.findById(id);
       /* if (!admin.isPresent())
            throw new AdminNotFoundException("id-" + id);*/
      //  return intervention.get();
        return new Intervention();
    }
    @DeleteMapping("/intervention/{id}")
    public void deleteIntervention(@PathVariable InterventionKey id) {
      //  interventionRepository.deleteById(id);
    }
/*
    @PutMapping("/intervention/{id}")
    public ResponseEntity<Object> updateIntervention(@RequestBody Intervention intervention, @PathVariable InterventionKey id) {

       /* Optional<Intervention> interventionOptional = interventionRepository.findById(id);

        if (!interventionOptional.isPresent())  return ResponseEntity.notFound().build();

        intervention.setId(id);
        interventionRepository.save(intervention);
        return ResponseEntity.noContent().build();

    }*/
}



