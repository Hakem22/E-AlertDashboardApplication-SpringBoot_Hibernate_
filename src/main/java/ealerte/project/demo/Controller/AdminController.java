package ealerte.project.demo.Controller;

import ealerte.project.demo.Model.Admin;
import ealerte.project.demo.Model.Alert;
import ealerte.project.demo.Repository.AdminRepository;
import ealerte.project.demo.Repository.AlertCRepository;
import ealerte.project.demo.Repository.AlertRepository;
import ealerte.project.demo.Repository.AlertSRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    public AdminController(AdminRepository adminRepository, AlertSRepository alertSRepository,
                           AlertCRepository alertCRepository, AlertRepository alertRepository) {
        this.adminRepository = adminRepository;
        this.alertCRepository = alertCRepository;
        this.alertSRepository = alertSRepository;
        this.alertRepository = alertRepository;
    }

    @GetMapping("/admins")
    public List<Admin> getAdmins() {
        return (List<Admin>) adminRepository.findAll();
    }

    @GetMapping("/admins/{id}")
    public Admin retrieveAdmin(@PathVariable long id) {
        Optional<Admin> admin = adminRepository.findById(id);
       /* if (!admin.isPresent())
            throw new AdminNotFoundException("id-" + id);*/
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
        adminRepository.save(admin);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/admin")
    public void addAdmin(@RequestBody Admin admin){
        adminRepository.save(admin);
    }


    /* Manipulating Alerts */

    @GetMapping("/admin/{id}/alerts")
    public Page<Alert> getAllAlertsByAdminId(@PathVariable (value = "id") Long id, Pageable pageable) {
        return alertRepository.findByActeurId(id,pageable);
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



