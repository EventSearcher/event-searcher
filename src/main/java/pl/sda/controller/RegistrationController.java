package pl.sda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.DTO.UserDTO;
import pl.sda.Exception.UserAlreadyExistException;
import pl.sda.model.User;
import pl.sda.service.UserService;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String getRegistrationForm(ModelMap modelMap){
        modelMap.addAttribute("newUser",new User());
        return "registration-page";
    }
    @GetMapping("/user/registered")
    public String handleNewUser(@ModelAttribute("newUser") @Valid User user) throws UserAlreadyExistException {
        UserDTO userDto = userService.changeClassToDTO(user);
        userService.add(userDto);
        return "redirect:/";
    }

}
