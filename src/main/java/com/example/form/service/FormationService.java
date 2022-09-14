package com.example.form.service;

import com.example.form.bean.Evenement;
import com.example.form.bean.Formation;
import com.example.form.dto.EvenementDto;
import com.example.form.dto.FormationDto;
import com.example.form.repository.FormationRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class FormationService {

    @Autowired
    private FormationRepo formationRepo;
    
    public long count() {

        return formationRepo.count();
    }

    private String UPLOAD_FILE = System.getProperty("user.dir")+"/src/main/resources/static/images/formations/";

    public List<Formation> findByDate(Date date) {

        return formationRepo.findByDate(date);
    }
    
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
    
    public String getImage(String imageName){
        Path path = Paths.get(UPLOAD_FILE, imageName);
        try {
            byte[] bytes = Files.readAllBytes(path);
            String base64 = Base64.getEncoder().encodeToString(bytes);
            String extension = imageName.split("[.]")[1];
            String imgData = "data:image/" + extension + ";base64," + base64;
            return  imgData;
        } catch (IOException err){
            return "file not found";
        }
    }

    public List<Formation> findByAdminEmail(String email) {

        return formationRepo.findByAdminUsername(email);
    }

    public Formation findByTitre(String titre) {

        return formationRepo.findByTitre(titre);
    }


    public List<Formation> findAll() {
        return formationRepo.findAll();
    }
    
}





