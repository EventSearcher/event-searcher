package pl.sda.ApiConsumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.Category;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.sda.model.Event;
import pl.sda.model.JsonToEventMapper;


import java.io.IOException;
import java.util.*;

@Component
public class TicketMasterClient {
    private final String API_KEY = "LUBYv7Fh6ttXG0prJ2gLwP8fXUSAJGP0";
    private final RestTemplate template =new RestTemplate();
    private final HttpHeaders headers = new HttpHeaders();

    public ResponseEntity<String> getJsonByCityName(String cityName,Integer page) throws IOException, InterruptedException {
        String url = "https://app.ticketmaster.com/discovery/v2/events.json?city={city}&page={page}&apikey={apikey}";

        HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(headers);


        Map<String,String> queryParams = new HashMap<>();

        queryParams.put("city",cityName);
        queryParams.put("page",page.toString());
        queryParams.put("apikey",API_KEY);

        return  template.exchange(url, HttpMethod.GET,httpEntity,String.class,queryParams);

    }

    public ResponseEntity<String> getJsonByCityNameAndDate (String cityName, Date date,Integer page){
        String url = "https://app.ticketmaster.com/discovery/v2/events.json?city={city}&startDateTime={startDateTime}&page={page}&apikey={apikey}";

        HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(headers);


        Map<String,String> queryParams = new HashMap<>();

        queryParams.put("city",cityName);
        queryParams.put("page",page.toString());
        queryParams.put("startDateTime",date.toString());
        queryParams.put("apikey",API_KEY);

        return  template.exchange(url, HttpMethod.GET,httpEntity,String.class,queryParams);
    }

    public ResponseEntity<String> getJsonByCityNameAndCategory (String cityName,String classificationName, Integer page){
        String url = "https://app.ticketmaster.com/discovery/v2/events.json?city={city}&classificationName={classificationName}&page={page}&apikey={apikey}";

        HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(headers);


        Map<String,String> queryParams = new HashMap<>();

        queryParams.put("city",cityName);
        queryParams.put("page",page.toString());
        queryParams.put("classificationName",classificationName);
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
                            .toString()));
        }
        return result;
    }
}
