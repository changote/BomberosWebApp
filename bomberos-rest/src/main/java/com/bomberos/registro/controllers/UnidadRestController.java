package com.bomberos.registro.controllers;

import com.bomberos.registro.dto.RegistroCompletoDTO;
import com.bomberos.registro.dto.UnidadCompletaDTO;
import com.bomberos.registro.entity.Unidad;
import com.bomberos.registro.entity.User;
import com.bomberos.registro.service.UnidadService;
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
@RequestMapping("/unidad")
public class UnidadRestController {

    @Autowired
    private UnidadService unidadService;

    @Operation(summary = "Consulta por una propiedad")
    @RequestMapping(value = "/allunidades", produces = { "application/json" }, method = RequestMethod.GET)
    public AjaxResponseObject getPropertyById(@AuthenticationPrincipal User usuario) {
        List<Unidad> unidadList = unidadService.getUnidad();
        return AjaxResponseGenerator.createSimpleResponseOK(unidadList);
    }

    @Operation(summary = "Consulta por una propiedad")
    @RequestMapping(value = "/unidadbyid", produces = { "application/json" }, method = RequestMethod.GET)
    public AjaxResponseObject getUnidadById(@AuthenticationPrincipal User usuario,@RequestParam(value = "id") Long id) {
        Unidad unidad = unidadService.findUnidadById(id);
        return AjaxResponseGenerator.createSimpleResponseOK(unidad);
    }

    @Operation(summary = "Consulta por una propiedad")
    @RequestMapping(value = "/unidadcompletabyid", produces = { "application/json" }, method = RequestMethod.GET)
    public AjaxResponseObject getUnidadCompletaById(@AuthenticationPrincipal User usuario, @RequestParam(value = "id") Long id) {
        log.info("unidad completa");
        UnidadCompletaDTO unidad = unidadService.findUnidadCompletaById(id);
        return AjaxResponseGenerator.createSimpleResponseOK(unidad);
    }

    @RequestMapping(value = "/nueva-unidad", produces = { "application/json" }, method = RequestMethod.POST)
    private AjaxResponseObject nuevaUnidad(@AuthenticationPrincipal User usuario, @RequestBody @Valid UnidadCompletaDTO request) {
        return AjaxResponseGenerator.createSimpleResponseOK(unidadService.nuevaUnidad(request));
    }

    @RequestMapping(value = "/delete", produces = { "application/json" }, method = RequestMethod.DELETE)
    private AjaxResponseObject deleteUnidad(@AuthenticationPrincipal User usuario, @RequestParam(value = "id") Long id) {
        return AjaxResponseGenerator.createSimpleResponseOK(unidadService.deleteUnidad(id));
    }
}
