package ealerte.project.demo.Controller;

import ealerte.project.demo.Model.*;
import ealerte.project.demo.Repository.ActeurRepository;
import ealerte.project.demo.Repository.AlertSRepository;
import ealerte.project.demo.Repository.InterventionRepository;
import ealerte.project.demo.Repository.SensorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AlertSController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private AlertSRepository alertSRepository;
    private InterventionRepository interventionRepository;
    private SensorRepository sensorRepository;
    private ActeurRepository acteurRepository;

    public AlertSController(AlertSRepository alertSRepository, InterventionRepository interventionRepository, SensorRepository sensorRepository, ActeurRepository acteurRepository) {
        this.alertSRepository = alertSRepository;
        this.interventionRepository = interventionRepository;
        this.sensorRepository = sensorRepository;
        this.acteurRepository = acteurRepository;
    }

    @GetMapping("/alertsensor")
    public List<AlertS> getAlertsSensor() {
        return (List<AlertS>) alertSRepository.findAll();
    }


    @GetMapping("/alertsensor/post")
    @ResponseBody
    public String postAlert(@RequestParam(required = false) String id, @RequestParam(required = false) String reference ,@RequestParam(required = false) String al,@RequestParam(required = false) String log,@RequestParam(required = false) String state,@RequestParam(required = false) String type,@RequestParam(required = false) String value ) {
        System.out.println("reference "+reference+ ",altitude "+al+" ,longitude "+log+", state "+state+" ,type "+type+" ,value "+value
        );

        AlertS alertS= new AlertS(LocalDate.now(), LocalTime.now(), Double.valueOf(value),Double.valueOf(al),Double.valueOf(log));

        Optional<Sensor> sensor= sensorRepository.findById(Long.valueOf(id));
        if(sensor.isPresent()) {
            alertS.setSensor(sensor.get());
            alertSRepository.save(alertS);
            return "okey";

        }
        return "false";
    }

    @PostMapping("/alertsensor")
    public void addAlertS(@RequestBody AlertS alertS){
        alertSRepository.save(alertS);
    }

    @Cacheable(value = "alerts", key = "#id", unless = "#result.followers < 1200")
    @GetMapping("/alertsensor/{id}")
    public AlertS retrieveAlertSensor(@PathVariable long id) {
        Optional<AlertS> alertS = alertSRepository.findById(id);
       /* if (!admin.isPresent())
            throw new AdminNotFoundException("id-" + id);*/
        LOG.info("Getting user with ID {}.", id);
        return alertS.get();
    }

    @CacheEvict(value = "alerts", allEntries=true)
    @DeleteMapping("/alertsensor/{id}")
    public void deleteAlertSensor(@PathVariable long id) {
        alertSRepository.deleteById(id);
    }

    @CachePut(value = "alerts", key = "#alertS.id")
    @PutMapping("/alertsensor/{id}/{idActeur}")
    public ResponseEntity<Object> updateStateAlertS(@RequestBody AlertS alertS, @PathVariable long id, @PathVariable long idActeur) {

        Optional<AlertS> alertSOptional = alertSRepository.findById(id);
        if (!alertSOptional.isPresent())  return ResponseEntity.notFound().build();
        alertS.setId(id);
        alertS.setSensor(alertSOptional.get().getSensor());
        alertS.setActeur(acteurRepository.findById(idActeur).get());
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

    @GetMapping("/alertssensor/valid")
    public List<AlertS> getAllvalidAlerts() {
        return alertSRepository.findAlertSByAlertState(AlertState.VALIDE);
    }

    @GetMapping("/alertssensor/invalid")
    public List<AlertS> getAllinvalidAlerts() {
        return alertSRepository.findAlertSByAlertState(AlertState.INVALIDE);
    }


    @GetMapping("/statfulalertssensor")
    public List<AlertS> getAllstatfulAlerts() {
        List<AlertS> alerts= alertSRepository.findAlertSByAlertState(AlertState.VALIDE);
        alerts.addAll(alertSRepository.findAlertSByAlertState(AlertState.INVALIDE));
        return alerts;
    }

    @GetMapping("/statlessalertssensor")
    public List<AlertS> getAllstatlessAlerts() {
        List<AlertS> alerts = new ArrayList<>();
        for (AlertS a: alertSRepository.findAll()) {
            if(a.getAlertState()==null) alerts.add(a);
        }

      return alerts;
    }

    @GetMapping("/alertsensor/type/{type}")
    public List<AlertS> getAlertsByType(@PathVariable String type) {
        SensorType sensortype;
        if(type.equals("wl")) sensortype= SensorType.WATER_LEVEL ;
        else sensortype= SensorType.FOREST_FIRE;

        List<AlertS> alerts= new ArrayList<>();
        for (AlertS a: alertSRepository.findAll()){
            if(a.getSensor().getType().equals(sensortype)) alerts.add(a);
        }
        return  alerts;
    }



    @GetMapping("/alertsensor/state/{state}")
    public List<AlertS> getAlertsByState(@PathVariable String state) {

        if(state.equals("valid"))  return alertSRepository.findAlertSByAlertState(AlertState.VALIDE);
        else return alertSRepository.findAlertSByAlertState(AlertState.INVALIDE);
    }

}
