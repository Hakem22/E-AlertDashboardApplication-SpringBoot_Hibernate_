package ealerte.project.demo.Repository;

import ealerte.project.demo.Model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
