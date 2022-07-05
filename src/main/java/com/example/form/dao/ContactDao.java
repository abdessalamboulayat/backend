package com.example.form.dao;

import com.example.form.bean.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactDao extends JpaRepository<Contact,Long> {
    Contact findByEmail(String email);
    int countContactAllByEmail(String email);

}
