package com.example.form.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.form.bean.Safir;
import com.example.form.repository.SafirRepo;

@Service
public class AdminServiceImp implements AdminService{

	@Autowired
	private SafirRepo safirRepo;
	@Autowired
	private SafirService safirService;
	@Autowired
	private SpringMailer springMailer;
	@Autowired
	private PasswordService passwordService;
	
	@Override
	public String addNewSafir(Safir safir) {
		
		if(safirRepo.findByUsername(safir.getUsername()) != null) {
			return "Compte existe déjà";
		}
		else {
			String password = springMailer.randomPassword(8);
			String subject = "Cordonnées d'accés à votre compte Safir";
				
			safir.setPassword(passwordService.passwordEncoder().encode(password));
			safir.setIsSafir(true);
			safirRepo.save(safir);
			safirService.addRoleToUser(safir.getUsername(), "Safir");
			springMailer.sendMail(
					safir.getNomSafir(),
					safir.getPrenomSafir(),
					safir.getUsername(),
					subject,
					password
					);
			return "Compte crée avec succés"; 
		}
	}
	
	/*public String getImage(String imageName)  {
		String fileDir = System.getProperty("user.dir")+"/src/main/resources/static/images/";
        Path path = Paths.get(fileDir, imageName);
        try {
            byte[] bytes = Files.readAllBytes(path);
            String base64 = Base64.getEncoder().encodeToString(bytes);
            String extension = imageName.split("[.]")[1];
            String imgData = "data:image/" + extension + ";base64," + base64;
            return  imgData;
        } catch (IOException err){
            return "file not found";
        }
	}*/

}
