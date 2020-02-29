package com.bmsantana.tokenvalidation.dto;

import javax.validation.constraints.Email;

import org.hibernate.annotations.NotFound;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDTO {
	
	@NotEmpty(message = "Informe um nome válido!")
	private String nome;
	
	@Email
	@NotEmpty(message = "Informe um email válido!")
	private String email;
	
	@NotEmpty(message = "Informe um telefone válido!")
	private String telefone;

}
