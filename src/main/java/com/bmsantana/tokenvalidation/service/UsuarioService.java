package com.bmsantana.tokenvalidation.service;

import com.bmsantana.tokenvalidation.model.entity.Usuario;
import com.bmsantana.tokenvalidation.model.enums.StatusAutenticacao;

public interface UsuarioService {
	
	Usuario salvar(Usuario usuario);

	Usuario atualizar(Usuario usuario);
	
	void validar(Usuario usuario);
		
	void atualizarStatus(Usuario usuario, StatusAutenticacao status);

}
