package ealerte.project.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String streetname;
    @NotEmpty
    private String streetnumber;
    @NotEmpty
    private String postalcode;
    @NotEmpty
    private String region;
    /*
    @OneToOne(mappedBy = "address")
    private InterventionUnit interventionUnit;*/

    public Address(){}
    public Address( @NotEmpty String streetname, @NotEmpty String streetnumber, @NotEmpty String postalcode, @NotEmpty String region) {
        this.streetname = streetname;
        this.streetnumber = streetnumber;
        this.postalcode = postalcode;
        this.region = region;
    }

    public Long getId() {
        return id;
    }



    public String getPostalcode() {
        return postalcode;
    }

    public String getRegion() {
        return region;
    }
/*
    public InterventionUnit getInterventionUnit() {
        return interventionUnit;
    }
*/
    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetname() {
        return streetname;
    }

    public String getStreetnumber() {
        return streetnumber;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname;
    }

    public void setStreetnumber(String streetnumber) {
        this.streetnumber = streetnumber;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public void setRegion(String region) {
        this.region = region;
    }
/*
    public void setInterventionUnit(InterventionUnit interventionUnit) {
        this.interventionUnit = interventionUnit;
    }
*/
    @Override
    public String toString(){
        return new ToStringCreator(this)
                .append("streetName", this.getStreetname())
                .append("streetNumber", this.getStreetnumber())
                .append("region", this.getRegion())
                .append("postalCode", this.getPostalcode()).toString();
    }

}
