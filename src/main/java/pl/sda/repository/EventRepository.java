package pl.sda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.DTO.EventDTO;

@Repository
public interface EventRepository extends JpaRepository<EventDTO, Integer> {

    EventDTO findByCity(String city);
}
