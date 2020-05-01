package com.jfb.cursomc.api.services;

import java.util.List;

import com.jfb.cursomc.api.domain.Cidade;
import com.jfb.cursomc.api.repositories.CidadeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repo;

    public List<Cidade> findByEstado(Integer estadoId) {
        return repo.findCidades(estadoId);
    }
}