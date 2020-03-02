package com.bmsantana.tokenvalidation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmsantana.tokenvalidation.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	boolean existsByEmail(String email);

	Usuario findByEmail(String email);

}
