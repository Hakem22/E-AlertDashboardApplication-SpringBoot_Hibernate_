package ealerte.project.demo.Controller;

import ealerte.project.demo.Model.AlertS;
import ealerte.project.demo.Model.Intervention;
import ealerte.project.demo.Repository.AlertSRepository;
import ealerte.project.demo.Repository.InterventionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AlertSController {
    private AlertSRepository alertSRepository;
    private InterventionRepository interventionRepository;

    public AlertSController(AlertSRepository alertSRepository, InterventionRepository interventionRepository) {
        this.alertSRepository = alertSRepository;
        this.interventionRepository = interventionRepository;
    }

    @GetMapping("/alertssensor")
    public List<AlertS> getAlertsSensor() {
        return (List<AlertS>) alertSRepository.findAll();
    }

    @PostMapping("/alertsensor")
    public void addAlertS(@RequestBody AlertS alertS){
        alertSRepository.save(alertS);
    }

    @GetMapping("/alertsensor/{id}")
    public AlertS retrieveAlertSensor(@PathVariable long id) {
        Optional<AlertS> alertS = alertSRepository.findById(id);
       /* if (!admin.isPresent())
            throw new AdminNotFoundException("id-" + id);*/
        return alertS.get();
    }
    @DeleteMapping("/alertsensor/{id}")
    public void deleteAlertSensor(@PathVariable long id) {
        alertSRepository.deleteById(id);
    }

    @PutMapping("/alertsensor/{id}")
    public ResponseEntity<Object> updateAdmin(@RequestBody AlertS alertS, @PathVariable long id) {

        Optional<AlertS> alertSOptional = alertSRepository.findById(id);
        if (!alertSOptional.isPresent())  return ResponseEntity.notFound().build();
        alertS.setId(id);
        alertSRepository.save(alertS);
        return ResponseEntity.noContent().build();
    }

    /* Manipulating Intervention */

    @GetMapping("/alertsensor/{id}/interventions")
    public Page<Intervention> getAllInterventionsByAlertId(@PathVariable (value = "id") Long id, Pageable pageable) {
        return interventionRepository.findByAlertId(id,pageable);
    }

    @PostMapping("/alertsensor/{id}/interventions")
    public Intervention createIntervention(@PathVariable (value = "id") Long id, @Valid @RequestBody Intervention intervention) {
        return alertSRepository.findById(id).map(alert -> {
            intervention.setAlert(alert);
            return interventionRepository.save(intervention);
        }).orElseThrow(() -> new ResourceNotFoundException("alertId " + id + " not found"));
    }



    @DeleteMapping("/alertsensor/{id}/intervention/{interventionId}")
    public ResponseEntity<?> deleteIntervention(@PathVariable (value = "id") Long id,
                                                @PathVariable (value = "interventionId") Long interventionId) {
        return interventionRepository.findByIdAndAlertId(interventionId,id).map(intervention -> {
            interventionRepository.delete(intervention);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("media not found with id " + interventionId + " and postId " + id));
    }


}
