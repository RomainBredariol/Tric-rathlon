package triceratlon;


import java.util.*;

public class Triathlon {
	
	// listage des atribut
	private String nom;
	private String descriptif ;
	private String format;
	private String lieu;
	private boolean championnat;
	private Date dateDebut;
	private Date dateFin;
	private HashMap<Integer, Tache> listeTache;
	private HashMap<Integer, Benevole> listeBenevole;
	private HashMap<Integer, Document> listeDoc;
	
	
	
	
	//constructeur
	public Triathlon(String nom, String descriptif, String format, String lieu, boolean championnat, Date dateDebut,
			Date dateFin) {
		super();
		this.nom = nom;
		this.descriptif = descriptif;
		this.format = format;
		this.lieu = lieu;
		this.championnat = championnat;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
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

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}


	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescriptif() {
		return descriptif;
	}


	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
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
