package ealerte.project.demo.Model;

import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="sensor")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @NotNull
    private String reference;
    @Enumerated(EnumType.STRING)
    private SensorState State;
    @Enumerated(EnumType.STRING)
    private SensorType type;
    @NotNull
    private Float altitude;
    @NotNull
    private Float longitude;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sensor")
    private List<AlertS> alertsS = new ArrayList<>();

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

    public Float getLongitude() {
        return longitude;
    }

    public List<AlertS> getAlertsS() {
        return alertsS;
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

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public void setAlertsS(List<AlertS> alertsS) {
        this.alertsS = alertsS;
    }

    public void addAlertS(AlertS alertS){ this.alertsS.add(alertS);}

    @Override
    public String toString(){
        return new ToStringCreator(this)
                .append("reference", this.getReference())
                .append("state", this.getState() )
                .append("type", this.getType())
                .append("altitude", this.getAltitude())
                .append("logitude", this.getLongitude()).toString();

    }
}
