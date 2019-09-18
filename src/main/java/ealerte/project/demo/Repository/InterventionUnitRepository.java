package ealerte.project.demo.Repository;

import ealerte.project.demo.Model.InterventionType;
import ealerte.project.demo.Model.InterventionUnit;
import ealerte.project.demo.Model.Localisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface   InterventionUnitRepository extends JpaRepository<InterventionUnit, Long> {
    List<InterventionUnit> findInterventionUnitsByInterventionType(InterventionType type);

}
