package com.bmsantana.tokenvalidation.service.Impl;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.transaction.annotation.Transactional;

import com.bmsantana.tokenvalidation.exception.RegraNegocioException;
import com.bmsantana.tokenvalidation.model.entity.Usuario;
import com.bmsantana.tokenvalidation.model.enums.StatusAutenticacao;
import com.bmsantana.tokenvalidation.model.repository.UsuarioRepository;
import com.bmsantana.tokenvalidation.service.UsuarioService;

public class UsuarioServiceImp implements UsuarioService{
	
	private UsuarioRepository repository;

	public UsuarioServiceImp(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	@Transactional
	public Usuario salvar(Usuario usuario) {
		validar(usuario);
		usuario.setStatus(StatusAutenticacao.PENDENTE);
		return repository.save(usuario);
	}
	
	@Override
	@Transactional
	public void validar(Usuario usuario) {
		String mail = usuario.getEmail();
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher m = p.matcher(mail);
		boolean matchFound = m.matches();
		
		if (usuario.getNome() == null || usuario.getNome().trim().equals("")) {
			throw new RegraNegocioException("Informe um nome válido!");
		}
		
		if (usuario.getEmail() == null || usuario.getEmail().trim().equals("") || !matchFound) {
			throw new RegraNegocioException("Informe um email válido!");
		}
		
		if (usuario.getTelefone() == null || usuario.getTelefone().trim().equals("")) {
			throw new RegraNegocioException("Informe um telefone válido!");
		}		
	}

	@Override
	@Transactional
	public Usuario atualizar(Usuario usuario) {
		Objects.requireNonNull(usuario.getId());
		validar(usuario);
		repository.save(usuario);
		return null;
	}
	
	@Override
	@Transactional
	public void atualizarStatus(Usuario usuario, StatusAutenticacao status) {
		usuario.setStatus(status);
		atualizar(usuario);
		
	}

}
