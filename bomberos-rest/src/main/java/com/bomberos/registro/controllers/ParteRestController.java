package com.bomberos.registro.controllers;

import com.bomberos.registro.entity.Parte;
import com.bomberos.registro.entity.Unidad;
import com.bomberos.registro.service.ParteService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
public class ParteRestController {
    @Autowired
    private ParteService parteService;

    @Operation(summary = "Consulta por una propiedad")
    @GetMapping(value = "/allpartesbyunidadid", produces = { "application/json" })
    public ResponseEntity<List<Parte>> getPropertyById(@RequestParam(value = "id") Long id) {
        List<Parte> parteList = parteService.findAllByIdUnidad(id);
        return ResponseEntity.ok(parteList);
    }
}
