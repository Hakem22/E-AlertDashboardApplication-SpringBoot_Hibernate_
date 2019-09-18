package ealerte.project.demo.Controller;

import ealerte.project.demo.Model.AlertC;
import ealerte.project.demo.Model.AlertState;
import ealerte.project.demo.Model.AlertType;
import ealerte.project.demo.Model.Citizen;
import ealerte.project.demo.Repository.AlertCRepository;
import ealerte.project.demo.Repository.CitizenRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CitizenController {

    private CitizenRepository citizenRepository;
    private AlertCRepository alertCRepository;

    public CitizenController(CitizenRepository citizenRepository, AlertCRepository alertCRepository) {
        this.citizenRepository = citizenRepository;
        this.alertCRepository = alertCRepository;
    }

    @GetMapping("/citizens")
    public List<Citizen> getCitizens() {
        return (List<Citizen>) citizenRepository.findAll();
    }

    @PostMapping("/citizen")
    public void addCitizen(@RequestBody Citizen citizen){
        citizenRepository.save(citizen);
    }

    @GetMapping("/citizen/{id}")
    public Citizen retrieveCitizen(@PathVariable long id) {
        Optional<Citizen> citizen = citizenRepository.findById(id);
       /* if (!admin.isPresent())
            throw new AdminNotFoundException("id-" + id);*/
        return citizen.get();
    }
    @DeleteMapping("/citizen/{id}")
    public void deleteCitizen(@PathVariable long id) {
        citizenRepository.deleteById(id);
    }

    @PutMapping("/citizen/{id}")
    public ResponseEntity<Object> updateAdmin(@RequestBody Citizen citizen, @PathVariable long id) {

        Optional<Citizen> citizenOptional = citizenRepository.findById(id);

        if (!citizenOptional.isPresent())  return ResponseEntity.notFound().build();

        citizen.setId(id);
        citizenRepository.save(citizen);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/citizen/{id}/alerts")
    public List<AlertC> getAllAlertsByCitizenId(@PathVariable (value = "id") Long id) {
        return alertCRepository.findByCitizenId(id);
    }

    @PostMapping("/citizen/{id}/alerts")
    public AlertC createAlerts(@PathVariable (value = "id") Long id, @Valid @RequestBody AlertC alertC) {
        return citizenRepository.findById(id).map(citizen -> {
            alertC.setCitizen(citizen);
            return alertCRepository.save(alertC);
        }).orElseThrow(() -> new ResourceNotFoundException("citizenId " + id + " not found"));
    }



    @DeleteMapping("/citizen/{id}/alerts/{alertId}")
    public ResponseEntity<?> deleteAlert(@PathVariable (value = "id") Long id,
                                         @PathVariable (value = "alertId") Long alertId) {
        return alertCRepository.findByIdAndCitizenId(alertId,id).map(alertC -> {
            alertCRepository.delete(alertC);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("alert not found with id " + alertId + " and postId " + id));
    }


    @GetMapping("/citizen/alertState/{state}")
    public List<Citizen> getCitizensByAlertsState(@PathVariable String state) {
        List<AlertC> alertsCitizen= new ArrayList<AlertC>();

        System.out.println("____________________________________________________________________"+state);
        if(state.equals("valide")) alertsCitizen=alertCRepository.findAlertCByAlertState(AlertState.VALIDE);
        if(state.equals("invalide")) alertsCitizen=alertCRepository.findAlertCByAlertState(AlertState.INVALIDE);

        List<Citizen> citizens=new ArrayList<Citizen>();

        for (AlertC alert: alertsCitizen) {
            Optional<Citizen> citizenOptional = citizenRepository.findById(alert.getCitizen().getId());
            citizens.add(citizenOptional.get());
        }
        System.out.println(citizens);
        return citizens;
    }

    @GetMapping("/citizen/alertType/{type}")
    public List<Citizen> getCitizensByAlertsType(@PathVariable String type) {
        List<AlertC> alertsCitizen= new ArrayList<AlertC>();

        if(type.equals("fire")) alertsCitizen=alertCRepository.findAlertCByAlertType(AlertType.FIRE);
        if(type.equals("flood")) alertsCitizen=alertCRepository.findAlertCByAlertType(AlertType.FLOOD);
        if(type.equals("accident")) alertsCitizen=alertCRepository.findAlertCByAlertType(AlertType.ACCIDENT);
        if(type.equals("earthquack")) alertsCitizen=alertCRepository.findAlertCByAlertType(AlertType.EARTHEQUAKE);
        if(type.equals("aggression")) alertsCitizen=alertCRepository.findAlertCByAlertType(AlertType.AGGRESSION);


        List<Citizen> citizens=new ArrayList<Citizen>();

        for (AlertC alert: alertsCitizen) {
            Optional<Citizen> citizenOptional = citizenRepository.findById(alert.getCitizen().getId());
            citizens.add(citizenOptional.get());
        }
        return citizens;
    }

}
