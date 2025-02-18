package com.bancolombia.aplicacionbancaria.controller;

import com.bancolombia.aplicacionbancaria.model.ClienteRequest;
import com.bancolombia.aplicacionbancaria.service.PrestamoHistorialService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/app")
public class ClienteController {

    private final PrestamoHistorialService historialPrestamoService;

    public ClienteController(PrestamoHistorialService historialPrestamoService) {
        this.historialPrestamoService = historialPrestamoService;
    }

    @PostMapping(path = "/consultarhistorial")
    public ResponseEntity<Object> consulta(@RequestBody ClienteRequest cliente){
        return new ResponseEntity<>(historialPrestamoService
                .encontrarHistorialPrestamoPorIdCliente(cliente.getId()), HttpStatus.OK);
    }
}
