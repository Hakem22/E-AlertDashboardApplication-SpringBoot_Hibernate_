package ealerte.project.demo.Model;

import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name="Citizen")
public class Citizen {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Digits(fraction = 0, integer = 10)
    private String phoneNumber;

    private String firstName;

    private String fastName;
/*
    @OneToMany(cascade = CascadeType.ALL,  mappedBy = "citizen")
    private Set<AlertC> alertsC;
*/
    public Citizen(){}

    public Citizen(@NotEmpty @Digits(fraction = 0, integer = 10) String phoneNumber) {

        this.phoneNumber = phoneNumber;
      //  this.alertsC =  Stream.of(alertsC).collect(Collectors.toSet());
        //this.alertsC.forEach(x -> x.setCitizen(this));
    }

    public Citizen(@NotEmpty @Digits(fraction = 0, integer = 10) String phoneNumber, String firstName, String fastName) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.fastName = fastName;
    }

    public Long getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFastName() {
        return fastName;
    }
/*
    public Set<AlertC> getAlerts() {
        return alertsC;
    }
*/
    public void setId(Long id) {
        this.id = id;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setFastName(String fastName) {
        this.fastName = fastName;
    }
/*
    public void setAlerts(Set<AlertC> alertsC) {
        this.alertsC = alertsC;
    }

    public void addAlertC(AlertC alertC){
        this.alertsC.add(alertC);
    }
    */
    @Override
    public String toString(){
        return new ToStringCreator(this)
                .append("id", this.getId())
                .append("PhoneNumber", this.getPhoneNumber()).toString();
    }
}
