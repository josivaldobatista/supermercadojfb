package com.jfb.cursomc.api.repositories;

import com.jfb.cursomc.api.domain.Produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}