package Accueil;

public class modeleAccueil {

    private  String nom;
    private  String prenom;
    private  String tel;
    private  String mail;

   
   

	public modeleAccueil(String nom, String prenom, 
			String tel, String mail) {
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setTel(tel);
		this.setMail(mail);
	}




	public String getNom() {
		return nom;
	}




	public void setNom(String nom) {
		this.nom = nom;
	}




	public String getPrenom() {
		return prenom;
	}




	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}




	public String getTel() {
		return tel;
	}




	public void setTel(String tel) {
		this.tel = tel;
	}




	public String getMail() {
		return mail;
	}




	public void setMail(String mail) {
		this.mail = mail;
	}




	
	

    
}