package ealerte.project.demo.Repository;

import ealerte.project.demo.Model.AlertC;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AlertCRepository extends JpaRepository<AlertC, Long> {
    Page<AlertC> findByCitizenId(Long id, Pageable pageable);
    Optional<AlertC> findByIdAndCitizenId(Long id, Long citizenId);

}
