package com.example.form.service;

import com.example.form.bean.Formulaire;
import com.example.form.bean.User;
import com.example.form.repository.FormulaireRepo;
import com.example.form.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FormulaireService {
    @Autowired                          // instancier  l'Object FormulaireDoa
    private FormulaireRepo formulaireRepo;
    @Autowired
    private UserRepo userRepo;
    public long count() {

        return formulaireRepo.count();
    }


    public int save(Formulaire entity) {

        if (findByNcin(entity.getNcin()) != null) {
            return -1;
        }
        else if (findByEmail(entity.getEmail()) != null) {
            return -2;
        } else {
            Formulaire formulaire1 = new Formulaire();
            formulaire1.setEmail(entity.getEmail());
            formulaire1.setNom(entity.getNom());
            formulaire1.setPrenom(entity.getPrenom());
            formulaire1.setGenre(entity.getGenre());
            formulaire1.setDatedenaissance(entity.getDatedenaissance());
            formulaire1.setAdresse(entity.getAdresse());
            formulaire1.setAnneexperience(entity.getAnneexperience());
            formulaire1.setIdeedeprojet(entity.getIdeedeprojet());
            formulaire1.setNationalite(entity.getNationalite());
            formulaire1.setNcin(entity.getNcin());
            formulaire1.setNumerodetelephone(entity.getNumerodetelephone());
            formulaire1.setSecteuredeprojet(entity.getSecteuredeprojet());
// save User
            User user = new User();
            user.setEmail(entity.getEmail());
            user.setNom(entity.getNom());
            user.setPrenom(entity.getPrenom());

            userRepo.save(user);
            formulaire1.setUser(user);// association  user au formulaire


            formulaireRepo.save(formulaire1);
            user.setFormulaire(formulaire1);//asociation formulaire  au user
            System.out.println("=====================================================");
            System.out.println(user);//
            System.out.println("=====================================================");
            userRepo.save(user);

            return 1;
        }
    }

    public List<Formulaire> findAll() {
        return formulaireRepo.findAll();
    }


    public Formulaire findByNcin(String ncin) {
        return formulaireRepo.findByNcin(ncin);
    }

    public Formulaire findByUserRef(String ref) {
        return formulaireRepo.findByUserRef(ref);
    }

    @Transactional
    public int deleteByNcin(String ncin) {

        return formulaireRepo.deleteByNcin(ncin);
    }


    public Formulaire findByNumerodetelephone(String numerodetelephone) {
        return formulaireRepo.findByNumerodetelephone(numerodetelephone);
    }

    public Formulaire findByEmail(String email) {
        return formulaireRepo.findByEmail(email);
    }
}
