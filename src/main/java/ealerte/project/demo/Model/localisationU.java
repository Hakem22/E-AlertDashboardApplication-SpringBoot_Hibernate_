package ealerte.project.demo.Model;

import javax.persistence.*;

@Entity
@Table(name="localisationU")
public class LocalisationU extends Localisation {


    @OneToOne
    @MapsId
    private InterventionUnit interventionUnit;



    public void setInterventionUnit(InterventionUnit interventionUnit) {
        this.interventionUnit = interventionUnit;
    }



}
