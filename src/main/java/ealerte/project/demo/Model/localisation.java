package ealerte.project.demo.Model;

import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Localisation implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Double altitude;
    @NotNull
    private Double longitude;
    @NotNull
    private Double rayon;


    public Localisation(){}
    public Localisation( @NotNull Double altitude, @NotNull Double longitude, @NotNull Double rayon) {
        this.altitude = altitude;
        this.longitude = longitude;
        this.rayon = rayon;
    }

    public Double getAltitude() {
        return altitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getRayon() {
        return rayon;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setRayon(Double rayon) {
        this.rayon = rayon;
    }

    public String toString(){
        return new ToStringCreator(this)
                .append("altitude", this.getAltitude())
                .append("longitude", this.getLongitude())
                .toString();
    }
}
