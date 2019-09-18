package ealerte.project.demo.Controller;


import ealerte.project.demo.Model.InterventionUnit;
import ealerte.project.demo.Model.Localisation;
import ealerte.project.demo.Repository.InterventionUnitRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class NearestUnits {


    private InterventionUnitRepository interventionUnitRepository;

    public NearestUnits(InterventionUnitRepository interventionUnitRepository) {
        this.interventionUnitRepository = interventionUnitRepository;
    }


}
