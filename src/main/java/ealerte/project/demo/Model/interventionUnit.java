package ealerte.project.demo.Model;

import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="interventionUnit")
public class InterventionUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @NotEmpty
    private InterventionType interventionType;
    @OneToOne(mappedBy = "interventionUnit", cascade = CascadeType.ALL)
    private Address address;

    @OneToOne(mappedBy = "interventionUnit", cascade = CascadeType.ALL)
    private LocalisationU localisationU;

    @OneToMany(mappedBy = "interventionUnit", cascade = CascadeType.ALL)
    private List<Intervention> interventions= new ArrayList<>();

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

    public List<Intervention> getInterventions() {
        return interventions;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setLocalisationU(LocalisationU localisationU) {
        this.localisationU = localisationU;
    }

    public void setInterventions(List<Intervention> interventions) {
        this.interventions = interventions;
    }

    @Override
    public String toString(){
        return new ToStringCreator(this)
                .append("Type", this.getInterventionType())
                .append("Address", this.getAddress().toString())
                .append("localisation", this.getLocalisationU().toString())
                .toString();
    }
}
