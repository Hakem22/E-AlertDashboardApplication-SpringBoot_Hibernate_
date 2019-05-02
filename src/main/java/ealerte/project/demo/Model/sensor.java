package ealerte.project.demo.Model;

import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name="sensor")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @NotEmpty
    private String reference;
    @Enumerated(EnumType.STRING)
    private SensorState State;
    @Enumerated(EnumType.STRING)
    private SensorType type;
    @NotNull
    private Double altitude;
    @NotNull
    private Double longitude;
    /*
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sensor")
    private Set<AlertS> alertsS;
*/
    public Sensor(){}
    public Sensor(@NotEmpty String reference, SensorState state, SensorType type, @NotNull Double altitude, @NotNull Double longitude) {
        this.reference = reference;
        State = state;
        this.type = type;
        this.altitude = altitude;
        this.longitude = longitude;
       // this.alertsS =  Stream.of(alertsS).collect(Collectors.toSet());
        //this.alertsS.forEach(x -> x.setSensor(this));
    }

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

    public Double getAltitude() {
        return altitude;
    }

    public Double getLongitude() {
        return longitude;
    }
/*
    public Set<AlertS> getAlertsS() {
        return alertsS;
    }
*/
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

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
/*
    public void setAlertsS(Set<AlertS> alertsS) {
        this.alertsS = alertsS;
    }
*/
  //  public void addAlertS(AlertS alertS){ this.alertsS.add(alertS); alertS.setSensor(this);}

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
