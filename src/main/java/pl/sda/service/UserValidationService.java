package pl.sda.service;

import org.springframework.stereotype.Service;
import pl.sda.model.UserDTO;

@Service
public interface UserValidationService {


    String validateUsername(UserDTO user);

    String validatePasswordsMatch(UserDTO user);
}

