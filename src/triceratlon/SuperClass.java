package triceratlon;

import java.util.Date;

public class SuperClass {
	private String nom;
	private String descriptif ;
	private Date dateDebut;
	private Date dateFin;
	
	public SuperClass(String nom, String descriptif, Date dateDebut, Date dateFin) {
		this.setNom(nom);
		this.setDescriptif(descriptif);
		this.setDateDebut(dateDebut);
		this.setDateFin(dateFin);
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescriptif() {
		return this.descriptif;
	}

	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	public Date getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
}
