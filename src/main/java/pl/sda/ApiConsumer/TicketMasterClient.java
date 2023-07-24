package pl.sda.ApiConsumer;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.sda.Security.ApiConfigProperties;
import pl.sda.model.Event;
import pl.sda.model.JsonToEventMapper;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class TicketMasterClient {
    private final RestTemplate template =new RestTemplate();
    private final HttpHeaders headers = new HttpHeaders();
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final SimpleDateFormat simpleDateFormatStart = new SimpleDateFormat("yyyy-MM-dd'T'00:00:00'Z'");
    private final SimpleDateFormat simpleDateFormatEnd = new SimpleDateFormat("yyyy-MM-dd'T'23:59:59'Z'");
    private final ApiConfigProperties apiConfigProperties;

    public TicketMasterClient(ApiConfigProperties apiConfigProperties) {
        this.apiConfigProperties = apiConfigProperties;
    }

    public List<ResponseEntity<String>> getJsonByCityName(String cityName, String startDate, String endDate, String sort) throws  ParseException {
        String url = "https://app.ticketmaster.com/discovery/v2/events.json?city={city}&apikey={apikey}&page={page}&startDateTime={startDateTime}&endDateTime={endDateTime}&size=200";

        HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(headers);
        String startD = null;
        String endD = null;
        if(!startDate.isEmpty()){
            startD = simpleDateFormatStart.format(simpleDateFormat.parse(startDate));
        }
        if(!endDate.isEmpty()){
            endD = simpleDateFormatEnd.format(simpleDateFormat.parse(endDate));
        }
        List<ResponseEntity<String>> res = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            Map<String,String> queryParams = new HashMap<>();
            queryParams.put("city",cityName);
            queryParams.put("page", String.valueOf(i));
            queryParams.put("startDateTime",startD);
            queryParams.put("endDateTime",endD);
            queryParams.put("sort",sort);
            queryParams.put("apikey", apiConfigProperties.apiKey());
            res.add(template.exchange(url,HttpMethod.GET,httpEntity,String.class,queryParams));

        }
            return res;
    }


    public List<Event> mapJsonToEventList(List<ResponseEntity<String>> response) throws IOException{



        List<Event> result = new ArrayList<>();

        for (int i = 0;i <= 4; i++){

            ObjectMapper objectMapper = new ObjectMapper();
            JsonToEventMapper apiEvent=  objectMapper.readValue(response.get(0).getBody(), new TypeReference<>() {
            });

            List<JsonToEventMapper.EventDetails> eventList = apiEvent.getEvents().getEventList();
            long totalElements = apiEvent.getPage().getTotalElements();

            for (JsonToEventMapper.EventDetails e : eventList) {
                Event event = new Event(e.getId(),
                        e.getName(),
                        e.getVenues()
                                .getVenuesList()
                                .stream()
                                .map(JsonToEventMapper.VenuesDetails::getCity)
                                .map(JsonToEventMapper.City::getName)
                                .collect(Collectors.joining()),
                        e.getDates().getStart().getLocalDate());
                if (!result.contains(event)) {
                    result.add(event);
                }
            }
            if(totalElements-(200*(i+1))<0){
                break;
            }
        }
        return result;
    }

}
