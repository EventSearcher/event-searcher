package pl.sda.service.impl;

import org.springframework.stereotype.Service;
import pl.sda.ApiConsumer.TicketMasterClient;
import pl.sda.model.Event;
import pl.sda.service.EventService;

import java.io.IOException;
import java.util.List;
@Service

public class inMemoryEventService implements EventService {

    private List<Event> events;

    private final TicketMasterClient ticketMasterClient;

    public inMemoryEventService(TicketMasterClient ticketMasterClient) {
        this.ticketMasterClient = ticketMasterClient;
    }

    private void setEvents(List<Event> newEventList) {
        this.events = newEventList;
    }

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
    public void findByCity(String cityName) throws IOException, InterruptedException {
        setEvents(ticketMasterClient
                .mapJsonToEventList(ticketMasterClient
                        .getJsonByCityName(cityName,0)));;
    }

}
