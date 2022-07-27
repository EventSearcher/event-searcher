package pl.sda.service.impl;

import org.springframework.stereotype.Service;
import pl.sda.model.Event;
import pl.sda.service.EventService;
import java.util.ArrayList;
import java.util.List;
@Service
public class inMemoryEventService implements EventService {

    private List<Event> events;
    private static int counter;

//    public inMemoryEventService() {
//    events = new ArrayList<>();
//    events.add(new Event(1, "First festival", "Cracow" ));
//    events.add(new Event(2, "Second festival", "Katowice" ));
//    events.add(new Event(3, "Third festival", "Warsaw" ));
//    counter = 3;
//    }

    @Override
    public List<Event> getAll() {
        return events;
    }

    @Override
    public void add(Event event) {

        events.add(event);
    }

    @Override
    public List<Event> filterByCity() {
        return events;
    }

}
