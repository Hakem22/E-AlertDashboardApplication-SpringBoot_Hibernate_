package ealerte.project.demo.Repository;

import ealerte.project.demo.Model.Intervention;
import ealerte.project.demo.Model.InterventionKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface InterventionRepository  extends JpaRepository<Intervention, Long> {
    Page<Intervention> findByAlertId(Long id, Pageable pageable);
    Optional<Intervention> findByIdAndAlertId(Long id, Long alertId);
    Page<Intervention> findByInterventionUnitId(Long id, Pageable pageable);
    Optional<Intervention> findByIdAndInterventionUnitId(InterventionKey key, Long interventionUnitId);
}
