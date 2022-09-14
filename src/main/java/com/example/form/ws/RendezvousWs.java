package com.example.form.ws;

import com.example.form.bean.Rendezvous;
import com.example.form.service.RendezvousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3006")
@RequestMapping("/api/v1/rendezvous ")
public class RendezvousWs {

    @GetMapping("/date/{date}")
    public List<Rendezvous> findByDaterendezvous(@PathVariable Date date) {
        return rendezvousService.findByDaterendezvous(date);
    }
@PostMapping("/")
    public int save(@RequestBody Rendezvous entity) {
        return rendezvousService.save(entity);
    }
@GetMapping("/email/{email}")
    public List<Rendezvous> findByEmail(@PathVariable String email) {
        return rendezvousService.findByEmail(email);
    }
@GetMapping("/")
    public List<Rendezvous> findAll() {
        return rendezvousService.findAll();
    }

    @Autowired
    private RendezvousService rendezvousService;

}
