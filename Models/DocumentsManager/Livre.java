package DocumentsManager;

public class Livre extends Document{
	private int nbPages;
	private String typeLivres;
	private String tome;
	
	public Livre() {
		super();
		nbPages=0;
		typeLivres="";
		tome="";
	}
	public Livre(String ISBN,String titre,String editeur,String anneeAdition,String nbExemplaire,String nbPages,String typeLivres,String tome,String[] auteur){
		super( ISBN, titre, editeur, anneeAdition, nbExemplaire,auteur);
		this.nbPages=Integer.parseInt(nbPages);
		this.typeLivres=typeLivres;
		this.tome=tome;
	}
	public int getNbPages() {
		return nbPages;
	}
	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}
	public String getTypeLivres() {
		return typeLivres;
	}
	public void setTypeLivres(String typeLivres) {
		this.typeLivres = typeLivres;
	}
	public String getTome() {
		return tome;
	}
	public void setTome(String tome) {
		this.tome = tome;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+" le nombre des pages est "+this.getNbPages()+" le type de livres est "+this.getTypeLivres()+" la tome est "+this.getTome();
	}
	

}
