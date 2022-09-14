package com.example.form.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.form.bean.Authority;
import com.example.form.bean.Safir;
import com.example.form.repository.AuthorityRepo;
import com.example.form.repository.SafirRepo;

@Service
@Transactional
public class SafirServiceImp implements SafirService{

	private String UPLOAD_FILE = System.getProperty("user.dir")+"/src/main/resources/static/images/activiteSafir/";
	@Autowired
	private SafirRepo safirRepo;
	@Autowired
	private AuthorityRepo authorityRepo;
	
	@Override
	public Safir findByEmailSafir(String email) {
		return safirRepo.findByUsername(email);
	}

	@Override
	public Safir addRoleToUser(String username, String authority) {
		Safir safir = safirRepo.findByUsername(username);
		Authority role = authorityRepo.findByAuthority(authority);
		safir.getAuthorities().add(role);
		return safir;
	}

	@Override
	public String saveFile(MultipartFile file) {
		try {
    		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
    		String newFileName = dateFormat.format(new Date())+file.getOriginalFilename();
    		byte[] bytes = file.getBytes();
    		Path path = Paths.get(UPLOAD_FILE+newFileName);
    		Files.write(path, bytes);
    		return newFileName;
    	}
    	catch(Exception ex) {
    		return null;
    	}
	}

}
