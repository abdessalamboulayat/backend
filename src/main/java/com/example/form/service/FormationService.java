package com.example.form.service;

import com.example.form.bean.Evenement;
import com.example.form.bean.Formation;
import com.example.form.dao.AdminDao;
import com.example.form.dao.FormationDao;
import com.example.form.dao.FormulaireDao;
import com.example.form.dto.EvenementDto;
import com.example.form.dto.FormationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class FormationService {




    @Autowired
    private FormationDao formationDao;
    @Autowired
    private AdminDao adminDao;



    public long count() {

        return formationDao.count();
    }


    private String fileDir = System.getProperty("user.dir")+"/src/main/resources/static/images";


    public List<Formation> findByDate(Date date) {

        return formationDao.findByDate(date);
    }


    public int save(FormationDto formation) throws IOException {


        if (findByTitre(formation.getTitre()) != null) {
            return -1;
        } //else if (evenement.getDate().compareTo(new Date()) < 0) {
        //return -2;
        //}
        else {
            Path path = Paths.get(fileDir,formation.getFile().getOriginalFilename());
            Files.write(path, formation.getFile().getBytes());
            Formation formation1= new Formation();
            formation1.setDate(formation.getDate());
            formation1.setTexte(formation.getTexte());
            formation1.setTitre(formation.getTitre());
            formation1.setImage(formation.getFile().getOriginalFilename());
            /*evenement1.setAdmin(adminDao.findByEmail(evenement.getAdmin().getEmail()));//*/
            formationDao.save(formation1);
            return 1;

        }
    }

    public int update(Evenement evenement) {
        Formation formation1 = formationDao.getById(evenement.getId());
        if (formation1 == null)
            return -1;
        else {
            formation1.setDate(evenement.getDate());
            formation1.setTexte(evenement.getTexte());
            formation1.setTitre(evenement.getTitre());
            formationDao.save(formation1);
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









    public List<Formation> findByAdminEmail(String email) {

        return formationDao.findByAdminEmail(email);
    }

    public Formation findByTitre(String titre) {

        return formationDao.findByTitre(titre);
    }


    public List<Formation> findAll() {
//recuperer les images

        File file =new File(fileDir);

        return formationDao.findAll();
    }


    @Transactional
    public int deleteByTitre(String titre) {
        return formationDao.deleteByTitre(titre);
    }
}





