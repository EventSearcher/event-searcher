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



    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Events {
        @JsonProperty("events")
        public List<EventDetails> eventList;

    }
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class EventDetails{
        @JsonProperty("name")
        public String name;
        @JsonProperty("id")
        public String id;
        @JsonProperty("_embedded")
        public Venues venues;
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
        @JsonProperty("city")
        public City city;
    }
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class City{
        @JsonProperty("name")
        public String name;
    }
}
