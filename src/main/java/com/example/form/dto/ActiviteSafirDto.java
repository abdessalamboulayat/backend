package com.example.form.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.web.multipart.MultipartFile;

import com.example.form.bean.Safir;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ActiviteSafirDto {

	private String titre;
	private String resume;
	private String text;
	//@JsonFormat(pattern = "yyyy-mm-dd")
	private Date dateActiviteSafir;
	private MultipartFile image;
	private MultipartFile[] listeImage;
	//private List<String> listeImage;
	//private Safir safir;
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
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	
	public MultipartFile[] getListeImage() {
		return listeImage;
	}
	public void setListeImage(MultipartFile[] listeImage) {
		this.listeImage = listeImage;
	}
}
