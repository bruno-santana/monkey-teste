package com.bmsantana.tokenvalidation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmsantana.tokenvalidation.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findByEmail(String email);

}
