package com.bmsantana.tokenvalidation.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bmsantana.tokenvalidation.model.Usuario;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryTest {
	
	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	TestEntityManager entityManager;
	
	public static Usuario criarUsuario() {
		return Usuario.builder()
				.nome("usuario")
				.email("usuario@email.com")
				.telefone("0123456789")
				.build();
	}
	
	@Test
	public void verificaEmail() {
		//Deve verificar a existencia de um email
		
		//cenário
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);
		
		//ação-execução
		boolean result = repository.existsByEmail("usuario@email.com");
		
		//verificação
		Assertions.assertThat(result).isTrue();
		
	}
	
	@Test
	public void retornaFalsoVerificacaoEmail() {
		//Deve retornar falso quando não houver usuário cadastrado com o email
		
		//cenário
		
		
		//ação-execução
		boolean result = repository.existsByEmail("usuario@email.com");
		
		//verificação
		Assertions.assertThat(result).isFalse();
	}
	
	@Test
	public void persistirUsuario() {
		//Deve persistir um usuário na base de dados
		
		//cenário
		Usuario usuario = criarUsuario();
		
		//acão
		Usuario usuarioSalvo = repository.save(usuario);
		
		//verificação
		Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
	}
	
	

}
