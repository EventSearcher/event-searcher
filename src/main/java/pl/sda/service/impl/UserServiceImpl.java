package pl.sda.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.DTO.UserDTO;
import pl.sda.Exception.UserAlreadyExistException;
import pl.sda.model.User;
import pl.sda.repository.UserRepository;
import pl.sda.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void add(UserDTO user) throws UserAlreadyExistException {
        if(userRepository.existsByName(user.getName())){
            throw new UserAlreadyExistException("User with name "+user.getName()+" already exist");
        }
        userRepository.save(user);

    }
    @Override
    public UserDTO changeClassToDTO(User user){
        BCryptPasswordEncoder BCrypt = new BCryptPasswordEncoder();
        return new UserDTO(user.getName(),BCrypt.encode(user.getPassword()));
    }


}
