package ealerte.project.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String StreetName;
    private  String StreetNumber;
    private String postalcode;
    private String region;

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
}
