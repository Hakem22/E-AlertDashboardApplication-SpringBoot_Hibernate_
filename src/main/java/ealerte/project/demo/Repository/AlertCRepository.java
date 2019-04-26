package ealerte.project.demo.Repository;

import ealerte.project.demo.Model.AlertC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AlertCRepository extends JpaRepository<AlertC, Long> {
}
