package pl.sda.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonToEventMapper {


    @JsonProperty("_embedded")
    public Events events;
    public Page page;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Events {
        @JsonProperty("events")
        public List<EventDetails> eventList;

    }
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class EventDetails{

        public String name;
        public String id;
        @JsonProperty("_embedded")
        public Venues venues;
        public Dates dates;
    }
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Dates{
        public Start start;
    }
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Start{
        public String localDate;
    }
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Venues{
        @JsonProperty("venues")
        public List<VenuesDetails> venuesList;
    }
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VenuesDetails {

        public City city;
    }
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class City{

        public String name;
    }
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Page{

        public long totalElements;
        public int number;

    }
}
