package ealerte.project.demo.Model;

import javax.persistence.*;

@Entity
@Table(name="localisationA")
public class LocalisationA extends Localisation implements Comparable<Localisation> {

    @OneToOne
    @MapsId
    private Alert alert;



    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    public int compareTo(Localisation localisation){
        if(this.getAltitude()+this.getRayon()>localisation.getAltitude() && this.getLongitude()+this.getRayon()>localisation.getLongitude()) return 1;
        return 0;
    }


}
