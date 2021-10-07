package com.ensias.projetJava;

public class Professeur extends Adherent{

	private String cin;
	
	
	
	public Professeur() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Professeur(String code_adh, String nom, String prenom, String adresse, String tel, int typeAdh,
			int nbr_eprunter, String departement) {
		super(code_adh, nom, prenom, adresse, tel, typeAdh, nbr_eprunter);
		this.cin = cin;
	}



	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	
	
	
	
}
