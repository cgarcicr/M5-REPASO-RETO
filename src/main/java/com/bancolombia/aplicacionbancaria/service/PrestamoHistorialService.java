package com.bancolombia.aplicacionbancaria.service;

import com.bancolombia.aplicacionbancaria.model.PrestamoHistorialEntity;
import com.bancolombia.aplicacionbancaria.repository.PrestamoHistorialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestamoHistorialService {

    private final PrestamoHistorialRepository repository;

    public PrestamoHistorialService(PrestamoHistorialRepository repository) {
        this.repository = repository;
    }

    public List<PrestamoHistorialEntity> encontrarHistorialPrestamoPorIdClienteUltimos3(Long idCliente){
        List<PrestamoHistorialEntity>  historialPrestamoEntity =
                repository.encontrarPrestamoHistorialByIdClienteUltimos3(idCliente);
        if(historialPrestamoEntity != null && !historialPrestamoEntity.isEmpty()){
            return historialPrestamoEntity;
        } else {
            throw new NullPointerException("No existe el historial de prestamo por cliente");
        }
    }

    public List<PrestamoHistorialEntity> encontrarHistorialPrestamoPorIdCliente(Long idCliente){
        List<PrestamoHistorialEntity>  historialPrestamoEntity =
                repository.encontrarPrestamoHistorialPorIdCliente(idCliente);
        if(historialPrestamoEntity != null && !historialPrestamoEntity.isEmpty()){
            return historialPrestamoEntity;
        } else {
            throw new NullPointerException("Historial de prestamo por cliente no existe");
        }
    }
}
