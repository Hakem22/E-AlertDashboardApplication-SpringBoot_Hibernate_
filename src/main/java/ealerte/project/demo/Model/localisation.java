package ealerte.project.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Localisation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Float altitude;
    private Float longitude;
    private Float rayon;

    public Long getId() {
        return id;
    }

    public Float getAltitude() {
        return altitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public Float getRayon() {
        return rayon;
    }

    public void setId(Long id) {
        this.id = id;
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
}
