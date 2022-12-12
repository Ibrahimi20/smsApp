package com.example.demo.model;

import java.sql.Date;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class SMS {
	public SMS() {}
	
	public SMS(String numObjetAppe, String contactInfo, String nom, String prenom, String etat, Date dateEnvoie) {
	
		NumObjetAppe = numObjetAppe;
		ContactInfo = contactInfo;
		Nom = nom;
		Prenom = prenom;
		Etat = etat;
		this.dateEnvoie = dateEnvoie;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long OrderId;
	@NonNull
	private String NumObjetAppe;
	@NonNull
	private String ContactInfo;
	@NonNull
	private String Nom;
	@NonNull
	private String Prenom;
	@NonNull
	private String Etat;
	@NonNull
	@Column(name = "DateEnvoie")
	private Date dateEnvoie;

	public String getNumObjetAppe() {
		return NumObjetAppe;
	}

	public void setNumObjetAppe(String numObjetAppe) {
		NumObjetAppe = numObjetAppe;
	}

	public String getContactInfo() {
		return ContactInfo;
	}

	public void setContactInfo(String contactInfo) {
		ContactInfo = contactInfo;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getPrenom() {
		return Prenom;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}

	public String getEtat() {
		return Etat;
	}

	public void setEtat(String etat) {
		Etat = etat;
	}
	
	



	public Date getDateEnvoie() {
		return dateEnvoie;
	}

	public void setDateEnvoie(Date date_Envoie) {
		dateEnvoie = date_Envoie;
	}


	


	
	
	
	
	
	

}
