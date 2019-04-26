package ealerte.project.demo.Repository;

import ealerte.project.demo.Model.LocalisationU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface LocalisationURepository extends JpaRepository<LocalisationU,Long> {
}
