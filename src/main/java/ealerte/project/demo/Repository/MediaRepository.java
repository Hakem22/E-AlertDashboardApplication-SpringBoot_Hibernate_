package ealerte.project.demo.Repository;

import ealerte.project.demo.Model.Media;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MediaRepository extends JpaRepository<Media,Long> {
    Page<Media> findByAlertCId(Long id, Pageable pageable);
    Optional<Media> findByIdAndAlertCId(Long id, Long alertId);
}
