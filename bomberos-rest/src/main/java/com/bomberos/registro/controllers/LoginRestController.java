package com.bomberos.registro.controllers;

import com.bomberos.registro.dto.LoginDTO;
import com.bomberos.registro.dto.RespuestaDTO;
import com.bomberos.registro.dto.UserDTO;
import com.bomberos.registro.service.LoginService;
import com.bomberos.registro.web.AjaxResponseGenerator;
import com.bomberos.registro.web.AjaxResponseObject;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Log4j2
@RequestMapping("/login")
@RestController
public class LoginRestController {
	
	@Autowired
	private LoginService loginService;

//	@Operation(summary = "Inicio de sesión del usuario")
//	@PostMapping(value = "", produces = { "application/json" }, consumes = { "application/json" })
//    public ResponseEntity<RespuestaDTO> login(HttpServletRequest request, HttpServletResponse response, @RequestBody @Valid LoginDTO loginDTO) {
//		/* ****************** INTENTO DE LOGIN ****************** */
//		try {
//			log.info("Se procede autenticar al usuario: " + loginDTO.getUsername());
//			HttpSession session = request.getSession(false);
//			if(session!=null)
//				session.invalidate();
//			request.getSession(true);
//			loginService.doLogin(loginDTO.getUsername(), loginDTO.getPassword());
//		} catch(Exception e) {
//			log.info("Ocurrió un error autenticando al usuario: " + loginDTO.getUsername());
//			return new ResponseEntity<>(new RespuestaDTO("Credenciales incorrectas"), HttpStatus.UNAUTHORIZED);
//		}
//		log.info("Se autenticó correctamente al usuario: " + loginDTO.getUsername());
//		response.setHeader("Set-Cookie", "JSESSIONID=" + request.getSession().getId() + " ; SameSite=None ; Secure");
//		return ResponseEntity.ok(new RespuestaDTO("Login OK"));
//    }
	@RequestMapping(value = "", produces = { "application/json" }, method = RequestMethod.POST)
	private AjaxResponseObject login(HttpServletRequest request, HttpServletResponse response, @RequestBody @Valid LoginDTO loginDTO) {
		try {
			log.info("Se procede autenticar al usuario: " + loginDTO.getUsername());
			HttpSession session = request.getSession(false);
			if(session!=null)
				session.invalidate();
			request.getSession(true);
			loginService.doLogin(loginDTO.getUsername(), loginDTO.getPassword());
		} catch(Exception e) {
			log.info("Ocurrió un error autenticando al usuario: " + loginDTO.getUsername());
			return AjaxResponseGenerator.createSimpleResponseError("Login Error");
		}
		log.info("Se autenticó correctamente al usuario: " + loginDTO.getUsername());
		response.setHeader("Set-Cookie", "JSESSIONID=" + request.getSession().getId() + " ; SameSite=None ; Secure");
		return AjaxResponseGenerator.createSimpleResponseOK("Login Ok");
	}

	@RequestMapping(value = "/info", produces = { "application/json" }, method = RequestMethod.GET)
	public AjaxResponseObject userInfo(@AuthenticationPrincipal UserDTO user){
		log.info(user.getNombre());
		return AjaxResponseGenerator.createSimpleResponseOK(user);
	}
	
}
