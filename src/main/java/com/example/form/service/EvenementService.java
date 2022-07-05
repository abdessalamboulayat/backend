package com.example.form.service;

import com.example.form.bean.Evenement;
import com.example.form.dao.AdminDao;
import com.example.form.dao.EvenementDao;
import com.example.form.dto.EvenementDto;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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


    public  long count() {
        return evenementDao.count();
    }

    @Autowired
    private EvenementDao evenementDao;
    @Autowired
    private AdminDao adminDao;


    public List<Evenement> OrderByDate() {
        List<Evenement> recentEvenement = evenementDao.OrderByDate();

        List<Evenement> newevent =  new ArrayList<Evenement>();
        for(int i =0; i <3; i++){
            newevent.add(recentEvenement.get(i));

        }
        return newevent ;

    }




    private String fileDir = System.getProperty("user.dir")+"/src/main/resources/static/images";


    public int save(EvenementDto evenement) throws IOException {
       // Evenement evenement1 = evenementDao.getById(evenement.getId());
        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");

        if (findByTitre(evenement.getTitre()) != null) {
            return -1;
        } //else if (evenement.getDate().compareTo(new Date()) < 0) {
            //return -2;
        //}
        else {
            Path path = Paths.get(fileDir,evenement.getFile().getOriginalFilename());
            Files.write(path, evenement.getFile().getBytes());
            Evenement evenement1= new Evenement();
            evenement1.setDate(evenement.getDate());
            evenement1.setTexte(evenement.getTexte());
            evenement1.setTitre(evenement.getTitre());
            evenement1.setImage(evenement.getFile().getOriginalFilename());
            /*evenement1.setAdmin(adminDao.findByEmail(evenement.getAdmin().getEmail()));//*/
            evenementDao.save(evenement1);
            return 1;

        }
    }

    public String getImage(String imageName){
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
    }

    public int update(Evenement evenement) {
        Evenement evenement1 = evenementDao.getById(evenement.getId());
        if (evenement1 == null)
            return -1;
        else {
            evenement1.setDate(evenement.getDate());
            evenement1.setTexte(evenement.getTexte());
            evenement1.setTitre(evenement.getTitre());
            evenementDao.save(evenement1);
            return 1;
        }
    }


    public List<Evenement> findByAdminEmail(String email) {

      return evenementDao.findByAdminEmail(email);
    }

    public Evenement findByTitre(String titre) {

        return evenementDao.findByTitre(titre);
    }


    public List<Evenement> findAll() {
//recuperer les images

        File file =new File(fileDir);
        

        return evenementDao.findAll();
    }


    public List<Evenement> findByDate(Date date) {

        return evenementDao.findByDate(date);
    }

    @Transactional
    public int deleteByTitre(String titre) {
        return evenementDao.deleteByTitre(titre);
    }
}
