package com.ensias.projetJava;

public class Etudiant extends Adherent {
	private String cne;
	
	
	
	
	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Etudiant(String code_adh, String nom, String prenom, String adresse, String tel, int typeAdh,
			int nbr_eprunter, String filiere) {
		super(code_adh, nom, prenom, adresse, tel, typeAdh, nbr_eprunter);
		
	}

	public String getCne() {
		return cne;
	}
	public void setCne(String cne) {
		this.cne = cne;
	}
	
	
	
}
