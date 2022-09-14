package com.example.form.ws;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import com.example.form.bean.ActiviteSafir;
import com.example.form.bean.Authority;
import com.example.form.bean.Evenement;
import com.example.form.bean.Formation;
import com.example.form.bean.Safir;
import com.example.form.dto.ActiviteSafirDto;
import com.example.form.dto.EvenementDto;
import com.example.form.dto.FormationDto;
import com.example.form.repository.ActiviteSafirRepo;
import com.example.form.repository.AuthorityRepo;
import com.example.form.repository.EvenementRepo;
import com.example.form.repository.FormationRepo;
import com.example.form.repository.SafirRepo;
import com.example.form.service.AdminService;
import com.example.form.service.EvenementService;
import com.example.form.service.FormationService;
import com.example.form.service.PasswordService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = {"http://localhost:3006", "http://localhost:3001"})
@RequestMapping("/api/admin/")
public class AdminWs {

	@Autowired
	private SafirRepo safirRepo;
	@Autowired
	private AdminService adminService;
	@Autowired
	private ActiviteSafirRepo activiteSafirRepo;
	@Autowired
	private EvenementRepo evenementRepo;
	@Autowired
	private FormationRepo formationRepo;
	@Autowired
	private AuthorityRepo authorityRepo;
	@Autowired
	private EvenementService evenementService;
	@Autowired
	private FormationService formationService;
	
	@GetMapping("/validateToken")
	public String validateToken(){
		return "validate token";
	}
 
	@PostMapping("/addNewSafir")
	public String addNewSafir(@RequestBody Safir safir) {
		System.out.println(safir.getNomSafir());
		System.out.println(safir.getPrenomSafir());
		return adminService.addNewSafir(safir);
	}
	
