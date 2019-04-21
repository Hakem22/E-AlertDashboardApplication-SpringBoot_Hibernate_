package ealerte.project.demo.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @NotNull
    private String URI;

    @ManyToOne
    @JoinColumn(name="alert_id", nullable = false)
    private AlertC alertC;


    public Long getId() {
        return id;
    }

    public String getURI() {
        return URI;
    }

    public AlertC getAlertC() {
        return alertC;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }

    public void setAlertC(AlertC alertC) {
        this.alertC = alertC;
    }
}
