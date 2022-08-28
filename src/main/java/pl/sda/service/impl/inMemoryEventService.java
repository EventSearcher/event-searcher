package pl.sda.service.impl;

import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.sda.ApiConsumer.TicketMasterClient;
import pl.sda.model.Event;
import pl.sda.pagination.PageInfo;
import pl.sda.service.EventService;

import java.io.IOException;
import java.util.List;
@Service

public class inMemoryEventService implements EventService {



    private final TicketMasterClient ticketMasterClient;
    @Getter
    private PageInfo info;
    public inMemoryEventService(TicketMasterClient ticketMasterClient, PageInfo info) {
        this.ticketMasterClient = ticketMasterClient;
        this.info = info;
    }

    private void setInfo(PageInfo newInfo){
        this.info = newInfo;
    }

    @Override
    public void add(Event event) {

    }

    @Override
    public List<Event> findByCity(String cityName, Integer page) throws IOException, InterruptedException {
        ResponseEntity<String> response = ticketMasterClient
                .getJsonByCityName(cityName,page);


        setInfo(ticketMasterClient.mapResponseToPageInfo(response));

        return ticketMasterClient.mapJsonToEventList(response);

    }



}
