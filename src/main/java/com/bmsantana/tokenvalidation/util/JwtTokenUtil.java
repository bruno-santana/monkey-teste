package com.bmsantana.tokenvalidation.util;

import java.time.ZonedDateTime;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenUtil {
	public static String key = "86b15bfc1eb73ca2c7da5c04151d15ca";
	
	public static final String create(String subject) {
		Date dataCriacao = Date.from(ZonedDateTime.now().toInstant());
		Date dataExpiracao = Date.from(ZonedDateTime.now().plusMinutes(10).toInstant());
		
		return Jwts.builder().setSubject(subject).setIssuedAt(dataCriacao)
				.setExpiration(dataExpiracao).signWith(SignatureAlgorithm.HS512, key).compact();
	}

	public static Jws<Claims> decode(String token){
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token);
	}
	

}
