package ealerte.project.demo.Repository;

import ealerte.project.demo.Model.LocalisationA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface LocalisationARepository extends JpaRepository<LocalisationA, Long> {
}
