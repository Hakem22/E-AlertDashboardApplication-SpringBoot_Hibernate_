package ealerte.project.demo.Repository;

import ealerte.project.demo.Model.Alert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    Page<Alert> findByActeurId(Long id, Pageable pageable);
    Optional<Alert> findByIdAndActeurId(Long id, Long acteurId);
}
