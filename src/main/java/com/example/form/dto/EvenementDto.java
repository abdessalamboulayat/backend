package com.example.form.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
// recuperer le formdata
public class EvenementDto {


   // private String name;
    private String titre;
    private String texte;
    private Date date;
    private MultipartFile file;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
   /* public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }*/
}
