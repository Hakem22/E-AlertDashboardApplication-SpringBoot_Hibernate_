package ealerte.project.demo.Model;

import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name="Alert_Citizen")
public class AlertC extends Alert {


    @Enumerated(EnumType.STRING)
    private AlertType alertType;
    @NotEmpty
    private String description;
/*
    @OneToMany(mappedBy = "alertC", cascade =CascadeType.ALL)
    private Set<Media> medias;
*/

    @ManyToOne
    @JoinColumn
    private Citizen citizen;


/*
    @OneToMany(mappedBy = "alert")
    private List<Intervention> interventions=new ArrayList<>();
*/

    public AlertC(){super();}

    public AlertC(@NotNull LocalDate dateSend, @NotNull LocalTime timeSend,  AlertType alertType, @NotEmpty String description, @NotNull Double altitude,@NotNull Double longitude, Media... media) {
        super(dateSend, timeSend,altitude,longitude);
        this.alertType = alertType;
        this.description = description;
        //this.medias =  Stream.of(media).collect(Collectors.toSet());
       // this.medias.forEach(x -> x.setAlertC(this));



    }



    public AlertType getAlertType() {
        return alertType;
    }

    public String getDescription() {
        return description;
    }
/*
    public Set<Media> getMedias() {
        return medias;
    }

*/

    public Citizen getCitizen() {
        return citizen;
    }

/*
    public List<Intervention> getInterventions() {
        return interventions;
    }
*/


    public void setAlertType(AlertType alertType) {
        this.alertType = alertType;
    }

    public void setDescription(String description) {
        this.description = description;
    }
/*
    public void setMedias(Set<Media> medias) {
        this.medias = medias;
    }
*/

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }

/*
    public void setInterventions(List<Intervention> interventions) {
        this.interventions = interventions;
    }
    */

    @Override
    public String toString(){
        return new ToStringCreator(this)
                .append("type", this.getAlertType())
                .append("citizen", this.getCitizen().toString())
                .toString();

    }




}
