package ealerte.project.demo.Repository;

import ealerte.project.demo.Model.AlertC;
import ealerte.project.demo.Model.AlertState;
import ealerte.project.demo.Model.AlertType;
import ealerte.project.demo.Model.Citizen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AlertCRepository extends JpaRepository<AlertC, Long> {
    List<AlertC> findByCitizenId(Long id);
    Optional<AlertC> findByIdAndCitizenId(Long id, Long citizenId);
    List<AlertC> findAlertCByAlertState(AlertState state);
    List<AlertC> findAlertCByAlertType(AlertType alertType);
    List<AlertC> findAllByActeurId(Long id);




}
