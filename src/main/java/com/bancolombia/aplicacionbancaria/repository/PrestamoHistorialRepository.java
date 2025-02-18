package com.bancolombia.aplicacionbancaria.repository;

import com.bancolombia.aplicacionbancaria.model.PrestamoHistorialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrestamoHistorialRepository extends JpaRepository<PrestamoHistorialEntity, Long> {

    @Query(value = "SELECT hp.* FROM historial_prestamo hp " +
            "INNER JOIN prestamo p " +
            "ON hp.id_prestamo = p.id " +
            "WHERE p.id_cliente = :IdCliente", nativeQuery = true)
    List<PrestamoHistorialEntity> encontrarPrestamoHistorialPorIdCliente(@Param("IdCliente") Long IdCliente);

    @Query(value = "SELECT * FROM historial_prestamo WHERE id_prestamo = :idPrestamo", nativeQuery = true)
    PrestamoHistorialEntity encontrarPrestamoHistorialPorIdPrestamo(@Param("idPrestamo") Long idPrestamo);

    @Query(value = "SELECT hp.* FROM historial_prestamo hp " +
            "INNER JOIN prestamo p " +
            "ON hp.id_prestamo = p.id " +
            "WHERE p.id_cliente = :IdCliente " +
            "LIMIT 3", nativeQuery = true)
    List<PrestamoHistorialEntity> encontrarPrestamoHistorialByIdClienteUltimos3(@Param("IdCliente") Long IdCliente);


}
