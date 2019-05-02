package ealerte.project.demo.Repository;

import ealerte.project.demo.Model.AlertS;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlertSRepository extends JpaRepository<AlertS, Long> {
    Page<AlertS> findBySensorId(Long id, Pageable pageable);
    Optional<AlertS> findByIdAndSensorId(Long id, Long sensorId);

}

