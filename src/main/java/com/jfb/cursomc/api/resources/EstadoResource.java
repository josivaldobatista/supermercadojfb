package com.jfb.cursomc.api.resources;

import java.util.List;
import java.util.stream.Collectors;

import com.jfb.cursomc.api.domain.Cidade;
import com.jfb.cursomc.api.domain.Estado;
import com.jfb.cursomc.api.dto.CidadeDTO;
import com.jfb.cursomc.api.dto.EstadoDTO;
import com.jfb.cursomc.api.services.CidadeService;
import com.jfb.cursomc.api.services.EstadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estados")
public class EstadoResource {

    @Autowired
    private EstadoService service;

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<List<EstadoDTO>> findAll() {
        List<Estado> list = service.findAll();
        List<EstadoDTO> listDto = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/{estadoId}/cidades")
    public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId) {
        List<Cidade> list = cidadeService.findByEstado(estadoId);
        List<CidadeDTO> listDto = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);

    }

}