package pl.sda.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.sda.Security.CustomUserDetailsService;
import pl.sda.model.*;
import pl.sda.repository.RoleRepository;
import pl.sda.repository.UserRepository;
import pl.sda.service.impl.UserValidationServiceImpl;
import java.util.Collections;

@Slf4j
@Controller
public class AuthController {

    private CustomUserDetailsService userDetailsService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private AuthenticationManager authenticationManager;
    private UserValidationServiceImpl userValidationService;

    public AuthController(CustomUserDetailsService userDetailsService, UserRepository userRepository,
                          PasswordEncoder passwordEncoder, RoleRepository roleRepository,
                          AuthenticationManager authenticationManager,
                          UserValidationServiceImpl userValidationService) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository =roleRepository;
        this.authenticationManager = authenticationManager;
        this.userValidationService = userValidationService;


    }

    @GetMapping("/start")
    public String getStartPage(ModelMap modelMap,Authentication authentication){

        if(authentication==null || !authentication.isAuthenticated()){

            modelMap.addAttribute("user",new UserLoginDTO());

            return "start-page";
        }

        return "redirect:/user/interface";
    }

    @GetMapping("/user/interface")
    public String getUserInterface(ModelMap modelMap, Authentication authentication){
        modelMap.addAttribute("username",authentication.getName());
        return "user-interface";
    }


    @GetMapping("/registration/form")
    public String getRegistrationForm(ModelMap modelMap){
        modelMap.addAttribute("newUser",new UserDTO());
        return "registration-page";
    }


    @PostMapping ("/registration")
    public String handleNewUser(@ModelAttribute("newUser")UserDTO userDTO,
                                BindingResult result, RedirectAttributes redirectAttributes){
        String err = userValidationService.validateUsername(userDTO);
        if (!err.isEmpty()){
            ObjectError error = new ObjectError("globalError",err);
            result.addError(error);
            redirectAttributes.addFlashAttribute("error1",err);
        }
        err = userValidationService.validatePasswordsMatch(userDTO);
        if (!err.isEmpty()){
            ObjectError error = new ObjectError("globalError",err);
            result.addError(error);
            redirectAttributes.addFlashAttribute("error2",err);
        }
        if (result.hasErrors()){
            return "registration-page";
        }
        UserEntity user = new UserEntity();

        user.setUsername(userDTO.getName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);
        return "redirect:/start";
    }

    @PostMapping ("/login")
    public String login(@ModelAttribute("user") UserLoginDTO user) {
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                user.getName(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/user/interface";

    }





}
