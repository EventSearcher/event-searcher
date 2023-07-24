package pl.sda.service.impl;

import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.sda.ApiConsumer.TicketMasterClient;
import pl.sda.model.Event;
import pl.sda.model.PaginatedList;
import pl.sda.service.EventService;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class EventServiceImpl implements EventService {



    private final TicketMasterClient ticketMasterClient;
    @Getter
    private PaginatedList<Event> paginatedList;
    private final Map<String[],PaginatedList<Event>> cache = new HashMap<>();
    public EventServiceImpl(TicketMasterClient ticketMasterClient) {
        this.ticketMasterClient = ticketMasterClient;

    }


    @Override
    public void add(Event event) {

    }

    @Override
    public List<Event> getEvents(String cityName, Integer page, String startDate, String endDate, String sort, Integer pageSize) throws IOException, ParseException {
        String[] key = new String[]{cityName,
                startDate,endDate,sort};

        if (!cache.containsKey(key)) {
            if(!cache.isEmpty()){cache.clear();}
            List<ResponseEntity<String>> response = ticketMasterClient
                    .getJsonByCityName(cityName, startDate, endDate, sort);
            paginatedList = new PaginatedList<>(ticketMasterClient.mapJsonToEventList(response));
            cache.put(key,paginatedList);
            return paginatedList.getPage(page);
        }else{
            return cache.get(key).getPage(page);
        }

    }

    @Override
    public List<Event> getPage(int pageNumber) {
        return paginatedList.getPage(pageNumber);
    }

    public int getCurrentPage() {
        return paginatedList.getCurrentPage();
    }

    @Override
    public int getTotalPages() {
        return paginatedList.getTotalPages();
    }


}
