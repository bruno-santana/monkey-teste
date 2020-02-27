package com.bmsantana.tokenvalidation.service.Impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.bmsantana.tokenvalidation.model.Usuario;
import com.bmsantana.tokenvalidation.service.EmailService;
import com.bmsantana.tokenvalidation.util.JwtTokenUtil;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmail(Usuario usuario) throws MessagingException {

		String link = "http://localhost:8080/api/usuarios/ativacao?token=" + JwtTokenUtil.create(usuario.getEmail());

		MimeMessage msg = javaMailSender.createMimeMessage();

		// true = multipart message
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		helper.setTo(usuario.getEmail());

		helper.setSubject("Ative sua conta");
		helper.setText("Para ativar sua conta, <a href='" + link + "'>clique aqui</a>", true);

		javaMailSender.send(msg);

	}

}
