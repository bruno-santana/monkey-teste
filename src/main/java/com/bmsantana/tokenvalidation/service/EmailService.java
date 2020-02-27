package com.bmsantana.tokenvalidation.service;

import javax.mail.MessagingException;

import com.bmsantana.tokenvalidation.model.Usuario;

public interface EmailService {

	void sendEmail(Usuario usuario) throws MessagingException;
}
