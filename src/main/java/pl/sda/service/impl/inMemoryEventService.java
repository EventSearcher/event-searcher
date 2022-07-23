package pl.sda.service.impl;

import org.springframework.stereotype.Service;
import pl.sda.model.Event;
import pl.sda.repository.EventRepository;
import pl.sda.service.EventService;

import java.util.List;

@Service
public class inMemoryEventService implements EventService {

    private final EventRepository eventRepository;

    public inMemoryEventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public void add(Event event) {
        eventRepository.save(event);
    }

    @Override
    public Event getByCity(String city) {
        return eventRepository.findByCity(city);
    }

    @Override
    public Event getById(Integer id) {
        return eventRepository.findById(id).orElse(null);
    }

}
