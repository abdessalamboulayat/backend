package com.example.form.dao;

import com.example.form.bean.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao extends JpaRepository<Admin,Long> {


Admin findByEmail(String email);


}
