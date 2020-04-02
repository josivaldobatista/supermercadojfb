package com.jfb.cursomc.api.services;

import java.util.List;
import java.util.Optional;

import com.jfb.cursomc.api.domain.Categoria;
import com.jfb.cursomc.api.dto.CategoriaDTO;
import com.jfb.cursomc.api.repositories.CategoriaRepository;
import com.jfb.cursomc.api.services.exceptions.DataIntegrityException;
import com.jfb.cursomc.api.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria find(final Integer id) {
        final Optional<Categoria> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objecto não encontrado! ID: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insert(final Categoria obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Categoria update(final Categoria obj) {
		final Categoria newObj = find(obj.getId()); // Aqui estou verificando se o objeto existe.
		updateData(newObj, obj);
		return repo.save(newObj);
	}

    public void delete(final Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (final DataIntegrityViolationException e) {
            throw new DataIntegrityException(
                "Não é possivel excluir uma categoria que possui produtos.");
        }
    }

    public List<Categoria> findAll () {
        return repo.findAll();
    }

    public Page<Categoria> findPage(final Integer page, final Integer linesPerPage, final String orderBy, final String direction) {
        final PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    // Metódo para instanciar uma Categoria a parti de um DTO.
    public Categoria fromDTO(final CategoriaDTO objDto) {
        return new Categoria(objDto.getId(), objDto.getNome());
    }

    private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}
}