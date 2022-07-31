package pl.sda.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import pl.sda.ApiConsumer.TicketMasterClient;
import pl.sda.model.Event;
import pl.sda.service.EventService;

import java.io.IOException;
import java.util.List;
@Service

public class inMemoryEventService implements EventService {



    private final TicketMasterClient ticketMasterClient;

    public inMemoryEventService(TicketMasterClient ticketMasterClient) {
        this.ticketMasterClient = ticketMasterClient;
    }


    @Override
    public void add(Event event) {

    }

    @Override
    public List<Event> findByCity(String cityName, Integer page) throws IOException, InterruptedException {
        return ticketMasterClient
                .mapJsonToEventList(ticketMasterClient
                        .getJsonByCityName(cityName,page));
    }



}
