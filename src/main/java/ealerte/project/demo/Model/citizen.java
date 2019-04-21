package ealerte.project.demo.Model;

import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Citizen")
public class Citizen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Digits(fraction = 0, integer = 10)
    private String phoneNumber;

    private String firstName;

    private String fastName;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "citizen")
    private List<AlertC> alerts= new ArrayList<>();


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

    public List<AlertC> getAlerts() {
        return alerts;
    }

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

    public void setAlerts(List<AlertC> alerts) {
        this.alerts = alerts;
    }

    public void addAlertC(AlertC alertC){
        this.alerts.add(alertC);
    }
    @Override
    public String toString(){
        return new ToStringCreator(this)
                .append("id", this.getId())
                .append("PhoneNumber", this.getPhoneNumber()).toString();
    }
}
