package com.jfb.cursomc.api.repositories;

import java.util.List;

import com.jfb.cursomc.api.domain.Estado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

    @Transactional(readOnly = true)
    public List<Estado> findAllByOrderByNome();

}