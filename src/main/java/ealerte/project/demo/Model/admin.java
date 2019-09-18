package ealerte.project.demo.Model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name="admin")
public class Admin extends Acteur{


    public Admin(){}
    public Admin(@NotEmpty String firstName, @NotEmpty String lastName, @Digits(fraction = 0, integer = 10) @NotEmpty String phone,@NotEmpty String email, @NotEmpty String username, @NotEmpty String password){
        super(firstName,lastName, phone, email,username, password);

    }

}
