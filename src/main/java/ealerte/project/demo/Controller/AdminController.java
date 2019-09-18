package ealerte.project.demo.Controller;

import ealerte.project.demo.Model.*;
import ealerte.project.demo.Repository.*;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController  {
    private AdminRepository adminRepository;
    private AlertCRepository alertCRepository;
    private AlertSRepository alertSRepository;
    private AlertRepository alertRepository;
    private AgentRepository agentRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AdminController(AdminRepository adminRepository, AlertSRepository alertSRepository,
                           AlertCRepository alertCRepository, AlertRepository alertRepository,
                           AgentRepository agentRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.adminRepository = adminRepository;
        this.alertCRepository = alertCRepository;
        this.alertSRepository = alertSRepository;
        this.alertRepository = alertRepository;
        this.agentRepository = agentRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

    @GetMapping("/admins")
    public List<Admin> getAdmins() {
        return (List<Admin>) adminRepository.findAll();
    }



    @GetMapping("/admins/{username}")
    public Admin retrieveAdmin(@PathVariable String username) {

        Optional<Admin> admin = adminRepository.findAdminByUsername(username);
        if (!admin.isPresent())
            throw new RuntimeException("id not found f l'admin ta3ek" + username);
        return admin.get();
    }
    @DeleteMapping("/admins/{id}")
    public void deleteAdmin(@PathVariable long id) {
        adminRepository.deleteById(id);
    }

    @PutMapping("/admins/{id}")
    public ResponseEntity<Object> updateAdmin(@RequestBody Admin admin, @PathVariable long id) {

        Optional<Admin> adminOptional = adminRepository.findById(id);

        if (!adminOptional.isPresent())  return ResponseEntity.notFound().build();

        admin.setId(id);
        admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
        adminRepository.save(admin);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/admin")
    public void addAdmin(@RequestBody Admin admin){
        admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
        adminRepository.save(admin);
    }


    /* Manipulating Alerts */

    @GetMapping("/actor/{id}/alerts")
    public List<Alert> getAllAlertsByActorId(@PathVariable (value = "id") Long id) {
        System.out.println(alertRepository.findAllByActeurId(id));
        return alertRepository.findAllByActeurId(id);
    }

    @GetMapping("/actor/{id}/alertscitizen")
    public List<AlertC> getAllAlertcitizenByActorId(@PathVariable (value = "id") Long id) {
        System.out.println(alertCRepository.findAllByActeurId(id));
        return alertCRepository.findAllByActeurId(id);
    }

    @GetMapping("/actor/{id}/alertsensor")
    public List<AlertS> getAllAlertsensorByActorId(@PathVariable (value = "id") Long id) {
        System.out.println(alertSRepository.findAllByActeurId(id));
        return alertSRepository.findAllByActeurId(id);
    }

    @PostMapping("/admin/{id}/alerts")
    public Alert createAlert(@PathVariable (value = "id") Long id, @Valid @RequestBody Alert alert) {
        return adminRepository.findById(id).map(admin -> {
            alert.setActeur(admin);
            return alertRepository.save(alert);
        }).orElseThrow(() -> new ResourceNotFoundException("adminId " + id + " not found"));
    }



    @DeleteMapping("/admin/{id}/alert/{alertId}")
    public ResponseEntity<?> deleteAlert(@PathVariable (value = "id") Long id,
                                         @PathVariable (value = "MediaId") Long alertId) {
        return alertRepository.findByIdAndActeurId(alertId,id).map(alert -> {
            alertRepository.delete(alert);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("alert not found with id " + alertId + " and postId " + id));
    }


}



