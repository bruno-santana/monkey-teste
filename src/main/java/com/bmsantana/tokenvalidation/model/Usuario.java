package com.bmsantana.tokenvalidation.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.bmsantana.tokenvalidation.dto.UsuarioDTO;
import com.bmsantana.tokenvalidation.enums.StatusAutenticacao;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "usuario", schema = "autenticacao", 
uniqueConstraints = @UniqueConstraint(columnNames = "email", name = "uk_email"))
public class Usuario {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name= "email")
	private String email;
	
	@Column(name= "telefone")
	private String telefone;
	
	@JsonIgnore
	@Column(name= "senha")
	private String senha;
	
	@Column(name = "data_cadastro")
	private LocalDateTime dataCadastro;
	
	@Column(name = "status")
	@Enumerated(value = EnumType.STRING)
	private StatusAutenticacao status;
	
	public Usuario(UsuarioDTO dto) {
		setNome(dto.getNome());
		setEmail(dto.getEmail());
		setTelefone(dto.getTelefone());
	}
}
