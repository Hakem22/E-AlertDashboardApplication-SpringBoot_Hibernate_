package ealerte.project.demo.Controller;

import ealerte.project.demo.Model.AlertC;
import ealerte.project.demo.Model.Intervention;
import ealerte.project.demo.Model.Media;
import ealerte.project.demo.Repository.AlertCRepository;
import ealerte.project.demo.Repository.InterventionRepository;
import ealerte.project.demo.Repository.MediaRepository;
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
public class AlertCController {
    private AlertCRepository alertCRepository;
    private MediaRepository mediaRepository;
    private InterventionRepository interventionRepository;

    public AlertCController(AlertCRepository alertCRepository, MediaRepository mediaRepository, InterventionRepository interventionRepository) {
        this.alertCRepository = alertCRepository;
        this.mediaRepository = mediaRepository;
        this.interventionRepository = interventionRepository;
    }

    @GetMapping("/alertscitizen")
    public List<AlertC> getAlertsCitizen() {
        return (List<AlertC>) alertCRepository.findAll();
    }
    @PostMapping("/alertcitizen")
    public void addAlertC(@RequestBody AlertC alertC){
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

    @PutMapping("/alertcitizen/{id}")
    public ResponseEntity<Object> updateAdmin(@RequestBody AlertC alertC, @PathVariable long id) {

        Optional<AlertC> alertCOptional = alertCRepository.findById(id);

        if (!alertCOptional.isPresent())  return ResponseEntity.notFound().build();

        alertC.setId(id);
        alertCRepository.save(alertC);
        return ResponseEntity.noContent().build();
    }

    /* Manipulating medias */
    @GetMapping("/alertcitizen/{id}/medias")
    public Page<Media> getAllMediasByAlertId(@PathVariable (value = "id") Long id, Pageable pageable) {
        return mediaRepository.findByAlertCId(id,pageable);
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

}
