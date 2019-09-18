package ealerte.project.demo.Repository;

import ealerte.project.demo.Model.Acteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActeurRepository extends JpaRepository<Acteur, Long> {
    Acteur findActeurByUsername(String username);



}
