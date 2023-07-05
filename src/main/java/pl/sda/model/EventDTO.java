package pl.sda.model;

import org.springframework.context.annotation.Primary;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "event")
public class EventDTO {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String city;
}
