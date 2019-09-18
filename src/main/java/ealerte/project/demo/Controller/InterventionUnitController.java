package ealerte.project.demo.Controller;

import ealerte.project.demo.Model.Intervention;
import ealerte.project.demo.Model.InterventionKey;
import ealerte.project.demo.Model.InterventionType;
import ealerte.project.demo.Model.InterventionUnit;
import ealerte.project.demo.Repository.InterventionRepository;
import ealerte.project.demo.Repository.InterventionUnitRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class InterventionUnitController  {
    private InterventionUnitRepository interventionUnitRepository;
    private InterventionRepository interventionRepository;

    public InterventionUnitController(InterventionUnitRepository interventionUnitRepository, InterventionRepository interventionRepository) {
        this.interventionUnitRepository = interventionUnitRepository;
        this.interventionRepository= interventionRepository;
    }

    @GetMapping("/units")
    public List<InterventionUnit> getUnits() {
        return (List<InterventionUnit>) interventionUnitRepository.findAll();
    }
    @PostMapping("/unit")
    public void addAUnit(@RequestBody InterventionUnit unit){
        System.out.println(unit);interventionUnitRepository.save(unit);
    }

    @GetMapping("/unit/{id}")
    public InterventionUnit retrieveUnit(@PathVariable long id) {
        Optional<InterventionUnit> unit = interventionUnitRepository.findById(id);
       /* if (!admin.isPresent())
            throw new AdminNotFoundException("id-" + id);*/
        return unit.get();
    }
    @DeleteMapping("/unit/{id}")
    public void deleteUnit(@PathVariable long id) {
        interventionUnitRepository.deleteById(id);
    }

    @PutMapping("/unit/{id}")
    public ResponseEntity<Object> updateAdmin(@RequestBody InterventionUnit unit, @PathVariable long id) {

        Optional<InterventionUnit> unitOptional = interventionUnitRepository.findById(id);

        if (!unitOptional.isPresent())  return ResponseEntity.notFound().build();

        unit.setId(id);
        interventionUnitRepository.save(unit);
        return ResponseEntity.noContent().build();
    }

    /* Manipulating Intervention */

    @GetMapping("/unit/{id}/interventions")
    public Page<Intervention> getAllInterventionsByUnitId(@PathVariable (value = "id") Long id, Pageable pageable) {
        return interventionRepository.findByInterventionUnitId(id,pageable);
    }

    @PostMapping("/unit/{id}/interventions")
    public Intervention createIntervention(@PathVariable (value = "id") Long id, @Valid @RequestBody Intervention intervention) {
        return interventionUnitRepository.findById(id).map(interventionUnit -> {
            intervention.setInterventionUnit(interventionUnit);
            return interventionRepository.save(intervention);
        }).orElseThrow(() -> new ResourceNotFoundException("unitId " + id + " not found"));
    }



    @DeleteMapping("/unit/{id}/intervention/{interventionId}")
    public ResponseEntity<?> deleteIntervention(@PathVariable (value = "id") Long id,
                                                @PathVariable (value = "interventionId") InterventionKey interventionId) {
        return interventionRepository.findByIdAndInterventionUnitId(interventionId,id).map(intervention -> {
            interventionRepository.delete(intervention);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("intervention not found with id " + interventionId + " and postId " + id));
    }


    @GetMapping("/unit/unitType/{type}")
    public List<InterventionUnit> getAllInterventionsUnitsByType(@PathVariable String type) {
        System.out.println("____________________________"+type);
        if(type.equals("hospitality")) {
            System.out.println( interventionUnitRepository.findInterventionUnitsByInterventionType(InterventionType.HOSPITALITY));
            return interventionUnitRepository.findInterventionUnitsByInterventionType(InterventionType.HOSPITALITY);}
        if(type.equals("policedirection")) return interventionUnitRepository.findInterventionUnitsByInterventionType(InterventionType.POLICE_DIRECTION);
        if(type.equals("civilprotection")) return interventionUnitRepository.findInterventionUnitsByInterventionType(InterventionType.CIVIL_PROTECTION);
        else return interventionUnitRepository.findInterventionUnitsByInterventionType(InterventionType.GENDARMERIE);
    }



    @GetMapping("/nearestunits/coords")
    @ResponseBody
    public List<InterventionUnit> streamData(@RequestParam(required = false) Double al,@RequestParam Double log,@RequestParam Double rayon) {
        List<InterventionUnit> units=new ArrayList<>();
        System.out.println(rayon);
        for (InterventionUnit unit: interventionUnitRepository.findAll()) {
            if(Math.abs(unit.getAltitude()-al)<rayon && Math.abs(unit.getLongitude()-log)<rayon) {
                units.add(unit);
                System.out.println(unit.getAltitude()-al);
            }
        }
        return units;
    }

    @GetMapping("/unit/unitmobile/{type}")
    public List<InterventionUnit> getAllInterventionsUnitsByTypeForMobile (@PathVariable String type) {
        System.out.println("____________________________"+type);
        if(type.equals("HOSPITALITY")) {
            System.out.println( interventionUnitRepository.findInterventionUnitsByInterventionType(InterventionType.HOSPITALITY));
            return interventionUnitRepository.findInterventionUnitsByInterventionType(InterventionType.HOSPITALITY);}
        else if(type.equals("POLICE_DIRECTION")) return interventionUnitRepository.findInterventionUnitsByInterventionType(InterventionType.POLICE_DIRECTION);
        else if(type.equals("CIVIL_PROTECTION")) return interventionUnitRepository.findInterventionUnitsByInterventionType(InterventionType.CIVIL_PROTECTION);
        else if(type.equals("GENDARMERIE")) return interventionUnitRepository.findInterventionUnitsByInterventionType(InterventionType.GENDARMERIE);
        else return null;
    }

}
