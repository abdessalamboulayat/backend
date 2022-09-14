package com.example.form.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SpringMailer {
	
	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMail(String nom, String prenom, String email,String subject, String password) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("abdox0higo@gmail.com");
		simpleMailMessage.setTo(email);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText("Cher Safir " + nom + " "+ prenom + ",\n Votre compte est crée avec succée \n Email: " + email + "\n Mot de passe: " +password);
		this.javaMailSender.send(simpleMailMessage);
		System.out.println("message sent");
	}
	
	public String randomPassword(int length) {
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    Random rnd = new Random();

	    StringBuilder sb = new StringBuilder(length);
	    for (int i = 0; i < length; i++) {
	        sb.append(AB.charAt(rnd.nextInt(AB.length())));
	    }
	    return sb.toString();
	}

}
