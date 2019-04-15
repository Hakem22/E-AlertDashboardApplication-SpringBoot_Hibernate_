package ealerte.project.demo.Model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class AlertC extends Alert{

    @Enumerated(EnumType.STRING)
    private AlertType AlertType;
    private String Description;

    public AlertType getAlertType() {
        return AlertType;
    }

    public String getDescription() {
        return Description;
    }

    public void setAlertType(AlertType alertType) {
        AlertType = alertType;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
