package com.example.form.ws;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.form.bean.ActiviteSafir;
import com.example.form.bean.Formation;
import com.example.form.bean.Safir;
import com.example.form.dto.ActiviteSafirDto;
import com.example.form.dto.EvenementDto;
import com.example.form.repository.ActiviteSafirRepo;
import com.example.form.repository.SafirRepo;
import com.example.form.service.SafirService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3006"})
@RequestMapping("/api/safir")
public class SafirWs {

	@Autowired
	private ActiviteSafirRepo activiteSafirRepo;
	@Autowired
	private SafirRepo safirRepo;
	@Autowired
	private SafirService safirService;
	
	@RequestMapping(path = "/addActiviteSafir", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE })
	public ActiviteSafir addActiviteSafir(@ModelAttribute ActiviteSafirDto activiteSafirDto)throws Exception {
		ActiviteSafir activiteSafir = new ActiviteSafir();
		System.out.println(activiteSafirDto.getDateActiviteSafir());
		if(!activiteSafirDto.getImage().isEmpty()) {
			//Files.copy(activiteSafirDto.getImage().getInputStream(), Paths.get(UPLOAD_FILE, activiteSafirDto.getImage().getOriginalFilename()));
			String newFileName = safirService.saveFile(activiteSafirDto.getImage());
			activiteSafir.setImage(newFileName);
			for(MultipartFile file : activiteSafirDto.getListeImage()) {
    			System.out.println(file.getOriginalFilename());
    			System.out.println(file.getSize());
    			String newImageName = safirService.saveFile(file);
    			activiteSafir.getListeImage().add(newImageName);
    		}
		}
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		Safir currentUser = safirRepo.findByUsername(username);

		activiteSafir.setSafir(currentUser);
		activiteSafir.setTitre(activiteSafirDto.getTitre());
		activiteSafir.setResume(activiteSafirDto.getResume());
		activiteSafir.setDateActiviteSafir(activiteSafirDto.getDateActiviteSafir());
		activiteSafir.setText(activiteSafirDto.getText());
		
		return activiteSafirRepo.save(activiteSafir);
	}
	
	@RequestMapping(path = "/updateActivite/{idActivite}", method = RequestMethod.PUT, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE })
	public ActiviteSafir updateActivite(@PathVariable("idActivite") Long idActivite, @ModelAttribute ActiviteSafirDto activiteSafirDto) {
		if(idActivite != null) {
			ActiviteSafir activiteSafir = activiteSafirRepo.getById(idActivite);
			if(activiteSafir != null) {
			
				if(activiteSafirDto.getTitre()!=null) {
					activiteSafir.setTitre(activiteSafirDto.getTitre());
				}
				if(activiteSafirDto.getResume()!=null) {
					activiteSafir.setResume(activiteSafirDto.getResume());
				}
				if(activiteSafirDto.getDateActiviteSafir()!=null) {
					activiteSafir.setDateActiviteSafir(activiteSafirDto.getDateActiviteSafir());
				}
				if(activiteSafirDto.getText()!=null) {
					activiteSafir.setText(activiteSafirDto.getText());
				}
				return activiteSafirRepo.save(activiteSafir);
			}
		}
		return null;
	}
	
	@DeleteMapping("/deleteActivite/{idActivite}")
	public void deleteFormation(@PathVariable("idActivite") Long idActivite) {
		
		if(idActivite != null) {
			//Long id = Long.parseLong(idFormation);
			ActiviteSafir event = activiteSafirRepo.getById(idActivite);
			if(event != null) {
				activiteSafirRepo.deleteById(idActivite);
			}
		}
	}
	
	@GetMapping("/getCurrentUser")
	public Safir getUser(Principal principal) {
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		Safir currentUser = safirRepo.findByUsername(username);
		return currentUser;
	}
	
	@GetMapping("/getMyActivities")
	public List<ActiviteSafir> getMyActivities(){
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		Safir currentUser = safirRepo.findByUsername(username);
		return activiteSafirRepo.findBySafir(currentUser);
	}
	
}
