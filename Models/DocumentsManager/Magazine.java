package DocumentsManager;

public class Magazine extends Document{
	private String periodicite;
	private String moisEdition;
	private String jour;
	
	public Magazine() {
		// TODO Auto-generated constructor stub
		super();
		this.periodicite="";
		this.moisEdition="";
		this.jour="";
		
	}
	
	

	public String getPeriodicite() {
		return periodicite;
	}

	public void setPeriodicite(String periodicite) {
		this.periodicite = periodicite;
	}

	public String getMoisEdition() {
		return moisEdition;
	}

	public void setMoisEdition(String moisEdition) {
		this.moisEdition = moisEdition;
	}

	public String getJour() {
		return jour;
	}

	public void setJour(String jour) {
		this.jour = jour;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+" la periodiciter est :"+this.getPeriodicite()+" le mois de l'edition est "+this.getMoisEdition() +" le jours est "+this.getJour();
	}
	

}
