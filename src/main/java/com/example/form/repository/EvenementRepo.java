package com.example.form.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.form.bean.Evenement;

@Repository
public interface EvenementRepo extends JpaRepository<Evenement, Long> {
	List<Evenement> findByDate(Date date);
    List<Evenement> findBySafirUsername(String username);
    Evenement findByTitre(String titre);
    int deleteByTitre(String titre);
    @Query("SELECT e FROM Evenement  e ORDER BY e.date DESC ")
    List<Evenement> OrderByDate();
}
