package com.example.form.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.form.bean.Formation;

@Repository
public interface FormationRepo extends JpaRepository<Formation, Long>{
	List<Formation> findByDate(Date date);
    List<Formation> findByAdminUsername(String email);
    Formation findByTitre(String titre);
    int deleteByTitre(String titre);
}
