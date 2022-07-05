package com.example.form.dao;

import com.example.form.bean.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationDao extends JpaRepository<Consultation,Long> {

    Consultation findByEmail(String email);
}
