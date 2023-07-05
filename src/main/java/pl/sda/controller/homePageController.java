package pl.sda.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.model.UserLoginDTO;
import pl.sda.service.EventService;

@Slf4j
@Controller
public class homePageController {

    private EventService eventService;
    @GetMapping("/")
    public String getHomePage(ModelMap modelMap){
            modelMap.addAttribute("user",new UserLoginDTO());
        return "homepage";
    }





}