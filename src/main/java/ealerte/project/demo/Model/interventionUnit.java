package ealerte.project.demo.Model;

import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private LocalisationU localisationU;

    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private Address address;
/*
    @OneToMany(mappedBy = "interventionUnit", cascade = CascadeType.ALL)
    private List<Intervention> interventions= new ArrayList<>();
*/
    public InterventionUnit(){}

    public InterventionUnit(InterventionType interventionType, LocalisationU localisationU, Address address) {
        this.interventionType = interventionType;
        this.address = address;
        //this.address.setInterventionUnit(this);
        this.localisationU = localisationU;
        //this.localisationU.setInterventionUnit(this);
    }

    public Long getId() {
        return id;
    }

    public InterventionType getInterventionType() {
        return interventionType;
    }

    public Address getAddress() {
        return address;
    }

    public LocalisationU getLocalisationU() {
        return localisationU;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setInterventionType(InterventionType interventionType) {
        this.interventionType = interventionType;
    }
/*
    public List<Intervention> getInterventions() {
        return interventions;
    }
*/
    public void setAddress(Address address) {
        this.address = address;
    }

    public void setLocalisationU(LocalisationU localisationU) {
        this.localisationU = localisationU;
    }
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
                .append("localisation", this.getLocalisationU().toString())
                .toString();
    }
}
