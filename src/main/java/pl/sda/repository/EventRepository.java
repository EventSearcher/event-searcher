package pl.sda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.model.Event;
@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    Event findByCity(String city);
}
