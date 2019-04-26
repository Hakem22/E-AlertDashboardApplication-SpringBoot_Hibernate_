package ealerte.project.demo.Model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name="admin")
public class Admin extends Acteur{


    public Admin(){}
    public Admin(@NotEmpty String firstName, @NotEmpty String lastName, @Digits(fraction = 0, integer = 10) @NotEmpty String phone, @NotEmpty String username, @NotEmpty String password, Alert... alerts){
        super(firstName,lastName, phone,username, password, alerts);

    }

}
