package pl.sda.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.sda.model.Event;
import pl.sda.service.EventService;

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
        modelMap.addAttribute("events", eventService.getAll());
        return "event-list";
    }
}

 /*   @GetMapping("/event/{city}")
    public String listByCity(String city, ModelMap modelMap) {
        modelMap.addAttribute("event", eventService.getByCity(city));
        return "event-by-city";
    }

    @GetMapping("/event/{id}")
    public String eventDetails(@PathVariable Integer id, ModelMap modelMap) {
        modelMap.addAttribute("event", eventService.getById(id));
        return "event-details";
    }
}
*/
