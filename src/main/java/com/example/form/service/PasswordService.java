package com.example.form.service;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class PasswordService {

	private PasswordEncoder passwordEncoder;
	
	
	
	public PasswordService() {
		super();
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
