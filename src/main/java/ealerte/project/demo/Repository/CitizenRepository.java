package ealerte.project.demo.Repository;

import ealerte.project.demo.Model.Citizen;
import org.springframework.data.repository.CrudRepository;

public interface CitizenRepository extends CrudRepository<Citizen, Long> {
}
