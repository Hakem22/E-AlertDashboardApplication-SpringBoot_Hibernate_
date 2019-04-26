package ealerte.project.demo.Repository;

import ealerte.project.demo.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
