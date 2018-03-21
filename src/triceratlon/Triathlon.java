package triceratlon;


import java.util.*;

public class Triathlon extends SuperClass{
	
	// listage des atribut
	private String format;
	private String lieu;
	private boolean championnat;
	private HashMap<Integer, Tache> listeTache;
	private HashMap<Integer, Benevole> listeBenevole;
	private HashMap<Integer, Document> listeDoc;
	
	
	
	
	//constructeur
	public Triathlon(String nom, String descriptif, String format, String lieu, boolean championnat, Date dateDebut,
			Date dateFin) {
		super(nom, descriptif, dateDebut, dateFin);
		this.format = format;
		this.lieu = lieu;
		this.championnat = championnat;
		this.listeTache = new HashMap <Integer, Tache>();
		this.listeBenevole =  new HashMap <Integer , Benevole>();
		this.listeDoc =  new HashMap <Integer, Document>();
	}
	


	//getteur et setter

	public HashMap getListeTache() {
		return listeTache;
	}

	public void setListeTache(HashMap listeTache) {
		this.listeTache = listeTache;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getLieu() {
		return lieu;
	}


	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public boolean isChampionnat() {
		return championnat;
	}

	public void setChampionnat(boolean championnat) {
		this.championnat = championnat;
	}
	
	public String getNom() {
		return super.getNom();
	}
	
	public String getDescriptif() {
		return super.getDescriptif();
	}
	
	public Date getDateDebut() {
		return super.getDateDebut();
	}
	
	public Date getDateFin() {
		return super.getDateFin();
	}
	
	public void setNom(String nom) {
		super.setNom(nom);
	}
	
	public void setDescriptif(String descriptif) {
		super.setDescriptif(descriptif);
	}
	
	public void setDateDebut(Date dateDebut) {
		super.setDateDebut(dateDebut);
	}
	
	public void setDateFin(Date dateFin) {
		super.setDateFin(dateFin);
	}
	

	
	// methode spécifique a un triatlon
	
	public void ajouterTache(Tache tache) throws IllegalArgumentException {
		if(tache == null) {
			throw new IllegalArgumentException("Erreur sur la valeur de la tache");
		}
		
		this.getListeTache().put(tache.getIdTache(),tache);
		
	}
	

	public void suprimerTache(Tache tache) throws IllegalArgumentException{
		if(tache == null ) {
			throw new IllegalArgumentException("la valeur de la tache n'est pas valide");
		}
		this.getListeTache().remove(tache);
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
