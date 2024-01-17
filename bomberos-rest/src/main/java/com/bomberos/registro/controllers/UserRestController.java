package com.bomberos.registro.controllers;

import com.bomberos.registro.dto.RegistroCompletoDTO;
import com.bomberos.registro.dto.RespuestaDTO;
import com.bomberos.registro.dto.UserDTO;
import com.bomberos.registro.entity.User;
import com.bomberos.registro.service.UserService;
import com.bomberos.registro.web.AjaxResponseGenerator;
import com.bomberos.registro.web.AjaxResponseObject;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping("/user")
public class UserRestController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Guardado de nuevo usuario")
    @PostMapping(value = "/createuser", produces = { "application/json" })
    public ResponseEntity<?> setUser(@RequestBody @Valid User newUser) {
        /* ****************** INTENTO DE USER SAVE ****************** */
        try {
            log.info("Se procede guardar al usuario: " + newUser.getUsername());
            userService.saveNewUser(newUser);
        } catch (Exception ex) {
            log.info("Ocurrió un error al guardar al usuario: " + newUser.getUsername());
            return ResponseEntity.ok(new RespuestaDTO("Error al intentar guardar."));
        }
        log.info("Exito");
        return ResponseEntity.ok(new RespuestaDTO("Evento guardado correctamente"));
    }

//    @RequestMapping(value = "/edit-user", produces = { "application/json" }, method = RequestMethod.PUT)
//    private AjaxResponseObject codigosConcepto(@RequestBody @Valid RegistroCompletoDTO request) {
//        return AjaxResponseGenerator.createSimpleResponseOK(userService.nuevoRegistro(request));
//    }

    @RequestMapping(value = "/baja-user", produces = { "application/json" }, method = RequestMethod.PUT)
    private AjaxResponseObject bajaUser(@RequestParam (value = "id") Long id) {
        return AjaxResponseGenerator.createSimpleResponseOK(userService.bajaUser(id));
    }

    @RequestMapping(value = "/get-users", produces = { "application/json" }, method = RequestMethod.GET)
    private AjaxResponseObject getUsers() {
        return AjaxResponseGenerator.createSimpleResponseOK(userService.getUsers());
    }
}
