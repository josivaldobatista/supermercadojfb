package com.jfb.cursomc.api.resources;

import java.util.ArrayList;
import java.util.List;

import com.jfb.cursomc.api.domain.Categoria;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @GetMapping
    public List<Categoria> findById() {
        Categoria cat1 = new Categoria(1, "Maria da Silava");
        Categoria cat2 = new Categoria(2, "José Albuquerque");
        List<Categoria> lista = new ArrayList<>();
        lista.add(cat1);
        lista.add(cat2);
        return lista;
    }

}