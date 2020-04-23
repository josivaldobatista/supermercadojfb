package com.jfb.cursomc.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jfb.cursomc.api.domain.Cidade;
import com.jfb.cursomc.api.domain.Cliente;
import com.jfb.cursomc.api.domain.Endereco;
import com.jfb.cursomc.api.domain.enums.Perfil;
import com.jfb.cursomc.api.domain.enums.TipoCliente;
import com.jfb.cursomc.api.dto.ClienteDTO;
import com.jfb.cursomc.api.dto.ClienteNewDTO;
import com.jfb.cursomc.api.repositories.ClienteRepository;
import com.jfb.cursomc.api.repositories.EnderecoRepository;
import com.jfb.cursomc.api.security.UserSS;
import com.jfb.cursomc.api.services.exceptions.AuthorizationException;
import com.jfb.cursomc.api.services.exceptions.DataIntegrityException;
import com.jfb.cursomc.api.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente find(final Integer id) {
		UserSS user = UserService.authenticated();
		
		if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado!");
		}

		final Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objecto não encontrado! ID: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null); // Garantindo que é uma inserção.
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public Cliente update(final Cliente obj) {
		final Cliente newObj = find(obj.getId()); // Aqui estou verificando se o objeto existe.
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(final Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (final DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque há pedidos relacionadas.");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Page<Cliente> findPage(final Integer page, final Integer linesPerPage, final String orderBy,
			final String direction) {
		final PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	// Metódo para instanciar uma Cliente a parti de um DTO(não de um banco de
	// dados).
	public Cliente fromDTO(final ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null);
	}

	public Cliente fromDTO(final ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(),
				TipoCliente.toEnum(objDto.getTipo()), pe.encode(objDto.getSenha()));

		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);

		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), cli, cid);
				
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());

		if (objDto.getTelefone2() != null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3() != null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		return cli;
	}

	/**
	 * 
	 * Método para instanciar o objeto do banco de dados e atualizar com os novos
	 * dados que veram da requisição.
	 * 
	 * @param newObj = que veio do banco de dados.
	 * @param obj    = que veio da requisição.
	 */
	private void updateData(final Cliente newObj, final Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}