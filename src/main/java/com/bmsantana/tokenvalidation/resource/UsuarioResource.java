package com.bmsantana.tokenvalidation.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bmsantana.tokenvalidation.dto.UsuarioDTO;
import com.bmsantana.tokenvalidation.enums.StatusAutenticacao;
import com.bmsantana.tokenvalidation.exception.RegraNegocioException;
import com.bmsantana.tokenvalidation.model.Usuario;
import com.bmsantana.tokenvalidation.service.UsuarioService;
import com.bmsantana.tokenvalidation.util.JwtTokenUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;	
	
	@PostMapping
	public ResponseEntity salvar(@Valid @RequestBody UsuarioDTO dto ) {
		try {			
			Usuario entidade = new Usuario(dto);
			entidade = service.salvar(entidade);
			return new ResponseEntity(entidade, HttpStatus.CREATED);
			
			
		} catch (RegraNegocioException e) {
			
			return ResponseEntity.badRequest().body(e.getMessage());			
		}
	}
	
	@GetMapping("/ativacao")
	public ResponseEntity validar(@RequestParam (value="token", required=true) String token) {
		try {
			Jws<Claims> claims = JwtTokenUtil.decode(token);
			
			String email = Jwts.parser().setSigningKey("86b15bfc1eb73ca2c7da5c04151d15ca").parseClaimsJws(token).getBody().getSubject();
			
			Usuario usuario = service.buscarPorEmail(email);
			
			if(usuario != null) {
				usuario.setStatus(StatusAutenticacao.ATIVO);
				service.atualizar(usuario);
				return new ResponseEntity<>("Usuário ativado com sucesso", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Usuário não existe", HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>("Email não existe", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	

}
