package com.example.form.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.form.bean.Safir;

public interface SafirService {

	Safir findByEmailSafir(String email);
	Safir addRoleToUser(String username, String authority);
	String saveFile(MultipartFile file);
}
