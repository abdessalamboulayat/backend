package com.example.form.service;

import com.example.form.bean.Evenement;
import com.example.form.dto.EvenementDto;
import com.example.form.repository.EvenementRepo;

import org.apache.logging.log4j.util.Strings;
import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class EvenementService {

	private String UPLOAD_FILE = System.getProperty("user.dir")+"/src/main/resources/static/images/evenements/";
	@Autowired
    private EvenementRepo evenementRepo;

    public  long count() {
        return evenementRepo.count();
    }

    public List<Evenement> OrderByDate() {
        List<Evenement> recentEvenement = evenementRepo.OrderByDate();

        List<Evenement> newevent =  new ArrayList<Evenement>();
        for(int i =0; i <3; i++){
            newevent.add(recentEvenement.get(i));

        }
        return newevent ;

    }

    /*public int save(EvenementDto evenement) throws IOException {
       
        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");

        if (findByTitre(evenement.getTitre()) != null) {
            return -1;
        } 
        else {
            Path path = Paths.get(UPLOAD_FILE,evenement.getFile().getOriginalFilename());
            Files.write(path, evenement.getFile().getBytes());
            Evenement evenement1= new Evenement();
            //evenement1.setDate(evenement.getDate());
            evenement1.setTexte(evenement.getTexte());
            evenement1.setTitre(evenement.getTitre());
            evenement1.setImage(evenement.getFile().getOriginalFilename());
            evenementRepo.save(evenement1);
            return 1;

        }
    }*/
    
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

    /*public int update(Evenement evenement) {
        Evenement evenement1 = evenementRepo.getById(evenement.getId());
        if (evenement1 == null)
            return -1;
        else {
            evenement1.setDate(evenement.getDate());
            evenement1.setTexte(evenement.getTexte());
            evenement1.setTitre(evenement.getTitre());
            evenementRepo.save(evenement1);
            return 1;
        }
    }*/


    public List<Evenement> findByAdminEmail(String email) {

      return evenementRepo.findBySafirUsername(email);
    }

    public Evenement findByTitre(String titre) {

        return evenementRepo.findByTitre(titre);
    }


    public List<Evenement> findAll() {

        return evenementRepo.findAll();
    }


    public List<Evenement> findByDate(Date date) {

        return evenementRepo.findByDate(date);
    }
    
    /*@Transactional
    public int deleteByTitre(String titre) {
        return evenementRepo.deleteByTitre(titre);
    }*/
}
