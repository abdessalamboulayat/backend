package com.example.form.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.awt.*;
import java.util.Date;

@Entity
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String image;
    private String texte;
    //@JsonFormat(pattern = "yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    private Date date;
    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_admin", nullable = false, referencedColumnName = "id")
    private Safir safir;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

  /*  public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
*/
    public Safir getAdmin() {
        return safir;
    }

    public void setAdmin(Safir admin) {
        this.safir = admin;
    }
}




