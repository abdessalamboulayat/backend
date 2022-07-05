package com.example.form.ws;

import com.example.form.bean.Formation;
import com.example.form.dto.FormationDto;
import com.example.form.service.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("api/v1/formation")
public class FormationWs {

    @RequestMapping(path = "/", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public int save(@ModelAttribute FormationDto formation) throws IOException {
        return formationService.save(formation);
    }

    @GetMapping("/{date}/date")
    public List<Formation> findByDate(Date date) {
        return formationService.findByDate(date);
    }

    @GetMapping("/{email}/email")
    public List<Formation> findByAdminEmail(String email) {
        return formationService.findByAdminEmail(email);
    }

    @GetMapping("/{titre}/titre")
    public Formation findByTitre(String titre) {
        return formationService.findByTitre(titre);
    }

    @Transactional
    @DeleteMapping("/{Titre}/titre")
    public int deleteByTitre(String titre) {
        return formationService.deleteByTitre(titre);
    }

    @GetMapping("/")
    public List<Formation> findAll() {
        return formationService.findAll();
    }

    @GetMapping("/images/{imageName}")
    public String getImage(@PathVariable String imageName) {
        return formationService.getImage(imageName);
    }

    @GetMapping("/count/")
    public long count() {
        return formationService.count();
    }


    @Autowired
    private FormationService formationService;

}
