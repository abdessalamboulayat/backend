package com.example.form.ws;

import com.example.form.bean.Evenement;
import com.example.form.dao.EvenementDao;
import com.example.form.dto.EvenementDto;
import com.example.form.service.EvenementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("api/v1/evenement")
public class EvenementWs {




    @Autowired
    private EvenementService evenementService;

    @GetMapping("/count/")
    public long count() {
        return evenementService.count();
    }

    @GetMapping("/fistThree")
    public List<Evenement> OrderByDate() {
        return evenementService.OrderByDate();
    }

    @RequestMapping(path = "/", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE })
    public int save(@ModelAttribute EvenementDto evenement) throws IOException {

        return evenementService.save(evenement);
    }
    @GetMapping("/date/{date}")
    public List<Evenement> findByDate(Date date) {
        return evenementService.findByDate(date);
    }

    @PutMapping("/")
    public int update(@RequestBody Evenement evenement) {
        return evenementService.update(evenement);
    }

    @GetMapping("/email/{email}")
    public List<Evenement> findbyAdminEmail(@RequestBody String email) {
        return evenementService.findByAdminEmail(email);
    }

    @GetMapping("/titre/{titre}")
    public Evenement findByTitre(@RequestBody String titre) {
        return evenementService.findByTitre(titre);
    }

    @GetMapping()
    public List<Evenement> findAll() {
        return evenementService.findAll();
    }


    @Transactional
    @DeleteMapping("/titre/{titre}")
    public int deleteByTitre(String titre) {
        return evenementService.deleteByTitre(titre);
    }

    @GetMapping("/images/{imageName}")
    public String getImage(@PathVariable String imageName) {
        return evenementService.getImage(imageName);
    }
}


