package ealerte.project.demo.Controller;

import ealerte.project.demo.Model.Alert;
import ealerte.project.demo.Model.AlertState;
import ealerte.project.demo.Repository.AlertRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AlertController {

    private AlertRepository alertRepository;

    public AlertController(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    @GetMapping("/alerts/state/{state}")
    public List<Alert> getAlertsByState(@PathVariable String state) {

      if(state.equals("valid"))  return alertRepository.findAlertByAlertState(AlertState.VALIDE);
      else return alertRepository.findAlertByAlertState(AlertState.INVALIDE);
    }




    @PutMapping("/alert/{id}")
    public ResponseEntity<Object> updateActor(@RequestBody Alert alert, @PathVariable long id) {

        Optional<Alert> alertSOptional = alertRepository.findById(id);
        if (!alertSOptional.isPresent())  return ResponseEntity.notFound().build();
        alert.setId(id);
        System.out.println(id);
        System.out.println(alert);
        alertRepository.save(alert);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/alerts/{id}")
    public Alert getAlertsByid(@PathVariable long id) {
        return alertRepository.findById(id).get();
    }


}
