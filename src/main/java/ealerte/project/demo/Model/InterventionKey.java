package ealerte.project.demo.Model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class InterventionKey implements Serializable {
    private Long alertId;
    private Long interventionUnitId;

    public InterventionKey(){}
    public InterventionKey(Long alertId, Long interventionUnitId) {
        this.alertId = alertId;
        this.interventionUnitId = interventionUnitId;
    }

    public Long getAlertId() {
        return alertId;
    }

    public Long getInterventionUnitId() {
        return interventionUnitId;
    }

    public void setAlertId(Long alertId) {
        this.alertId = alertId;
    }

    public void setInterventionUnitId(Long interventionUnitId) {
        this.interventionUnitId = interventionUnitId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof InterventionKey)) return false;
        InterventionKey key = (InterventionKey) o;
        return key.getAlertId().equals(this.getAlertId()) && key.interventionUnitId.equals(this.getInterventionUnitId());
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.getAlertId(), this.interventionUnitId);
    }

}
