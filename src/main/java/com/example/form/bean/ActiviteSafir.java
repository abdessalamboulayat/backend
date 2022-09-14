package com.example.form.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class ActiviteSafir {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titre;
	private String resume;
	private String text;
	//@JsonFormat(pattern = "yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
	private Date dateActiviteSafir;
	private String image;
	//private List<String> listeImage = new ArrayList<>();
	private ArrayList<String> listeImage = new ArrayList<>();
	private String statu;
	@ManyToOne
	@JoinColumn(name = "id_safir", nullable = false, referencedColumnName = "id")
	private Safir safir;
	
	
	public ActiviteSafir() {
		super();
	}
	public ActiviteSafir(String titre, String resume, String text, Date dateActiviteSafir, String image,
			 Safir safir) {
		super();
		this.titre = titre;
		this.resume = resume;
		this.text = text;
		this.dateActiviteSafir = dateActiviteSafir;
		this.image = image;
		//this.listeImage = listeImage;
		this.safir = safir;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getDateActiviteSafir() {
		return dateActiviteSafir;
	}
	public void setDateActiviteSafir(Date dateActiviteSafir) {
		this.dateActiviteSafir = dateActiviteSafir;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public ArrayList<String> getListeImage() {
		return listeImage;
	}
	public void setListeImage(ArrayList<String> listeImage) {
		this.listeImage = listeImage;
	}
	public Safir getSafir() {
		return safir;
	}
	public void setSafir(Safir safir) {
		this.safir = safir;
	}
	public String getStatu() {
		return this.statu;
	}
	public void setStatu(String statu) {
		this.statu = statu;
	}
}
