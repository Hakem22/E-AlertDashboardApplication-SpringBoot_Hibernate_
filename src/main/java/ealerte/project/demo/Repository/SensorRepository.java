package ealerte.project.demo.Repository;

import ealerte.project.demo.Model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Collection;

public interface SensorRepository extends JpaRepository<Sensor,Long> {
/*
    @Query("SELECT sensor from Sensor sensor left join fetch sensor.alertsS WHERE  sensor.reference LIKE  :reference%")
    @Transactional
    Collection<Sensor> findByReference(@Param("reference") String reference);
    */
}


