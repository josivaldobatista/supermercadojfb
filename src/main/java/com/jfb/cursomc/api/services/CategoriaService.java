package com.jfb.cursomc.api.services;

import java.util.Optional;

import com.jfb.cursomc.api.domain.Categoria;
import com.jfb.cursomc.api.repositories.CategoriaRepository;
import com.jfb.cursomc.api.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria findById(Integer id) {
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objecto não encontrado! ID: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insert(Categoria obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Categoria update(Categoria obj) {
        findById(obj.getId()); // Aqui estou verificando se o objeto existe.
        return repo.save(obj);
    }
}