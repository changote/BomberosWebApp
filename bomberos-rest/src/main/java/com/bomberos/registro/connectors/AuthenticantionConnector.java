package com.bomberos.registro.connectors;

import com.bomberos.registro.dto.UserDTO;
import com.bomberos.registro.entity.User;
import com.bomberos.registro.exceptions.AuthenticantionException;
import com.bomberos.registro.exceptions.PasswordException;
import com.bomberos.registro.service.PasswordService;
import com.bomberos.registro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthenticantionConnector {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordService passwordService;

	public void authenticate(String username, String password) {
		try {
			if (password == null || password.equals("")) {
				throw new RuntimeException();
			}
			User user = userService.findUserByUsername(username);

			if (user == null || !passwordService.verifyPassword(password,user.getPassword())) {
				throw new RuntimeException();
			}
		} catch (Exception e) {
			throw new AuthenticantionException("Ocurrió un error al autenticar el usuario " + username, e);
		}
	}

	public UserDTO getInformationUser(String username) {
		try {
			UserDTO usuario = new UserDTO();


			User user = userService.findUserByUsername(username);
			if (user != null) {
				if(user.isBloqueado())
					throw new PasswordException("Usuario bloqueado, contacte con el administrador");
				usuario.setUsername(username);
				usuario.setNombre(user.getNombreCompleto());
				usuario.setUserId(user.getUserId());
			}
			return usuario;
		} catch (Exception ex) {
			throw new AuthenticantionException("Error al intentar obtener la información del usuario: Problemas de conexión a la base de datos.", ex);
		}
	}

}