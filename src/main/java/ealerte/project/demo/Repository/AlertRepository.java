package ealerte.project.demo.Repository;

import ealerte.project.demo.Model.Acteur;
import ealerte.project.demo.Model.Alert;
import ealerte.project.demo.Model.AlertState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findAllByActeurId(Long id);
    Optional<Alert> findByIdAndActeurId(Long id, Long acteurId);
    List<Alert> findAlertByAlertState(AlertState state);

}
