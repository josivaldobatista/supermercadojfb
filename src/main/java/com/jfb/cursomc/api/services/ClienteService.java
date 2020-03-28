package com.jfb.cursomc.api.services;

import java.util.Optional;

import com.jfb.cursomc.api.domain.Cliente;
import com.jfb.cursomc.api.repositories.ClienteRepository;
import com.jfb.cursomc.api.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objecto não encontrado! ID: " + id + ", Tipo: " + Cliente.class.getName()));

    }
}