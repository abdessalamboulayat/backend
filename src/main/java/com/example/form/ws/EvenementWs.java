package com.example.form.ws;

import com.example.form.bean.Evenement;
import com.example.form.dto.EvenementDto;
import com.example.form.repository.EvenementRepo;
import com.example.form.service.EvenementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3006"})
@RequestMapping("api/v1/evenement")
public class EvenementWs {

	@Autowired
	private EvenementRepo evenementRepo;
    @Autowired
    private EvenementService evenementService;

    @GetMapping("/")
    public List<Evenement> findAll() {
        return evenementService.findAll();
    }
    
    @GetMapping("/count/")
    public long count() {
        return evenementService.count();
    }

    @GetMapping("/fistThree")
    public List<Evenement> OrderByDate() {
        return evenementService.OrderByDate();
    }

    @GetMapping("/date/{date}")
    public List<Evenement> findByDate(Date date) {
        return evenementService.findByDate(date);
    }
    
    @GetMapping("/email/{email}")
    public List<Evenement> findbyAdminEmail(@RequestBody String email) {
        return evenementService.findByAdminEmail(email);
    }

    @GetMapping("/titre/{titre}")
    public Evenement findByTitre(@PathVariable String titre) {
        return evenementService.findByTitre(titre);
    }
    
    @GetMapping("/getEventById/{idEvent}")
    public Evenement getEventById(@PathVariable("idEvent") String idEvent) {
    	if(idEvent != null) {
    		Long id = Long.parseLong(idEvent);
    		return evenementRepo.getById(id);
    	}
    	else {
    		return null;
    	}
    }

    @GetMapping("/images/{imageName}")
    public String getImage(@PathVariable String imageName) {
        return evenementService.getImage(imageName);
    }
    
    @GetMapping("/getAllEvent")
	public List<Evenement> getAllEvent(){
		return evenementRepo.findAll();
	}
   
    
   /* @RequestMapping(path = "/uploadFiles", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Void> uploadFiles(@RequestParam("files") MultipartFile[] files){
    	try {
    		for(MultipartFile file : files) {
    			System.out.println(file.getOriginalFilename());
    			System.out.println(file.getSize());
    			save(file);
    		}
    		return new ResponseEntity<Void>(HttpStatus.OK);
    	}
    	catch(Exception ex) {
    		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    	}
    }
    
    private String fileDir = System.getProperty("user.dir")+"/src/main/resources/static/images/activiteSafir/";*/
    
    /*private String save(MultipartFile file) {
    	try {
    		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
    		String newFileName = dateFormat.format(new Date())+file.getOriginalFilename();
    		byte[] bytes = file.getBytes();
    		Path path = Paths.get(fileDir+newFileName);
    		Files.write(path, bytes);
    		return newFileName;
    	}
    	catch(Exception ex) {
    		return null;
    	}
    }*/
}


