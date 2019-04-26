package ealerte.project.demo.Model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="localisationA")
public class LocalisationA extends Localisation implements Comparable<Localisation> {

    @OneToOne(mappedBy = "localisationA")
    private Alert alert;

    public LocalisationA(){ super ();}

    public LocalisationA(@NotNull Double altitude, @NotNull Double longitude, @NotNull Double rayon) {
        super( altitude, longitude, rayon);
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    public Alert getAlert() {
        return alert;
    }

    public int compareTo(Localisation localisation){
        if(this.getAltitude()+this.getRayon()>localisation.getAltitude() && this.getLongitude()+this.getRayon()>localisation.getLongitude()) return 1;
        return 0;
    }


}
