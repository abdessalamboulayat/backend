package com.example.form.service;

import com.example.form.bean.Consultation;
import com.example.form.repository.ConsultationRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsulationService {

    @Autowired
    private ConsultationRepo consultationRepo;

    public long count() {

        return consultationRepo.count();
    }

    public Consultation findByEmail(String email) {
        return consultationRepo.findByEmail(email);
    }

    public List<Consultation> findAll() {
        return consultationRepo.findAll();
    }

    public int save(Consultation entity) {
        if (findByEmail(entity.getEmail()) != null) {
            return -1;
        } else {
        	consultationRepo.save(entity);
            {
                return 1;
            }
        }


    }

}
