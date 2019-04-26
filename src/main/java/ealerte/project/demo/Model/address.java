package ealerte.project.demo.Model;

import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name="address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String StreetName;
    @NotEmpty
    private String StreetNumber;
    @NotEmpty
    private String postalcode;
    @NotEmpty
    private String region;
    @OneToOne(mappedBy = "address")
    private InterventionUnit interventionUnit;

    public Address(){}
    public Address( @NotEmpty String streetName, @NotEmpty String streetNumber, @NotEmpty String postalcode, @NotEmpty String region) {
        StreetName = streetName;
        StreetNumber = streetNumber;
        this.postalcode = postalcode;
        this.region = region;
    }

    public Long getId() {
        return id;
    }

    public String getStreetName() {
        return StreetName;
    }

    public String getStreetNumber() {
        return StreetNumber;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public String getRegion() {
        return region;
    }

    public InterventionUnit getInterventionUnit() {
        return interventionUnit;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStreetName(String streetName) {
        StreetName = streetName;
    }

    public void setStreetNumber(String streetNumber) {
        StreetNumber = streetNumber;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setInterventionUnit(InterventionUnit interventionUnit) {
        this.interventionUnit = interventionUnit;
    }

    @Override
    public String toString(){
        return new ToStringCreator(this)
                .append("streetName", this.getStreetName())
                .append("streetNumber", this.getStreetNumber())
                .append("region", this.getRegion())
                .append("postalCode", this.getPostalcode()).toString();
    }

}
