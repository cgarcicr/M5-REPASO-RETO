package com.bancolombia.aplicacionbancaria.model;
import jakarta.validation.constraints.NotNull;

public class ClienteRequest {

    @NotNull(message = "El valor no puede ser nulo")
    private Long id;

    public ClienteRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteRequest() {
    }
}
