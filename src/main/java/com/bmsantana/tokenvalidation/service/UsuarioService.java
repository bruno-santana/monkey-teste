package com.bmsantana.tokenvalidation.service;


import com.bmsantana.tokenvalidation.enums.StatusAutenticacao;
import com.bmsantana.tokenvalidation.model.Usuario;

public interface UsuarioService {
	
	Usuario salvar(Usuario usuario);

	Usuario atualizar(Usuario usuario);
	
	void validarEmail(String email);
		
	void atualizarStatus(Usuario usuario, StatusAutenticacao status);

	Usuario buscarPorEmail(String email);

}
