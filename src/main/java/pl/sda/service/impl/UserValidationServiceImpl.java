package pl.sda.service.impl;


import org.springframework.stereotype.Service;
import pl.sda.model.UserDTO;
import pl.sda.repository.UserRepository;
import pl.sda.service.UserValidationService;

@Service
public class UserValidationServiceImpl implements UserValidationService {

    private final UserRepository userRepository;

    public UserValidationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }




    @Override
    public String validateUsername(UserDTO user) {
        if (userRepository.existsByUsername(user.getName())){
            return "The username: "+ user.getName()+" is already taken!";
        }
        return "";
    }

    @Override
    public String validatePasswordsMatch(UserDTO user) {
        if (!user.getPassword().equals(user.getMatchingPassword())){
            return "Passwords are not equal";
        }
        return "";
    }


}
