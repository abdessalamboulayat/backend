package com.example.form.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.form.bean.Formulaire;

@Repository
public interface FormulaireRepo extends JpaRepository<Formulaire, Long>{
	Formulaire findByRef(String ref);

    Formulaire findByEmail(String email);
    Formulaire findByUserRef(String ref);
    Formulaire findByNcin(String ref);
    Formulaire findByNumerodetelephone(String numerodetelephone);

    int deleteByNcin(String ncin);
}
