package com.example.form.bean;

import javax.persistence.*;
import java.util.List;

@Entity



public class Admin {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String email;
    private String password;
/*@OneToMany(mappedBy = "admin")
private List <Evenement> evenement;
@OneToMany(mappedBy = "admin")
private   List <Formation> formation;

    public List<Evenement> getEvenement() {
        return evenement;
    }

    public void setEvenement(List<Evenement> evenement) {
        this.evenement = evenement;
    }

    public List<Formation> getFormation() {
        return formation;
    }

    public void setFormation(List<Formation> formation) {
        this.formation = formation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }*/
}
