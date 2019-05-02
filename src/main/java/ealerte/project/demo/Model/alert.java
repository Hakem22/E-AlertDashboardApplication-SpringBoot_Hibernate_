package ealerte.project.demo.Model;

import org.springframework.core.style.ToStringCreator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;



@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate dateSend;
    @NotNull
    private LocalTime timeSend;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateRecieved;
    private LocalTime timeRecieved;
    private AlertState alertState;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private LocalisationA localisationA;

    @ManyToOne
    @JoinColumn
    private Acteur acteur;

    public Alert(){}


    public Alert( @NotNull LocalDate dateSend, @NotNull LocalTime timeSend , LocalisationA localisationA) {

        this.dateSend = dateSend;
        this.timeSend = timeSend;
        this.localisationA=localisationA;
        //this.localisationA.setAlert(this);
    }

    public LocalisationA getLocalisationA() {
        return localisationA;
    }

    public void setLocalisationA(LocalisationA localisationA) {
        this.localisationA = localisationA;
    }

    public LocalDate getDateSend() {
        return dateSend;
    }

    public LocalTime getTimeSend() {
        return timeSend;
    }

    public LocalDate getDateRecieved() {
        return dateRecieved;
    }

    public LocalTime getTimeRecieved() {
        return timeRecieved;
    }

    public AlertState getAlertState() {
        return alertState;
    }

    public void setDateSend(LocalDate dateSend) {
        this.dateSend = dateSend;
    }

    public void setTimeSend(LocalTime timeSend) {
        this.timeSend = timeSend;
    }

    public void setDateRecieved(LocalDate dateRecieved) {
        this.dateRecieved = dateRecieved;
    }

    public void setTimeRecieved(LocalTime timeRecieved) {
        this.timeRecieved = timeRecieved;
    }

    public void setAlertState(AlertState alertState) {
        this.alertState = alertState;
    }

    public Long getId() {
        return id;
    }

    public Acteur getActeur() {
        return acteur;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setActeur(Acteur acteur) {
        this.acteur = acteur;
    }
}


