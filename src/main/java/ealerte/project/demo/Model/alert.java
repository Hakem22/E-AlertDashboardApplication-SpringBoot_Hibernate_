package ealerte.project.demo.Model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Timer;

@Entity
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate Date;
    private LocalTime Time;
    @Enumerated(EnumType.STRING)
    private alertState AlertSate;

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return Date;
    }

    public LocalTime getTime() {
        return Time;
    }

    public alertState getAlertSate() {
        return AlertSate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public void setTime(LocalTime time) {
        Time = time;
    }

    public void setAlertSate(alertState alertSate) {
        AlertSate = alertSate;
    }
}
