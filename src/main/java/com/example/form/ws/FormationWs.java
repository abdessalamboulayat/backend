package com.example.form.ws;

import com.example.form.bean.Formation;
import com.example.form.dto.FormationDto;
import com.example.form.repository.FormationRepo;
import com.example.form.service.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3006"})
@RequestMapping("api/v1/formation")
public class FormationWs {
	
	@Autowired
	private FormationRepo formationRepo;
	@Autowired
    private FormationService formationService;

    @GetMapping("/{date}/date")
    public List<Formation> findByDate(Date date) {
        return formationService.findByDate(date);
    }

    @GetMapping("/getFormationById/{idFormation}")
    public Formation getFormationById(@PathVariable("idFormation") String idFormation) {
    	if(idFormation != null) {
    		Long id = Long.parseLong(idFormation);
    		return formationRepo.getById(id);
    	}
    	else {
    		return null;
    	}
    }
    
    @GetMapping("/{email}/email")
    public List<Formation> findByAdminEmail(String email) {
        return formationService.findByAdminEmail(email);
    }

    @GetMapping("/{titre}/titre")
    public Formation findByTitre(String titre) {
        return formationService.findByTitre(titre);
    }

    @GetMapping("/getFormation")
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
}
