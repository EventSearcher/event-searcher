package pl.sda.ApiConsumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.sda.model.Event;
import pl.sda.model.JsonToEventMapper;
import pl.sda.pagination.PageInfo;
import pl.sda.pagination.PageInfoFromJson;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class TicketMasterClient {
    private final String API_KEY = "LUBYv7Fh6ttXG0prJ2gLwP8fXUSAJGP0";
    private final RestTemplate template =new RestTemplate();
    private final HttpHeaders headers = new HttpHeaders();

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final SimpleDateFormat simpleDateFormatStart = new SimpleDateFormat("yyyy-MM-dd'T'00:00:00'Z'");
    private final SimpleDateFormat simpleDateFormatEnd = new SimpleDateFormat("yyyy-MM-dd'T'23:59:59'Z'");


    public ResponseEntity<String> getJsonByCityName(String cityName,Integer page,String startDate,String endDate,String sort) throws IOException, InterruptedException, ParseException {
        String url = "https://app.ticketmaster.com/discovery/v2/events.json?city={city}&apikey={apikey}&page={page}&startDateTime={startDateTime}&endDateTime={endDateTime}";

        HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(headers);
        String startD = null;
        String endD = null;

        if(!startDate.isEmpty()){
            startD = simpleDateFormatStart.format(simpleDateFormat.parse(startDate));
        }
        if(!endDate.isEmpty()){
            endD = simpleDateFormatEnd.format(simpleDateFormat.parse(endDate));
        }

        Map<String,String> queryParams = new HashMap<>();

        queryParams.put("city",cityName);
        queryParams.put("page", String.valueOf(page));
        queryParams.put("startDateTime",startD);
        queryParams.put("endDateTime",endD);
        queryParams.put("sort",sort);
        queryParams.put("apikey",API_KEY);

        return  template.exchange(url, HttpMethod.GET,httpEntity,String.class,queryParams);

    }


    public List<Event> mapJsonToEventList(ResponseEntity<String> response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonToEventMapper apiEvent=  objectMapper.readValue(response.getBody(), new TypeReference<JsonToEventMapper>() {
        });
        List<JsonToEventMapper.EventDetails> eventList = apiEvent.getEvents().getEventList();

        List<Event> result = new ArrayList<>();
        for (JsonToEventMapper.EventDetails e : eventList) {
            result.add(new Event(e.getId(),
                    e.getName(),
                    e.getVenues()
                            .getVenuesList()
                            .stream()
                            .map(JsonToEventMapper.VenuesDetails::getCity)
                            .map(JsonToEventMapper.City::getName)
                            .collect(Collectors.joining()),
                    e.getDates().getStart().getLocalDate(),
                    e.getDates().getTimezone()));

        }
        return result;
    }
    public PageInfo mapResponseToPageInfo(ResponseEntity<String> response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        PageInfoFromJson info=  objectMapper.readValue(response.getBody(), new TypeReference<PageInfoFromJson>() {
        });

        return new PageInfo(info.getPage().size,info.getPage().totalElements,info.getPage().totalPages);

    }

}
