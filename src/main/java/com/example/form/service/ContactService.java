package com.example.form.service;

import com.example.form.bean.Contact;
import com.example.form.bean.Formulaire;
import com.example.form.dao.ContactDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import java.util.List;

@Service
public class ContactService {
    public List<Contact> findAll() {
        return contactDao.findAll();
    }

    @Autowired
    private ContactDao contactDao;

    public long count() {

        return contactDao.count();
    }


    public Contact findByEmail(String email) {
        return contactDao.findByEmail(email);
    }

    public int save(Contact entity) {
        if (findByEmail(entity.getEmail()) != null) {

            return -1;
        } else {
            contactDao.save(entity);
            return 1;
        }
    }


    public int countContactAllByEmail(String email) {
        return contactDao.countContactAllByEmail(email);
    }
}
