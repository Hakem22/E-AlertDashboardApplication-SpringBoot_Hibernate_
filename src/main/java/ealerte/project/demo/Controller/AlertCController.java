package ealerte.project.demo.Controller;

import ealerte.project.demo.Model.*;
import ealerte.project.demo.Repository.*;
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
public class AlertCController {
    private AlertCRepository alertCRepository;
    private MediaRepository mediaRepository;
    private InterventionRepository interventionRepository;
    private AlertRepository alertRepository;
    private CitizenRepository citizenRepository;
    private ActeurRepository acteurRepository;


    public AlertCController(AlertCRepository alertCRepository, MediaRepository mediaRepository, InterventionRepository interventionRepository, AlertRepository alertRepository, CitizenRepository citizenRepository, ActeurRepository acteurRepository) {
        this.alertCRepository = alertCRepository;
        this.mediaRepository = mediaRepository;
        this.interventionRepository = interventionRepository;
        this.alertRepository = alertRepository;
        this.citizenRepository = citizenRepository;
        this.acteurRepository = acteurRepository;
    }

    @GetMapping("/alertscitizen")
    public List<AlertC> getAlertsCitizen() {
        return (List<AlertC>) alertCRepository.findAll();
    }

    @GetMapping("/alerts")
    public List<Alert> getAlerts() {
        return (List<Alert>) alertRepository.findAll();
    }

    @PostMapping("/alertcitizen")
    public void addAlertC(@RequestBody AlertC alertC){
        System.out.println(alertC);
        AlertC alertC1=new AlertC(LocalDate.now(), LocalTime.now(),alertC.getAlertType(),alertC.getDescription(),alertC.getAltitude(),alertC.getLongitude());
        alertC1.setCitizen(alertC.getCitizen());
        citizenRepository.save(alertC.getCitizen());
        System.out.println(alertC.getCitizen());
        System.out.println(citizenRepository.findById((long) 5));
        alertCRepository.save(alertC);
    }

    @GetMapping("/alertcitizen/{id}")
    public AlertC retrieveAlertCitizen(@PathVariable long id) {
        Optional<AlertC> alertC = alertCRepository.findById(id);
       /* if (!admin.isPresent())
            throw new AdminNotFoundException("id-" + id);*/
        return alertC.get();
    }
    @DeleteMapping("/alertcitizen/{id}")
    public void deleteAlertCitizen(@PathVariable long id) {
        alertCRepository.deleteById(id);
    }
    @DeleteMapping("/alertall/{id}")
    public void deleteAlertitizen(@PathVariable long id) {
        alertRepository.deleteById(id);
    }

    @PutMapping("/alertcitizen/{id}/{idActeur}")
    public ResponseEntity<Object> updateStateAlertC(@RequestBody AlertC alertC, @PathVariable long id,@PathVariable long idActeur) {

        Optional<AlertC> alertCOptional = alertCRepository.findById(id);

        if (!alertCOptional.isPresent())  return ResponseEntity.notFound().build();
        alertC.setId(id);
        System.out.println(alertC);
        alertC.setCitizen(alertCOptional.get().getCitizen());
        alertC.setActeur(acteurRepository.findById(idActeur).get());
        alertCRepository.save(alertC);
        return ResponseEntity.noContent().build();
    }



    @PostMapping("/alertcitizen/{id}/medias")
    public Media createMedias(@PathVariable (value = "id") Long id, @Valid @RequestBody Media media) {
        return alertCRepository.findById(id).map(alert -> {
            media.setAlertC(alert);
            return mediaRepository.save(media);
        }).orElseThrow(() -> new ResourceNotFoundException("alertId " + id + " not found"));
    }



