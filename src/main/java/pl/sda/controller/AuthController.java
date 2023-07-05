package pl.sda.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.Security.CustomUserDetailsService;
import pl.sda.model.Role;
import pl.sda.model.UserDTO;
import pl.sda.model.UserEntity;
import pl.sda.model.UserLoginDTO;
import pl.sda.repository.RoleRepository;
import pl.sda.repository.UserRepository;

import javax.validation.Valid;
import java.util.Collections;


@Controller
public class AuthController {

    private CustomUserDetailsService userDetailsService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private AuthenticationManager authenticationManager;

    public AuthController(CustomUserDetailsService userDetailsService, UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, AuthenticationManager authenticationManager) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository =roleRepository;
        this.authenticationManager = authenticationManager;
    }
    @GetMapping("/user/logged")
    public String getLogged(){
        return "logged";
    }
    @GetMapping("/registration/form")
    public String getRegistrationForm(ModelMap modelMap){
        modelMap.addAttribute("newUser",new UserDTO());
        return "registration-page";
    }
    @PostMapping ("/registration")
    public String handleNewUser(@ModelAttribute("newUser") @Valid UserDTO userDTO){
        if (userRepository.existsByName(userDTO.getName())){
            return HttpStatus.BAD_REQUEST.getReasonPhrase();
        }
        UserEntity user = new UserEntity();

        user.setName(userDTO.getName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);
        return "redirect:/";
    }

    @PostMapping ("/login")
    public String login(@ModelAttribute("user") UserLoginDTO user){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getName(),user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/user/logged";
    }


}
