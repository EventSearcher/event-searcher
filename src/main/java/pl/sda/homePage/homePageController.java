package pl.sda.homePage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homePageController {

    @GetMapping("/home")
    public String getHomePage(){
        return "home-page";
    }
}
