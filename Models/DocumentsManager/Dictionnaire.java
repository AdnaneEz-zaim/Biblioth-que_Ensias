package DocumentsManager;

public class Dictionnaire extends Document{
	private String langue;
	private int nombreTomes;
	
	public Dictionnaire() {
		// TODO Auto-generated constructor stub
		super();
		this.langue="";
		this.nombreTomes=0;
	}
	
	public Dictionnaire(String ISBN,String titre,String editeur,String anneeAdition,String nbExemplaire,String langue,String nombreTomes,String[] auteurs){
		super( ISBN, titre, editeur, anneeAdition, nbExemplaire,auteurs);
		this.langue=langue;
		this.nombreTomes=Integer.parseInt(nombreTomes);
	}

	public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}

	public int getNombreTomes() {
		return nombreTomes;
	}

	public void setNombreTomes(int nombreTomes) {
		this.nombreTomes = nombreTomes;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+" la langue est "+this.getLangue()+" le nombres des tomes est "+this.getNombreTomes();
	}

}
