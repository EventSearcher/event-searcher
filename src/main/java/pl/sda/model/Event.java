package pl.sda.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Objects;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class Event {



    private String id;
    private String name;
    private String city;
    private String localDate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event event)) return false;
        return name.equals(event.name) && city.equals(event.city) && localDate.equals(event.localDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, city, localDate);
    }
}
