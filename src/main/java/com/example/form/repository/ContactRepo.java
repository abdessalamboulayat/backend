package com.example.form.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.form.bean.Contact;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Long>{
	Contact findByEmail(String email);
    int countContactAllByEmail(String email);
}
