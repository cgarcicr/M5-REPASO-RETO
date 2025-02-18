package com.bancolombia.aplicacionbancaria.repository;

import com.bancolombia.aplicacionbancaria.model.PrestamoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamoRepository extends JpaRepository<PrestamoEntity, Long> {
}
