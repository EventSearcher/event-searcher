package pl.sda.service;

import pl.sda.model.Event;

import java.util.List;

public interface EventService {

    List<Event> getAll();
    void add(Event event);
    List<Event> filterByCity();

}
