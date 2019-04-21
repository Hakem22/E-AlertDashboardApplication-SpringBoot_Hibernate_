package ealerte.project.demo.Repository;

import ealerte.project.demo.Model.Citizen;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Collection;

public interface CitizenRepository extends CrudRepository<Citizen, Long> {


    @Query("SELECT DISTINCT citizen FROM Citizen citizen left join fetch citizen.alerts WHERE citizen.phoneNumber LIKE :phoneNumber%")
    @Transactional
    Collection<Citizen> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);




}
