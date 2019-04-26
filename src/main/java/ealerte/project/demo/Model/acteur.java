package ealerte.project.demo.Model;

import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Acteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @Digits(fraction = 0, integer = 10)
    @NotEmpty
    private  String phone;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "acteur")
    private Set<Alert> alerts;

    public Acteur(){}

    public Acteur( @NotEmpty String firstName, @NotEmpty String lastName, @Digits(fraction = 0, integer = 10) @NotEmpty String phone, @NotEmpty String username, @NotEmpty String password, Alert... alerts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.alerts = Stream.of(alerts).collect(Collectors.toSet());
        this.alerts.forEach(x -> x.setActeur(this));
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString(){
        return new ToStringCreator(this)
                .append("lastName", this.getLastName())
                .append("firstNAme", this.getFirstName())
                .append("phone", this.getPhone()).toString();
    }
}
