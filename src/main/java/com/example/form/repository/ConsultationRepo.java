package com.example.form.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.form.bean.Consultation;

@Repository
public interface ConsultationRepo extends JpaRepository<Consultation, Long>{
	Consultation findByEmail(String email);
}
