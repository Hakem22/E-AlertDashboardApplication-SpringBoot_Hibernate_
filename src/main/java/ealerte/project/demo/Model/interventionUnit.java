package ealerte.project.demo.Model;

import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="interventionUnit")
public class InterventionUnit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private InterventionType interventionType;

    @NotNull
    private Double altitude;
    @NotNull
    private Double longitude;
/*
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private LocalisationU localisationU;
*/
    //@OneToOne( cascade = CascadeType.ALL)
    //@JoinColumn(unique = true)
    private String address;
/*
    @OneToMany(mappedBy = "interventionUnit", cascade = CascadeType.ALL)
    private List<Intervention> interventions= new ArrayList<>();
*/
    public InterventionUnit(){}

    public InterventionUnit(InterventionType interventionType, Double altitude,Double longitude, String address) {
        this.interventionType = interventionType;
        this.altitude=altitude;
        this.longitude=longitude;
        this.address = address;
        //this.address.setInterventionUnit(this);
        //this.localisationU = localisationU;
        //this.localisationU.setInterventionUnit(this);
    }

    public Long getId() {
        return id;
    }

    public InterventionType getInterventionType() {
        return interventionType;
    }

    public String getAddress() {
        return address;
    }

    /*public LocalisationU getLocalisationU() {
        return localisationU;
    }
*/
    public void setId(Long id) {
        this.id = id;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setInterventionType(InterventionType interventionType) {
        this.interventionType = interventionType;
    }
/*
    public List<Intervention> getInterventions() {
        return interventions;
    }
*/
    public void setAddress(String address) {
        this.address = address;
    }

   /* public void setLocalisationU(LocalisationU localisationU) {
        this.localisationU = localisationU;
    }
    */
/*
    public void setInterventions(List<Intervention> interventions) {
        this.interventions = interventions;
    }
*/
    @Override
    public String toString(){
        return new ToStringCreator(this)
                .append("Type", this.getInterventionType())
                .append("Address", this.getAddress().toString())
                .append("altitude", this.getAltitude())
                .append("longitude",this.getLongitude())
                .toString();
    }
}
