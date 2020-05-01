package com.jfb.cursomc.api.services;

import java.util.List;

import com.jfb.cursomc.api.domain.Estado;
import com.jfb.cursomc.api.repositories.EstadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {
    
    @Autowired
    private EstadoRepository repo;

    public List<Estado> findAll() {
        return repo.findAllByOrderByNome();
    }
}