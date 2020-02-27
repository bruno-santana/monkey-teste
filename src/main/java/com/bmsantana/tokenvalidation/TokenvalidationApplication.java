package com.bmsantana.tokenvalidation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class TokenvalidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokenvalidationApplication.class, args);
	}

}
