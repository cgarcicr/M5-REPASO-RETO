package com.bancolombia.aplicacionbancaria.service;

import com.bancolombia.aplicacionbancaria.model.*;
import com.bancolombia.aplicacionbancaria.repository.ClienteRepository;
import com.bancolombia.aplicacionbancaria.repository.PrestamoHistorialRepository;
import com.bancolombia.aplicacionbancaria.repository.PrestamoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Service
public class PrestamoService {

    private final PrestamoRepository repository;
    private final ClienteRepository clienteRepository;
    private final PrestamoHistorialRepository historialPrestamoRepository;

    public PrestamoService(PrestamoRepository repository, ClienteRepository clienteRepository,
                           PrestamoHistorialRepository historialPrestamoRepository) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.historialPrestamoRepository = historialPrestamoRepository;
    }

    public boolean validarSaldo(BigDecimal valor){
        if(valor.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Saldo debe ser positivo");
        }
        return true;
    }


    public void guardarPrestamo(PrestamoEntity prestamo){
        PrestamoHistorialEntity historialPrestamoEntity = new PrestamoHistorialEntity();
        historialPrestamoEntity.setPrestamo(prestamo);
        historialPrestamoEntity.setMonto(prestamo.getMonto());
        historialPrestamoEntity.setEstado(prestamo.getEstado());
        historialPrestamoEntity.setFechaCreacion(new Date());
        historialPrestamoEntity.setFechaActualizacion(new Date());
        historialPrestamoRepository.save(historialPrestamoEntity);
    }

    public PrestamoEntity actualizarPrestamo(PrestamoRequestEncontrar prestamo){
        PrestamoEntity prestamoEntity = repository.findById(prestamo.getId()).orElseThrow(()
                -> new NullPointerException("Préstamo no existe"));
        prestamoEntity.setEstado("aprobado");
        prestamoEntity = repository.save(prestamoEntity);
        actualizarHistorialPrestamo(prestamoEntity.getId());
        return prestamoEntity;
    }

    public void actualizarHistorialPrestamo(Long idPrestamo){
        PrestamoHistorialEntity historialPrestamoEntity = historialPrestamoRepository
                .encontrarPrestamoHistorialPorIdPrestamo(idPrestamo);
        if(historialPrestamoEntity != null){
            historialPrestamoEntity.setEstado("APROBADO");
            historialPrestamoEntity.setFechaActualizacion(new Date());
            historialPrestamoRepository.save(historialPrestamoEntity);
        }else{
            throw new NullPointerException("Historial del préstamo no existe");
        }
    }

    public PrestamoEntity encontrarPrestamoPorId(Long id) {
        return repository.findById(id).orElseThrow(()
                -> new NullPointerException("Préstamo no existe"));
    }

    public BigDecimal obtenerSimulacion(Long id){
        PrestamoEntity prestamoEntity = repository.findById(id).orElseThrow(()
                -> new NullPointerException("Préstamo no existe"));
        BigDecimal interesMensual = BigDecimal.valueOf(prestamoEntity.getInteres()).divide(BigDecimal.valueOf(12),
                2, RoundingMode.HALF_UP);
        BigDecimal monto = prestamoEntity.getMonto();
        BigDecimal montoMensual = monto.divide(BigDecimal.valueOf(prestamoEntity.getDuracionMeses()), 2,
                RoundingMode.HALF_UP);
        BigDecimal valorInteres= montoMensual.multiply(interesMensual);
        return montoMensual.add(valorInteres.setScale(2, RoundingMode.HALF_UP));
    }

    public PrestamoEntity guardarPrestamo(PrestamoRequest prestamo){
        PrestamoEntity prestamoEntity = new PrestamoEntity();
        ClienteEntity cliente = clienteRepository.findById(prestamo.getIdCliente()).orElseThrow(()
                -> new NullPointerException("El cliente no existe"));
        if(validarSaldo(prestamo.getMonto())){
            prestamoEntity = new PrestamoEntity();
            prestamoEntity.setMonto(prestamo.getMonto());
            prestamoEntity.setInteres(prestamo.getInteres());
            prestamoEntity.setDuracionMeses(prestamo.getDuracionMeses());
            prestamoEntity.setEstado("pendiente");
            prestamoEntity.setFechaCreacion(new Date());
            prestamoEntity.setCliente(cliente);
            prestamoEntity = repository.save(prestamoEntity);
            guardarPrestamo(prestamoEntity);
        }
        return prestamoEntity;
    }

}
