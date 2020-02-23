package com.bmsantana.tokenvalidation.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmsantana.tokenvalidation.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
