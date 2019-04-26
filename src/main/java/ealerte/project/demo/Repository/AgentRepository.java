package ealerte.project.demo.Repository;

import ealerte.project.demo.Model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AgentRepository extends JpaRepository<Agent,Long> {
}
