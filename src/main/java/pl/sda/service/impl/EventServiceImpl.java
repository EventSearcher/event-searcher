package pl.sda.service.impl;

import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.sda.ApiConsumer.TicketMasterClient;
import pl.sda.model.Event;
import pl.sda.pagination.PageInfo;
import pl.sda.service.EventService;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
@Service

public class EventServiceImpl implements EventService {



    private final TicketMasterClient ticketMasterClient;
    @Getter
    private PageInfo info;
    public EventServiceImpl(TicketMasterClient ticketMasterClient, PageInfo info) {
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
    public List<Event> findByCity(String cityName, Integer page, String startDate, String endDate,String sort) throws IOException, InterruptedException, ParseException {
        ResponseEntity<String> response = ticketMasterClient
                .getJsonByCityName(cityName,page,startDate,endDate,sort);


        setInfo(ticketMasterClient.mapResponseToPageInfo(response));

        return ticketMasterClient.mapJsonToEventList(response);

    }



}
