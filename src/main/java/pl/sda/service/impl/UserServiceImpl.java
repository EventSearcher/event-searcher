package pl.sda.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.model.UserEntity;
import pl.sda.Exception.UserAlreadyExistException;
import pl.sda.model.UserDTO;
import pl.sda.repository.UserRepository;
import pl.sda.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void add(UserEntity user) throws UserAlreadyExistException {
        if(userRepository.existsByName(user.getName())){
            throw new UserAlreadyExistException("UserDTO with name "+user.getName()+" already exist");
        }
        userRepository.save(user);

    }
    @Override
    public UserEntity changeClassToDTO(UserDTO userDTO){
        BCryptPasswordEncoder BCrypt = new BCryptPasswordEncoder();
        return new UserEntity(userDTO.getName(),BCrypt.encode(userDTO.getPassword()));
    }


}
