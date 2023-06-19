package pl.sda.service;

import org.springframework.stereotype.Service;
import pl.sda.DTO.UserDTO;
import pl.sda.Exception.UserAlreadyExistException;
import pl.sda.model.User;

@Service
public interface UserService {

    void add(UserDTO user) throws UserAlreadyExistException;

    UserDTO changeClassToDTO(User user);


}
