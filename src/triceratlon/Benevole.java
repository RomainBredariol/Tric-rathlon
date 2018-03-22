package triceratlon;

public class Benevole {

	private String nom;
	private String prenom;
	private String mail;
	private String tel;
	private String commentaire;
	
	public Benevole(String nom, String prenom, String mail, String tel, String commentaire) {
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setMail(mail);
		this.setTel(tel);
		this.setCommentaire(commentaire);
	}
	
	public void affecterAUneTache(Tache tache) {
		System.out.println("ta mere la pute");
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	
	
	
	
}
