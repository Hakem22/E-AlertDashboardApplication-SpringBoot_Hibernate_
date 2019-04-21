package ealerte.project.demo.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="admin")
public class Admin extends Acteur{


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "acteur", fetch = FetchType.LAZY)
    private List<Alert> alerts= new ArrayList<>();

    public List<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }
}
