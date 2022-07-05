package com.example.form.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import javax.persistence.*;
import java.util.Date;
@Entity
public class Formulaire {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    private String genre;
    private String email;
    private String adresse;
    private String nationalite;

    private String ref;
    private String ncin;
    private String numerodetelephone;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date datedenaissance;
    private String ideedeprojet;
    private String secteuredeprojet;
    private int anneexperience;
    @JsonIgnore
    @OneToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNcin() {
        return ncin;
    }

    public void setNcin(String ncin) {
        this.ncin = ncin;
    }

    public String getNumerodetelephone() {
        return numerodetelephone;
    }

    public void setNumerodetelephone(String numerodetelephone) {

        this.numerodetelephone = numerodetelephone;
    }

    public Date getDatedenaissance() {
        return datedenaissance;
    }

    public void setDatedenaissance(Date datedenaissance) {
        this.datedenaissance = datedenaissance;
    }

    public String getSecteuredeprojet() {
        return secteuredeprojet;
    }

    public void setSecteuredeprojet(String secteuredeprojet) {
        this.secteuredeprojet = secteuredeprojet;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getIdeedeprojet() {
        return ideedeprojet;
    }

    public void setIdeedeprojet(String ideedeprojet) {
        this.ideedeprojet = ideedeprojet;
    }

    public int getAnneexperience() {
        return anneexperience;
    }

    public void setAnneexperience(int anneexperience) {
        this.anneexperience = anneexperience;
    }
}