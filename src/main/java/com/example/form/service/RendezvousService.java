package com.example.form.service;

import com.example.form.bean.Rendezvous;
import com.example.form.dao.RendezvousDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RendezvousService {
    public List<Rendezvous> findByDaterendezvous(Date date) {
        return rendezvousDao.findByDaterendezvous(date);
    }
    public int save(Rendezvous entity) {
        if (findByDaterendezvous(entity.getDaterendezvous()) != null) {
            return -1;
        } else if (findByEmail(entity.getEmail()) != null) {
            return -2;
        } else if (entity.getDaterendezvous().compareTo(new Date()) < 0) {
            return -3;
        } else {
            rendezvousDao.save(entity);
            return 1;
        }
    }
    public List<Rendezvous> findByEmail(String email) {
        return rendezvousDao.findByEmail(email);
    }
    public List<Rendezvous> findAll() {
        return rendezvousDao.findAll();
    }
    @Autowired
    private RendezvousDao rendezvousDao;

}
