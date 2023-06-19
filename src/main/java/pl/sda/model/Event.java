package pl.sda.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class Event {



    private String id;
    private String name;
    private String city;
    private String localDate;
    private String timezone;
}
