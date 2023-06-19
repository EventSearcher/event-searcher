package pl.sda.homePage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.DTO.UserDTO;
import pl.sda.service.EventService;

@Slf4j
@Controller
public class homePageController {

    private EventService eventService;
    @GetMapping("/")
    public String getHomePage(){
        return "home-page";
    }



}