package com.bomberos.registro.auth;

import com.bomberos.registro.connectors.AuthenticantionConnector;
import com.bomberos.registro.dto.UserDTO;
import com.bomberos.registro.exceptions.AuthenticantionException;
import com.bomberos.registro.service.PasswordService;
import com.bomberos.registro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;


@Component
public class CustomAuthenticationProvider implements AuthenticationManager {

	@Autowired
	private AuthenticantionConnector authenticationConnector;



	/**
	 * Toma el obj Authentication que recibe como parametro (el cual contiene los datos de un usuario), y busca los permisos (authorities) que tiene
	 * dicho usuario.
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getPrincipal().toString();
		String password = authentication.getCredentials().toString();
		Collection<? extends GrantedAuthority> auth = authentication.getAuthorities();

		if (password == null || password.equals(""))
			throw new BadCredentialsException("1");

		try {
			authenticationConnector.authenticate(username, password);
		} catch (AuthenticantionException e) {
			throw new BadCredentialsException("3", e);
		}
		UserDTO userDTO = authenticationConnector.getInformationUser(username);
		return new UsernamePasswordAuthenticationToken(userDTO, password, auth);
	}
}