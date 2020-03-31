package com.jfb.cursomc.api.services;

import java.util.Optional;

import com.jfb.cursomc.api.domain.Pedido;
import com.jfb.cursomc.api.repositories.PedidoRepository;
import com.jfb.cursomc.api.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    public Pedido find(Integer id) {
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objecto não encontrado! ID: " + id + ", Tipo: " + Pedido.class.getName()));
    }

}