    @DeleteMapping("/alertcitizen/{id}/media/{mediaId}")
    public ResponseEntity<?> deleteMedia(@PathVariable (value = "id") Long id,
                                         @PathVariable (value = "MediaId") Long mediaId) {
        return mediaRepository.findByIdAndAlertCId(mediaId,id).map(media -> {
            mediaRepository.delete(media);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("media not found with id " + mediaId + " and postId " + id));
    }

    /* Manipulating Intervention */

    @GetMapping("/alertcitizen/{id}/interventions")
    public Page<Intervention> getAllInterventionsByAlertId(@PathVariable (value = "id") Long id, Pageable pageable) {
        return interventionRepository.findByAlertId(id,pageable);
    }

    @PostMapping("/alertcitizen/{id}/interventions")
    public Intervention createIntervention(@PathVariable (value = "id") Long id, @Valid @RequestBody Intervention intervention) {
        return alertCRepository.findById(id).map(alert -> {
            intervention.setAlert(alert);
            return interventionRepository.save(intervention);
        }).orElseThrow(() -> new ResourceNotFoundException("alertId " + id + " not found"));
    }



    @DeleteMapping("/alertcitizen/{id}/intervention/{interventionId}")
    public ResponseEntity<?> deleteIntervention(@PathVariable (value = "id") Long id,
                                         @PathVariable (value = "interventionId") Long interventionId) {
        return interventionRepository.findByIdAndAlertId(interventionId,id).map(intervention -> {
            interventionRepository.delete(intervention);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("media not found with id " + interventionId + " and postId " + id));
    }

    @GetMapping("/alertscitizen/valid")
    public List<AlertC> getAllvalidAlerts() {
        return alertCRepository.findAlertCByAlertState(AlertState.VALIDE);
    }

    @GetMapping("/alertscitizen/invalid")
    public List<AlertC> getAllinvalidAlerts() {
        return alertCRepository.findAlertCByAlertState(AlertState.INVALIDE);
    }

    @GetMapping("/alertscitizen/type/{type}")
    public List<AlertC> getAlertsByType(@PathVariable String type) {
        if(type.equals("acc")) return alertCRepository.findAlertCByAlertType(AlertType.ACCIDENT);
        if(type.equals("agr")) return alertCRepository.findAlertCByAlertType(AlertType.AGGRESSION);
        if(type.equals("etq")) return alertCRepository.findAlertCByAlertType(AlertType.EARTHEQUAKE);
        if(type.equals("flood")) return alertCRepository.findAlertCByAlertType(AlertType.FLOOD);
        else return  alertCRepository.findAlertCByAlertType(AlertType.FIRE);
    }

    @GetMapping("/statfulalertscitizen")
    public List<AlertC> getAllstatfulAlerts() {
        List<AlertC> alerts= alertCRepository.findAlertCByAlertState(AlertState.VALIDE);
        alerts.addAll(alertCRepository.findAlertCByAlertState(AlertState.INVALIDE));
        return alerts;
    }

    @GetMapping("/statlessalertscitizen")
    public List<AlertC> getAllstatlessAlerts() {
        List<AlertC> alerts = new ArrayList<>();
        for (AlertC a: alertCRepository.findAll()) {
            if(a.getAlertState()==null) alerts.add(a);
        }

        return alerts;
    }


    @GetMapping("/alertcitizen/state/{state}")
    public List<AlertC> getAlertsByState(@PathVariable String state) {

        if(state.equals("valid"))  return alertCRepository.findAlertCByAlertState(AlertState.VALIDE);
        else return alertCRepository.findAlertCByAlertState(AlertState.INVALIDE);
    }

    @PostMapping("/alertCitizenMobile/{description},{alertType},{altitude},{longitude},{phoneNumber},{firstName},{lastName}")
    public String alertMobile(@PathVariable String description,@PathVariable  String  alertType,@PathVariable String altitude,
                            @PathVariable String longitude,@PathVariable String phoneNumber, @PathVariable String firstName,@PathVariable  String lastName ){
        Optional<Citizen> citizen=citizenRepository.findCitizenByPhoneNumber(phoneNumber);
        Citizen citizen1;
        if(!citizen.isPresent()) {
            System.out.println("hhhhhhhhhhh");
            citizen1 = new Citizen(phoneNumber, firstName, lastName);
            citizenRepository.save(citizen1);

        }
        else {
            System.out.println("rrrrrr");
            citizen1=citizen.get();
        }

        AlertType alertType1;
        if(alertType.equals("FIRE")) alertType1=AlertType.FIRE;
        else if(alertType.equals("FLOOD")) alertType1=AlertType.FLOOD;
        else if(alertType.equals("ACCIDENT")) alertType1=AlertType.ACCIDENT;
        else if(alertType.equals("AGGRESSION")) alertType1=AlertType.AGGRESSION;
        else alertType1=AlertType.EARTHEQUAKE;

        AlertC alertC=new AlertC(LocalDate.now(), LocalTime.now(),alertType1,description,Double.valueOf(altitude),Double.valueOf(longitude));
        alertC.setCitizen(citizen1);
        System.out.println(citizen);
        System.out.println(alertC);
        alertCRepository.save(alertC);
        return String.valueOf(alertC.getId());



    }

    @GetMapping("/localtime")
    public LocalTime localtime(){
        return LocalTime.now();
    }




}
