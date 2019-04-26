package ealerte.project.demo.Repository;

import ealerte.project.demo.Model.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InterventionRepository  extends JpaRepository<Intervention, Long> {
}
