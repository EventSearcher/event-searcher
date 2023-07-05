package pl.sda.service;

import org.springframework.stereotype.Service;
import pl.sda.model.UserEntity;
import pl.sda.Exception.UserAlreadyExistException;
import pl.sda.model.UserDTO;

@Service
public interface UserService  {

    void add(UserEntity user) throws UserAlreadyExistException;

    UserEntity changeClassToDTO(UserDTO user);


}
