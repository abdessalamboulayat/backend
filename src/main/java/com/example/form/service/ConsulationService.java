package com.example.form.service;

import com.example.form.bean.Consultation;
import com.example.form.dao.ConsultationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsulationService {


    @Autowired
    private ConsultationDao consultationDao;

    public long count() {

        return consultationDao.count();
    }




    public Consultation findByEmail(String email) {
        return consultationDao.findByEmail(email);
    }

    public List<Consultation> findAll() {
        return consultationDao.findAll();
    }

    public int save(Consultation entity) {
        if (findByEmail(entity.getEmail()) != null) {
            return -1;
        } else {
            consultationDao.save(entity);
            {
                return 1;
            }
        }


    }

}
