package com.example.form.dao;

import com.example.form.bean.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface  FormationDao extends JpaRepository<Formation,Long> {
    List<Formation> findByDate(Date date);
    List<Formation> findByAdminEmail(String email);
    Formation findByTitre(String titre);
    int deleteByTitre(String titre);
}
