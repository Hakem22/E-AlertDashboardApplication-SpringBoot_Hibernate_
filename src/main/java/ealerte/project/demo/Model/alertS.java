package ealerte.project.demo.Model;

import javax.persistence.Entity;

@Entity
public class AlertS extends  Alert{
    private Float Value;

    public Float getValue() {
        return Value;
    }

    public void setValue(Float value) {
        Value = value;
    }
}
