package ealerte.project.demo.Repository;

import ealerte.project.demo.Model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CitizenRepository extends JpaRepository<Citizen, Long> {
Optional<Citizen> findCitizenByPhoneNumber(String phoneNumber);







}
