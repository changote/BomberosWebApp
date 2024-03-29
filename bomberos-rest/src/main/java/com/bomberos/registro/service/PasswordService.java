package com.bomberos.registro.service;

import com.bomberos.registro.entity.User;
import com.bomberos.registro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
    @Autowired
    private UserRepository userRepository;

    public String encryptPassword(String password){
        return BCrypt.hashpw(password,BCrypt.gensalt());
    }

    public boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    public void saveUser(User newUser){
        this.userRepository.save(newUser);
    }
}
