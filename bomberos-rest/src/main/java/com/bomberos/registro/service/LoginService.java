package com.bomberos.registro.service;

import com.bomberos.registro.auth.CustomAuthenticationProvider;
import com.bomberos.registro.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService {

	@Autowired
	private CustomAuthenticationProvider authenticantionConnector;

    @Autowired
    private UserService userService;
	
    public void doLogin(String username, String password) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentic = authenticantionConnector.authenticate(auth);
        SecurityContextHolder.getContext().setAuthentication(authentic);
    }
}
