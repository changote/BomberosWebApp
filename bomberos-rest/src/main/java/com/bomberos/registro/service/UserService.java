package com.bomberos.registro.service;

import com.bomberos.registro.entity.User;
import com.bomberos.registro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordService passwordService;

    public User findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    public void saveNewUser(User newUser){
        newUser.setBloqueado(false);
        newUser.setPassword(passwordService.encryptPassword(newUser.getPassword()));
        passwordService.saveUser(newUser);
    }

    public Object bajaUser(Long id) {
        Optional<User> possibleUser = userRepository.findById(id);
        if(possibleUser.isPresent()){
            User user = possibleUser.get();
            user.setBloqueado(!user.isBloqueado());
            return userRepository.save(user);
        }
        return null;
    }

    public List<User>getUsers(){
        return userRepository.findAll();
    }
}
