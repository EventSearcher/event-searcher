package pl.sda.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.sda.model.Event;
import pl.sda.service.EventService;

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




    @GetMapping("/events")
    public String findEventsByCityName(@RequestParam(value = "city" ,defaultValue = "", required = false) String city
            ,@RequestParam(required = false,defaultValue ="0") Integer page
            ,ModelMap modelMap) throws IOException, InterruptedException {

        modelMap.addAttribute("events", eventService.findByCity(city,page))
                .addAttribute("totalPages",eventService.getInfo().getTotalPages())
                .addAttribute("currentPage", page)
                .addAttribute("totalElements",eventService.getInfo().getTotalElements())
                .addAttribute("city",city);





        return "event-list";
    }








}




