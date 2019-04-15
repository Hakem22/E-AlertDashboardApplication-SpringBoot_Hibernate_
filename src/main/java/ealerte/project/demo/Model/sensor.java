package ealerte.project.demo.Model;

import javax.persistence.*;

@Entity
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reference;
    @Enumerated(EnumType.STRING)
    private SensorState State;
    @Enumerated(EnumType.STRING)
    private SensorType type;
    private Float altitude;
    private Float logitude;

    public Long getId() {
        return id;
    }

    public String getReference() {
        return reference;
    }

    public SensorState getState() {
        return State;
    }

    public SensorType getType() {
        return type;
    }

    public Float getAltitude() {
        return altitude;
    }

    public Float getLogitude() {
        return logitude;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setState(SensorState state) {
        State = state;
    }

    public void setType(SensorType type) {
        this.type = type;
    }

    public void setAltitude(Float altitude) {
        this.altitude = altitude;
    }

    public void setLogitude(Float logitude) {
        this.logitude = logitude;
    }
}
