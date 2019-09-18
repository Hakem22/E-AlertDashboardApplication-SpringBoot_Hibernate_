package ealerte.project.demo.Repository;

import ealerte.project.demo.Model.AlertS;
import ealerte.project.demo.Model.AlertState;
import ealerte.project.demo.Model.AlertType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlertSRepository extends JpaRepository<AlertS, Long> {
    List<AlertS> findBySensorId(Long id);
    Optional<AlertS> findByIdAndSensorId(Long id, Long sensorId);
    List<AlertS> findAlertSByAlertState(AlertState alertState);
    List<AlertS> findAllByActeurId(Long id);

}

