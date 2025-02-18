package com.bancolombia.aplicacionbancaria.controller;

import com.bancolombia.aplicacionbancaria.model.PrestamoEntity;
import com.bancolombia.aplicacionbancaria.model.PrestamoRequest;
import com.bancolombia.aplicacionbancaria.model.PrestamoRequestEncontrar;
import com.bancolombia.aplicacionbancaria.service.PrestamoHistorialService;
import com.bancolombia.aplicacionbancaria.service.PrestamoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/prestamo")
public class PrestamoController {

    private final PrestamoService prestamoService;
    private final PrestamoHistorialService prestamoHistorialService;

    public PrestamoController(PrestamoService prestamoService, PrestamoHistorialService historialPrestamoService) {
        this.prestamoService = prestamoService;
        this.prestamoHistorialService = historialPrestamoService;
    }

    @PostMapping(path = "/aprobar")
    public ResponseEntity<Object> aprobacion(@Valid @RequestBody PrestamoRequestEncontrar prestamo){
        Map<String, Object> message = new HashMap<>();
        prestamoService.actualizarPrestamo(prestamo);
        message.put("message", "Préstamo aprobado exitosamente");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping(path = "/simular")
    public ResponseEntity<Object> simulacion(@Valid @RequestBody PrestamoRequestEncontrar prestamoRequest){
        Map<String, Object> message = new HashMap<>();
        message.put("Cuota", prestamoService.obtenerSimulacion(prestamoRequest.getId()));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping(path = "/consultarhistorial")
    public ResponseEntity<Object> consulta(@Valid @RequestBody PrestamoRequestEncontrar prestamoRequest){
        Map<String, Object> message = new HashMap<>();
        PrestamoEntity prestamo = prestamoService.encontrarPrestamoPorId(prestamoRequest.getId());
        message.put("prestamo", prestamo);
        message.put("historial", prestamoHistorialService
                .encontrarHistorialPrestamoPorIdClienteUltimos3(prestamo.getCliente().getId()));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping(path = "/solicitar")
    public ResponseEntity<Object> solicitar(@RequestBody PrestamoRequest prestamo){
        Map<String, Object> message = new HashMap<>();
        prestamoService.guardarPrestamo(prestamo);
        message.put("message", "Prestamos solicitado exitosamente");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
