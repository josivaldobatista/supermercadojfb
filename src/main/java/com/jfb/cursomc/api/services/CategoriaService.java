package com.jfb.cursomc.api.services;

import java.util.List;
import java.util.Optional;

import com.jfb.cursomc.api.domain.Categoria;
import com.jfb.cursomc.api.repositories.CategoriaRepository;
import com.jfb.cursomc.api.services.exceptions.DataIntegrityException;
import com.jfb.cursomc.api.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria find(Integer id) {
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objecto não encontrado! ID: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insert(Categoria obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Categoria update(Categoria obj) {
        find(obj.getId()); // Aqui estou verificando se o objeto existe.
        return repo.save(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(
                "Não é possivel excluir uma categoria que possui produtos.");
        }
    }

    public List<Categoria> findAll () {
        return repo.findAll();
    }
}