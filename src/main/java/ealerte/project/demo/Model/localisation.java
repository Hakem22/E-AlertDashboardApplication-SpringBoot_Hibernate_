package ealerte.project.demo.Model;

import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Localisation {


    @Id
    private long id;
    @NotEmpty
    private Float altitude;
    @NotEmpty
    private Float longitude;
    @NotEmpty
    private Float rayon;



    public Float getAltitude() {
        return altitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public Float getRayon() {
        return rayon;
    }


    public void setAltitude(Float altitude) {
        this.altitude = altitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public void setRayon(Float rayon) {
        this.rayon = rayon;
    }

    public String toString(){
        return new ToStringCreator(this)
                .append("altitude", this.getAltitude())
                .append("longitude", this.getLongitude())
                .toString();
    }
}
