package ealerte.project.demo.Model;

import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Alert_Sensor")
public class AlertS extends  Alert {


    @NotNull
    private Double Value;


    @ManyToOne
    @JoinColumn(nullable = false)
    private Sensor sensor;

/*
    @OneToMany(mappedBy = "alert")
    private List<Intervention> interventions=new ArrayList<>();
*/
    public AlertS(){super();}


    public AlertS( @NotNull LocalDate dateSend, @NotNull LocalTime timeSend, @NotNull Double value, @NotNull Double altitude,@NotNull Double longitude) {
        super( dateSend, timeSend,altitude,longitude );
        Value = value;

    }

    public Double getValue() {
        return Value;
    }



    public Sensor getSensor() {
        return sensor;
    }

/*
    public List<Intervention> getInterventions() {
        return interventions;
    }

*/

    public void setValue(Double value) {
        Value = value;
    }



    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }


    @Override
    public String toString(){
        return new ToStringCreator(this)
                .append("value", this.getValue())
                .append("sensor", this.getSensor().toString())
                .toString();
    }
/*
    public void setInterventions(List<Intervention> interventions) {
        this.interventions = interventions;
    }
*/

}
