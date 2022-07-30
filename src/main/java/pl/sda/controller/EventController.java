package pl.sda.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.sda.model.Event;
import pl.sda.service.EventService;
import pl.sda.model.Filter;
import java.io.IOException;

@Slf4j
@Controller
public class EventController {


    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;

    }

    @GetMapping("/event/create")
    public String showEventForm(ModelMap modelMap) {
        modelMap.addAttribute("emptyEvent", new Event());
        return "event-create";
    }

    @PostMapping("/event/save")
    public String handleNewEvent(@ModelAttribute("emptyEvent") Event event) {
        log.info("Handle new event " + event);

        eventService.add(event);

        return "redirect:/event/list";
    }

    @GetMapping("/event/list")
    public String showEvents(ModelMap modelMap) {
        modelMap.addAttribute("events", eventService.getAll())
                .addAttribute("cityName",new Filter());

        return "event-list";
    }
//    @GetMapping("/event/filter")
//    public String filterByCity(ModelMap modelMap) {
//        modelMap.addAttribute("events", eventService.filterByCity());
//        return "event-filter";
//    }

    @PostMapping("/event/find")
    public String findEventsByCityName(@ModelAttribute("cityName") Filter cityName) throws IOException, InterruptedException {

        eventService.findByCity(cityName.getCityName());

        return "redirect:/event/list";
    }
}

