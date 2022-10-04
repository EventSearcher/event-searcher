package pl.sda.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import pl.sda.model.Event;
import pl.sda.pagination.PageInfo;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface EventService {


    void add(Event event);

    public List<Event> findByCity(String cityName, Integer page, String startDate, String endDate,String sort) throws IOException, InterruptedException, ParseException;


    PageInfo getInfo();
}
