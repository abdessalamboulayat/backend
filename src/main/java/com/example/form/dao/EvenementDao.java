package com.example.form.dao;

import com.example.form.bean.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface EvenementDao extends JpaRepository<Evenement,Long> {
   List<Evenement> findByDate(Date date);
    List<Evenement> findByAdminEmail(String email);
    Evenement findByTitre(String titre);
    int deleteByTitre(String titre);
  @Query("SELECT e FROM Evenement  e ORDER BY e.date DESC ")
  List<Evenement> OrderByDate();




}
