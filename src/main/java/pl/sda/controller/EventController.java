package pl.sda.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.model.Event;
import pl.sda.service.EventService;
import java.io.IOException;
import java.text.ParseException;

@Slf4j
@Controller
public class EventController {

    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping("/event/page")
    public String getEventListPage(){
        return "event-list";
    }

    @GetMapping("/events")
    public String getEvents(@RequestParam(value = "city" ,defaultValue = "", required = false) String city
            ,@RequestParam(required = false,defaultValue ="0") Integer page,
            @RequestParam(required=false, defaultValue = "") String startDate,
            @RequestParam(required = false, defaultValue = "")String endDate,
            @RequestParam(required = false,defaultValue = "date,asc")String sort,
            @RequestParam(required = false,defaultValue = "20")Integer pageSize,
            ModelMap modelMap) throws IOException, InterruptedException, ParseException {

        modelMap.addAttribute("events", eventService.getEvents(city,page,startDate,endDate,sort,pageSize))
                .addAttribute("currentPage", eventService.getCurrentPage())
                .addAttribute("totalPages",eventService.getTotalPages())
                .addAttribute("city",city);

        return "event-list";
    }


}




