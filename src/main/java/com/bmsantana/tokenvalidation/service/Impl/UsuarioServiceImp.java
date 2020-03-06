package com.bmsantana.tokenvalidation.service.Impl;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bmsantana.tokenvalidation.enums.StatusAutenticacao;
import com.bmsantana.tokenvalidation.exception.RegraNegocioException;
import com.bmsantana.tokenvalidation.model.Usuario;
import com.bmsantana.tokenvalidation.repository.UsuarioRepository;
import com.bmsantana.tokenvalidation.service.EmailService;
import com.bmsantana.tokenvalidation.service.UsuarioService;

@Service
public class UsuarioServiceImp implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private EmailService emailService;

	@Override
	@Transactional
	public Usuario salvar(Usuario usuario) {
		validarEmail(usuario.getEmail());
		usuario.setStatus(StatusAutenticacao.PENDENTE);
		usuario.setDataCadastro(LocalDateTime.now());
		usuario = repository.save(usuario);
		try {
			emailService.sendEmail(usuario);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuario;
	}

	@Override
	@Transactional
	public Usuario atualizar(Usuario usuario) {
		Objects.requireNonNull(usuario.getId());
		return repository.save(usuario);
	}

	@Override
	@Transactional
	public void atualizarStatus(Usuario usuario, StatusAutenticacao status) {
		usuario.setStatus(status);
		atualizar(usuario);

	}

	@Override
	public void validarEmail(String email) {
		boolean existe = repository.existsByEmail(email);
		if(existe) {
			throw new RegraNegocioException("Já existe um usuário cadastrado com este email!");
		}		
	}

	@Override
	public Usuario buscarPorEmail(String email) {
		return repository.findByEmail(email);
	}
	
}
