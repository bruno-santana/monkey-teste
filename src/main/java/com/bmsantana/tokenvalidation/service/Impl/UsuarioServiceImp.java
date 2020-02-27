package com.bmsantana.tokenvalidation.service.Impl;

import java.util.Objects;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bmsantana.tokenvalidation.enums.StatusAutenticacao;
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
		usuario.setStatus(StatusAutenticacao.PENDENTE);
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
		repository.save(usuario);
		return null;
	}

	@Override
	@Transactional
	public void atualizarStatus(Usuario usuario, StatusAutenticacao status) {
		usuario.setStatus(status);
		atualizar(usuario);

	}

	@Override
	public void validar(Usuario usuario) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public Usuario buscarPorEmail(String email) {
		return repository.findByEmail(email);
	}

	

}
