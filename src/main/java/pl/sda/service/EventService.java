package pl.sda.service;

import pl.sda.model.Event;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface EventService {


    void add(Event event);

    List<Event> getEvents(String cityName, Integer page, String startDate, String endDate,String sort,Integer pageSize) throws IOException, InterruptedException, ParseException;


    List<Event> getPage(int pageNumber);

    int getCurrentPage();

    int getTotalPages();

}
