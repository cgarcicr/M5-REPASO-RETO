package com.bancolombia.aplicacionbancaria.service;
import com.bancolombia.aplicacionbancaria.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }
}
