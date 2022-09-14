package com.example.form.ws;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.form.bean.ActiviteSafir;
import com.example.form.bean.Safir;
import com.example.form.repository.ActiviteSafirRepo;
import com.example.form.repository.SafirRepo;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3006"})
@RequestMapping("/api/activiteSafir/")
public class ActiviteSafirWs {

	private String UPLOAD_FILE = System.getProperty("user.dir")+"/src/main/resources/static/images/activiteSafir/";
	@Autowired
	private ActiviteSafirRepo activiteSafirRepo;
	@Autowired
	private SafirRepo safirRepo;
	
	@GetMapping("/getAllActivities")
	public List<ActiviteSafir> getAllActivities(){
		return activiteSafirRepo.findAll();
	}
	
	@GetMapping("/count")
	public Long numberOfActiviteSafir(){
		return activiteSafirRepo.count();
	}	

	@GetMapping("/getActiviteById/{idActivite}")
	public ActiviteSafir getActiviteById(@PathVariable("idActivite") Long idActivite) {
		if(idActivite != 0) {
			//Long id = Long.parseLong(idActivite);
			return activiteSafirRepo.getById(idActivite);
		}
		return null;
	}
	
	@GetMapping("/image/{imageName}")
	public String getImageByte(@PathVariable("imageName") String imageName) {
		return getImage(imageName);
	}
	
	@GetMapping("/listImages/{idActivite}")
	public ArrayList<String> getListImages(@PathVariable("idActivite") Long idActivite){
		
		if(idActivite != null) {
			ActiviteSafir activiteSafir = activiteSafirRepo.getById(idActivite);
			if(activiteSafir!=null) {
				ArrayList<String> listImage = new ArrayList<>();
				listImage.add(getImage(activiteSafir.getImage()));
				for(String imageName : activiteSafir.getListeImage()) {
					listImage.add(getImage(imageName));
				}
				return listImage;
			}
		}
		return null;
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
}