	/*@RequestMapping(path = "/addNewEvent", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE })
	public int save(@ModelAttribute EvenementDto evenement) throws IOException {

        return evenementService.save(evenement);
    }*/
	@RequestMapping(path = "/addNewEvent", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Evenement> addNewEvent(@ModelAttribute EvenementDto evenementDto)throws Exception {
		try {
			Evenement evenement = new Evenement();
			System.out.println("request add event");
			
			if(!evenementDto.getFile().isEmpty()) {
				
				String newFileName = evenementService.saveFile(evenementDto.getFile());
				evenement.setImage(newFileName);
			}
			String username = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
			Safir currentUser = safirRepo.findByUsername(username);

			//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
			//Date dateEvenement = dateFormat.parse(String.valueOf(evenementDto.getDate()));
			evenement.setAdmin(currentUser);
			evenement.setTitre(evenementDto.getTitre());
			evenement.setTexte(evenementDto.getTexte());
			evenement.setDate(evenementDto.getDate());
		
			return ResponseEntity.ok(evenementRepo.save(evenement));
		}catch (Exception ex) {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@Transactional
	@RequestMapping(path = "/updateEvent/{idEvent}", method = RequestMethod.PUT, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Evenement> updateEvent(@PathVariable("idEvent") String idEvent, @ModelAttribute EvenementDto evenementDto) throws IOException, ParseException {
		if(idEvent != null) {
			Long id = Long.parseLong(idEvent);
			Evenement evenement = evenementRepo.getById(id);
			if(evenement != null) {
				
				if(evenementDto.getDate() != null) {
					//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
					//Date dateEvenement = dateFormat.parse(String.valueOf(evenementDto.getDate()));
					evenement.setDate(evenementDto.getDate());
				}
				if(evenementDto.getTitre() != null) {
					evenement.setTitre(evenementDto.getTitre());
				}
				if(evenementDto.getTexte()!=null) {
					evenement.setTexte(evenementDto.getTexte());
				}
				
				return ResponseEntity.ok(evenementRepo.save(evenement));
			}
		}
		return ResponseEntity.badRequest().body(null);
		
	}
	
	@DeleteMapping("/deleteEvent/{idEvent}")
	public ResponseEntity<Void> deleteEvent(@PathVariable("idEvent") String idEvent) throws Exception{
		try {
			Long id = Long.parseLong(idEvent);
			if(idEvent != null) {
				Evenement event = evenementRepo.getById(id);
				if(event != null) {
					evenementRepo.deleteById(id);
				}
			}
			return ResponseEntity.ok(null);
		}catch(Exception ex) {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@RequestMapping(path = "/addNewFormation", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Formation> addNewFormation(@ModelAttribute FormationDto formationDto)throws Exception {
		try {
			Formation formation = new Formation();
			System.out.println("request add Formation");
			if(!formationDto.getFile().isEmpty()) {
				
				String newFileName = formationService.saveFile(formationDto.getFile());
				formation.setImage(newFileName);
			}
			String username = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
			Safir currentUser = safirRepo.findByUsername(username);

			//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
			//Date dateFormation = dateFormat.parse(String.valueOf(formationDto.getDate()));
			formation.setAdmin(currentUser);
			formation.setTitre(formationDto.getTitre());
			formation.setTexte(formationDto.getTexte());
			formation.setDate(formationDto.getDate());
		
			return ResponseEntity.ok(formationRepo.save(formation));
		}catch(Exception ex) {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@Transactional
	@RequestMapping(path = "/updateFormation/{idFormation}", method = RequestMethod.PUT, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Formation> updateFormation(@PathVariable("idFormation") String idFormation, @ModelAttribute FormationDto formationDto) throws Exception, IOException, ParseException {
		
			if(idFormation != null) {
				Long id = Long.parseLong(idFormation);
				Formation formation = formationRepo.getById(id);
				if(formation != null) {
				
					if(formationDto.getDate() != null) {
						//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
						//Date dateFormation = dateFormat.parse(String.valueOf(formationDto.getDate()));
						formation.setDate(formationDto.getDate());
					}
					if(formationDto.getTitre() != null) {
						formation.setTitre(formationDto.getTitre());
					}
					if(formationDto.getTexte()!=null) {
						formation.setTexte(formationDto.getTexte());
					}
			return ResponseEntity.ok(formationRepo.save(formation));
				
			}
			}
			return ResponseEntity.badRequest().body(null);
			
	}
	
	@DeleteMapping("/deleteFormation/{idFormation}")
	public ResponseEntity<Void> deleteFormation(@PathVariable("idFormation") String idFormation) throws Exception {
		try {
			if(idFormation != null) {
				Long id = Long.parseLong(idFormation);
				Formation event = formationRepo.getById(id);
			if(event != null) {
				formationRepo.deleteById(id);
				}
			}
			return ResponseEntity.ok(null);
		}catch(Exception ex) {
			return ResponseEntity.badRequest().body(null);
		}
		
	}
	
	@Transactional
	@PutMapping("/validerActiviteSafir/{idActivite}")
	public void validerActiviteSafir(@PathVariable("idActivite") String idActivite) {
		if(idActivite != null) {
			Long id = Long.parseLong(idActivite);
			ActiviteSafir activiteSafir = activiteSafirRepo.getById(id);
			if(activiteSafir != null) {
				activiteSafir.setStatu("valider");
				activiteSafirRepo.save(activiteSafir);
			}
		}
	}
	
	@Transactional
	@PutMapping("/refuserActiviteSafir/{idActivite}")
	public void refuserActiviteSafir(@PathVariable("idActivite") String idActivite) {
		if(idActivite != null) {
			Long id = Long.parseLong(idActivite);
			ActiviteSafir activiteSafir = activiteSafirRepo.getById(id);
			if(activiteSafir != null) {
				activiteSafir.setStatu("refuser");
				activiteSafirRepo.save(activiteSafir);
			}
		}
	}
	@GetMapping("/getAllSafir")
	public List<Safir> getAllSafir(){
		return safirRepo.findByIsSafir(true);
	}
	
	/*@GetMapping("/getAllEvent")
	public List<Evenement> getAllEvent(){
		List<Evenement> events = evenementRepo.findAll();
		for(int i =0 ; i<events.size(); i++) {
			String imageName = events.get(i).getImage();
			String image = adminService.getImage(imageName);
			events.get(i).setImage(image);
		}
		return events;
	}*/

}
