package com.example.form.service;

import com.example.form.bean.Contact;
import com.example.form.bean.Formulaire;
import com.example.form.repository.ContactRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import java.util.List;

@Service
public class ContactService {
	
	@Autowired
    private ContactRepo contactRepo;
	
    public List<Contact> findAll() {
        return contactRepo.findAll();
    }

    public long count() {

        return contactRepo.count();
    }


    public Contact findByEmail(String email) {
        return contactRepo.findByEmail(email);
    }

    public int save(Contact entity) {
        if (findByEmail(entity.getEmail()) != null) {

            return -1;
        } else {
        	contactRepo.save(entity);
            return 1;
        }
    }


    public int countContactAllByEmail(String email) {
        return contactRepo.countContactAllByEmail(email);
    }
}
