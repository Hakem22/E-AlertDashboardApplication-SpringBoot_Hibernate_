package ealerte.project.demo.Repository;

import ealerte.project.demo.Model.Sensor;
import ealerte.project.demo.Model.SensorState;
import ealerte.project.demo.Model.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

public interface SensorRepository extends JpaRepository<Sensor,Long> {

 List<Sensor> findSensorsByType(SensorType type);

 //List<Sensor> findSensorsByState(SensorState state);
}


