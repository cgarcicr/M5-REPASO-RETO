package com.bancolombia.aplicacionbancaria.model;

import jakarta.validation.constraints.NotNull;

public class PrestamoRequestEncontrar {


    @NotNull(message = "El id no puede ser nulo")
    private Long id;

    public PrestamoRequestEncontrar(Long id) {
        this.id = id;
    }

    public PrestamoRequestEncontrar() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
