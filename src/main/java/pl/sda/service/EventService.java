package pl.sda.service;

import pl.sda.model.Event;

import java.io.IOException;
import java.util.List;

public interface EventService {

    List<Event> getAll();
    void add(Event event);

    public void findByCity(String cityName) throws IOException, InterruptedException;
}
