package com.jfb.cursomc.api.services;

import java.util.Optional;

import com.jfb.cursomc.api.domain.Categoria;
import com.jfb.cursomc.api.repositories.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria findById(Integer id) {
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElse(null);
    }
}