package com.bancolombia.aplicacionbancaria.repository;

import com.bancolombia.aplicacionbancaria.model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
