package com.example.form.ws;

import com.example.form.bean.Consultation;
import com.example.form.service.ConsulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3006"})
@RequestMapping("api/v1/Consultation")
public class ConsultationWs {
	
	@Autowired
    ConsulationService consulationService;
	
    @GetMapping("/email/{email}")
    public Consultation findByEmail(String email) {
        return consulationService.findByEmail(email);
    }
    
    @GetMapping("/")
    public List<Consultation> findAll() {
        return consulationService.findAll();
    }
    
    @PostMapping("/")
    public int save(@RequestBody Consultation entity) {
        return consulationService.save(entity);
    }

    @GetMapping("/count/")
    public long count() {
        return consulationService.count();
    }
}


