package ealerte.project.demo.Model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="intervention")
public class Intervention {

    @EmbeddedId
    private InterventionKey id;
    @NotNull
    private LocalDate dateStart;
    private LocalDate dateEnd;
    @NotNull
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private Long victims;
    private Long interveners;
    private String cause;

    @ManyToOne
    @MapsId("alertId")
    @JoinColumn(name="alertId")
    private  Alert alert;

    @ManyToOne
    @MapsId("interventionUnitId")
    @JoinColumn(name="interventionUnitId")
    private InterventionUnit interventionUnit;

    public Intervention(){}
    public Intervention(InterventionKey id, @NotNull LocalDate dateStart,  @NotNull LocalTime timeStart, Alert alert, InterventionUnit interventionUnit) {
        this.id = id;
        this.dateStart = dateStart;
        this.timeStart = timeStart;
        this.alert = alert;
        this.interventionUnit = interventionUnit;
    }

    public InterventionKey getId() {
        return id;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public LocalTime getTimeStart() {
        return timeStart;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public Long getVictims() {
        return victims;
    }

    public Long getInterveners() {
        return interveners;
    }

    public String getCause() {
        return cause;
    }

    public Alert getAlert() {
        return alert;
    }

    public InterventionUnit getInterventionUnit() {
        return interventionUnit;
    }

    public void setId(InterventionKey id) {
        this.id = id;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setTimeStart(LocalTime timeStart) {
        this.timeStart = timeStart;
    }

    public void setTimeEnd(LocalTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public void setVictims(Long victims) {
        this.victims = victims;
    }

    public void setInterveners(Long interveners) {
        this.interveners = interveners;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    public void setInterventionUnit(InterventionUnit interventionUnit) {
        this.interventionUnit = interventionUnit;
    }


}

