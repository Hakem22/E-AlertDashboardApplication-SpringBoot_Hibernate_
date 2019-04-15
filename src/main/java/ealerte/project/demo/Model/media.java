package ealerte.project.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String URI;

    public Long getId() {
        return id;
    }

    public String getURI() {
        return URI;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }
}
