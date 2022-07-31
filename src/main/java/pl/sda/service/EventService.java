package pl.sda.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import pl.sda.model.Event;

import java.io.IOException;
import java.util.List;

public interface EventService {


    void add(Event event);

    public List<Event> findByCity(String cityName, Integer page) throws IOException, InterruptedException;



}
