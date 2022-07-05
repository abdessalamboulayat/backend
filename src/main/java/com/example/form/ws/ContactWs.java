package com.example.form.ws;

import com.example.form.bean.Contact;
import com.example.form.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("api/v1/contact")
public class ContactWs {

    @GetMapping("/email/{email}")
    public Contact findByEmail(String email) {

        return contactService.findByEmail(email);
    }
@PostMapping("/")
    public int save( @RequestBody Contact entity) {

        return contactService.save(entity);
    }
@GetMapping("/")
    public List<Contact> findAll() {
        return contactService.findAll();
    }

    @Autowired
    private ContactService contactService;


    @GetMapping("/count/")
    public long count() {
        return contactService.count();
    }

    @GetMapping("/email")
    public int countContactAllByEmail(String email) {
        return contactService.countContactAllByEmail(email);
    }
}
