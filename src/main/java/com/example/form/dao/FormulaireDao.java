package com.example.form.dao;

import com.example.form.bean.Formulaire;
import com.example.form.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormulaireDao extends JpaRepository<Formulaire, Long> {
    Formulaire findByRef(String ref);

Formulaire findByEmail(String email);
    Formulaire findByUserRef(String ref);
    Formulaire findByNcin(String ref);
    Formulaire findByNumerodetelephone(String numerodetelephone);

    int deleteByNcin(String ncin);



}
