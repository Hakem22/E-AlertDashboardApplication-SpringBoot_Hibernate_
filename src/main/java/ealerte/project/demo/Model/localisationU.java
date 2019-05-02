package ealerte.project.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="localisationU")
public class LocalisationU extends Localisation {

/*
    @OneToOne(mappedBy = "localisationU")
    private InterventionUnit interventionUnit;
*/
    public LocalisationU() { super ();}



    public LocalisationU(@NotNull Double altitude, @NotNull Double longitude, @NotNull Double rayon) {
        super(altitude, longitude, rayon);
    }
/*
    public InterventionUnit getInterventionUnit() {
        return interventionUnit;
    }

    public void setInterventionUnit(InterventionUnit interventionUnit) {
        this.interventionUnit = interventionUnit;
    }
*/


}
