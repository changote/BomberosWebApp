package com.bomberos.registro.controllers;

import com.bomberos.registro.dto.RegistroCompletoDTO;
import com.bomberos.registro.dto.RespuestaDTO;
import com.bomberos.registro.dto.UnidadCompletaDTO;
import com.bomberos.registro.dto.UserDTO;
import com.bomberos.registro.entity.User;
import com.bomberos.registro.service.RegistroService;
import com.bomberos.registro.web.AjaxResponseGenerator;
import com.bomberos.registro.web.AjaxResponseObject;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/registro")
public class RegistroRestController {
    @Autowired
    private RegistroService registroService;

//    @Operation(summary = "Consulta por propiedades de una ciudad o para todas")
//    @GetMapping(value = "/propertysbycity", produces = { "application/json" })
//    public ResponseEntity<List<Property>> getPropertysByCity(@RequestParam(value = "city", defaultValue = "") String city) {
//
//        log.info("Se solicitan las propiedades de la ciudad " + city);
//        List<Property>propertyList = propertyService.getAllByCity(city);
//        return ResponseEntity.ok(propertyList);
//    }

//    @Operation(summary = "Consulta por propiedades de una ciudad")
//    @GetMapping(value = "/propertysforhome", produces = { "application/json" })
//    public ResponseEntity<List<PropertyHomeDTO>> getPropertysForHome(@RequestParam(value = "city") Long city) throws InterruptedException {
//        log.info("Se solicitan las propiedades de la ciudad " + city);
//        List<PropertyHomeDTO>propertyList = propertyService.getAllByCity(city);
//        return ResponseEntity.ok(propertyList);
//    }

//    @RequestMapping(value = "/getregistrosbyunidad", produces = { "application/json" }, method = RequestMethod.GET)
//    private AjaxResponseObject codigosConcepto(@RequestParam (value = "id") Long id) {
//        return AjaxResponseGenerator.createSimpleResponseOK(registroService.getRegistrosByUnidadId(id));
//    }

//
//    @Operation(summary = "Consulta por una propiedad")
//    @GetMapping(value = "/propertybyid", produces = { "application/json" })
//    public ResponseEntity<PropertyDTO> getPropertyById(@RequestParam(value = "propertyid") Long propertyId) throws InterruptedException {
//        log.info("Se solicita la propiedad " + propertyId);
//        Thread.sleep(600);
//        PropertyDTO propertyDTO = propertyService.getPropertyById(propertyId);
//        return ResponseEntity.ok(propertyDTO);
//    }

    @RequestMapping(value = "/nuevoregistro", produces = { "application/json" }, method = RequestMethod.POST)
    private AjaxResponseObject nuevoRegistro(@RequestBody @Valid RegistroCompletoDTO request) {
        log.info("Registro creado exitosamente: Unidad => " + request.getIdUnidad());
        return AjaxResponseGenerator.createSimpleResponseOK(registroService.nuevoRegistro(request));
    }

    @RequestMapping(value = "/getregistros", produces = { "application/json" }, method = RequestMethod.GET)
    private AjaxResponseObject getRegistros(@AuthenticationPrincipal User usuario, @RequestParam (value = "id") Long id) {
        log.info("Get Registros: Unidad => " + id);
        return AjaxResponseGenerator.createSimpleResponseOK(registroService.getRegistrosByUnidadId(id));
    }
}
