package com.example.form.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.FetchMode;
import org.hibernate.annotations.Fetch;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
public class Safir implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomSafir;
	private String prenomSafir;
	private String telephone;
	private boolean isSafir;
	private String username;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Authority> authorities = new ArrayList<>();

	@Override
	public Collection<Authority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomSafir() {
		return this.nomSafir;
	}
	public String getPrenomSafir() {
		return this.prenomSafir;
	}
	public void setNomSafir(String nomSafir) {
		this.nomSafir = nomSafir;
	}
	public void setPrenomSafir(String prenomSafir) {
		this.prenomSafir = prenomSafir;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTelephone(){
		return this.telephone;
	}
	public void setTelephone(String telephone){
		this.telephone = telephone;
	}
	public boolean getIsSafir(){
		return this.isSafir;
	}
	public void setIsSafir(boolean isSafir){
		this.isSafir = isSafir;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
