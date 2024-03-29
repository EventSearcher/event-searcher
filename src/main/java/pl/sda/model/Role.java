package pl.sda.model;


import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

}
