package ealerte.project.demo.Model;

import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="alertC")
public class AlertC extends Alert implements Comparable<AlertC>{


    @Enumerated(EnumType.STRING)
    @NotEmpty
    private AlertType alertType;
    @NotEmpty
    private String description;

    @OneToMany(mappedBy = "alertC", cascade =CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Media> medias= new ArrayList<>();
/*
    @ManyToOne
    @JoinColumn(name="acteur_id", nullable = false)
    private Acteur acteur;
*/
    @ManyToOne
    @JoinColumn(name = "citizen-id", nullable = false)
    private Citizen citizen;

    @OneToOne(mappedBy = "alert", cascade =CascadeType.ALL, fetch = FetchType.LAZY)
    private LocalisationA localisationA;

    @OneToMany(mappedBy = "alert")
    private List<Intervention> interventions=new ArrayList<>();



    public AlertType getAlertType() {
        return alertType;
    }

    public String getDescription() {
        return description;
    }

    public List<Media> getMedias() {
        return medias;
    }



    public Citizen getCitizen() {
        return citizen;
    }

    public LocalisationA getLocalisationA() {
        return localisationA;
    }

    public List<Intervention> getInterventions() {
        return interventions;
    }



    public void setAlertType(AlertType alertType) {
        this.alertType = alertType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMedias(List<Media> medias) {
        this.medias = medias;
    }


    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }

    public void setLocalisationA(LocalisationA localisationA) {
        this.localisationA = localisationA;
    }

    public void setInterventions(List<Intervention> interventions) {
        this.interventions = interventions;
    }

    @Override
    public String toString(){
        return new ToStringCreator(this)
                .append("type", this.getAlertType())
                .append("citizen", this.getCitizen().toString())
                .toString();

    }

    public int compareTo(AlertC alertc){
        if( this.getAlertType().equals(alertc.getAlertType())
                &&
                this.getLocalisationA().compareTo(alertc.getLocalisationA())==1)
            return 1;
        return 0;

    }


}
