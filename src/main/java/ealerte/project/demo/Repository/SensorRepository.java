package ealerte.project.demo.Repository;

import ealerte.project.demo.Model.Sensor;
import org.springframework.data.repository.CrudRepository;

public interface SensorRepository extends CrudRepository<Sensor,Long> {
}
