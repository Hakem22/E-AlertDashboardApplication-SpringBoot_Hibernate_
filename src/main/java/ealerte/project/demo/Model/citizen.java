package ealerte.project.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Citizen {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String PhoneNumber;
    private String FirstName;
    private String LastName;

    public Long getId() {
        return id;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
}
