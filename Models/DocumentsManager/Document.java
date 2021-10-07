package DocumentsManager;

public class Document {
	private String ISBN;
	private String titre;
	private String[] auteur=new String[5];
	private String editeur;
	private String anneeAdition;
	private int nbExemplaire;
	private int numerodoc; 
	private int etatDoc;//0 si ne pas emprunter 1 sinon
	public Document() {
		ISBN="";
		titre="";
		editeur="";
		anneeAdition="";
		nbExemplaire=0;
		etatDoc=0;
	}
	
	public Document(String ISBN,String titre,String editeur,String anneeAdition,String nbExemplaire,String[] auteur) {
		this.ISBN=ISBN;
		this.titre=titre;
		this.editeur=editeur;
		this.anneeAdition=anneeAdition;
		this.nbExemplaire=Integer.parseInt(nbExemplaire);
		etatDoc=0;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String[] getAuteur() {
		return auteur;
	}

	public void setAuteur(String[] auteur) {
		this.auteur = auteur;
	}

	public String getEditeur() {
		return editeur;
	}

	public void setEditeur(String editeur) {
		this.editeur = editeur;
	}

	public String getAnneeAdition() {
		return anneeAdition;
	}

	public void setAnneeAdition(String anneeAdition) {
		this.anneeAdition = anneeAdition;
	}

	public int getNbExemplaire() {
		return nbExemplaire;
	}

	public void setNbExemplaire(int nbExemplaire) {
		this.nbExemplaire = nbExemplaire;
	}
	
	public int getNumerodoc() {
		return numerodoc;
	}
	
	public int getEtatDoc() {
		return etatDoc;
	}
	
	@Override
	public String toString() {
		if(this.etatDoc==0)
			return "desponible";
		else
			return "d'ejat emprunter";
	}
	
	public void incrementNbExemplaire() {
		this.nbExemplaire++;
	}
	
	public void decrementNbExemplaire() {
		if(this.nbExemplaire > 0)
			this.nbExemplaire --;
		else
			System.out.println("en ne peut pas decrumenter le nombre des exemplaires ");
	}
	
	/*-- le destructeur d'un document --*/
	public void finalize() {
		System.out.println("le document est bien detruit de la mémoire"+this); 
	}
}
