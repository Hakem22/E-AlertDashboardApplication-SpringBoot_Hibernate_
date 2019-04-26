package ealerte.project.demo.Model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="agent")
public class Agent extends Acteur {




    public Agent(){super();}
    public Agent( @NotEmpty String firstName, @NotEmpty String lastName, @Digits(fraction = 0, integer = 10) @NotEmpty String phone, @NotEmpty String username, @NotEmpty String password, Alert... alerts) {
        super( firstName, lastName, phone, username, password,alerts);
    }

}
