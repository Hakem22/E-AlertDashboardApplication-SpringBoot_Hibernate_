package ealerte.project.demo.Model;

import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="alertS")
public class AlertS extends  Alert implements  Comparable<AlertS>{


    @NotEmpty
    private Float Value;
/*
    @ManyToOne
    @JoinColumn(name="acteur_id", nullable = false)
    private Acteur acteur;
*/
    @ManyToOne
    @JoinColumn(name = "sensor-id", nullable = false)
    private Sensor sensor;

    @OneToOne(mappedBy = "alert", cascade =CascadeType.ALL, fetch = FetchType.LAZY)
    private LocalisationA localisationA;

    @OneToMany(mappedBy = "alert")
    private List<Intervention> interventions=new ArrayList<>();



    public Float getValue() {
        return Value;
    }



    public Sensor getSensor() {
        return sensor;
    }

    public LocalisationA getLocalisationA() {
        return localisationA;
    }

    public List<Intervention> getInterventions() {
        return interventions;
    }



    public void setValue(Float value) {
        Value = value;
    }



    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public void setLocalisationA(LocalisationA localisationA) {
        this.localisationA = localisationA;
    }

    @Override
    public String toString(){
        return new ToStringCreator(this)
                .append("value", this.getValue())
                .append("sensor", this.getSensor().toString())
                .toString();
    }

    public void setInterventions(List<Intervention> interventions) {
        this.interventions = interventions;
    }

    public int compareTo(AlertS alerts){
        if( this.getSensor().getType().equals(alerts.getSensor().getType())
                &&
                this.getLocalisationA().compareTo(alerts.getLocalisationA())==1)
            return 1;
        return 0;

    }
}
