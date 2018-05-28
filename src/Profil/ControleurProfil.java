package Profil;

import BDD.SqlRequete;
import MainApp.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControleurProfil {
	
	private MainApp mainApp;


	public void setMainApp(MainApp mainApp) {
		this.mainApp=mainApp;
	}
	
	@FXML
	private TextField nom;
	
	@FXML
	private void setNomTextField() {
		this.Nom = nom.getText();
	}
	
	
	
	@FXML
	private TextField prenom;
	
	@FXML
	private void setPrenomTextField() {
		this.Prenom = prenom.getText();
	}
	
	@FXML
	private TextField tel;
	
	@FXML
	private void setTelTextField() {
		this.Tel = tel.getText();
	}
	
	@FXML
	private TextField mail;
	
	@FXML
	private void setMailTextField() {
		this.Mail = mail.getText();
	}
	
	@FXML
	private Button boutonModifier;
	
	@FXML 
	private Button boutonRetour;
	
	private String Nom ;
	private String Prenom;
	private String Tel;
	private String Mail;
	
	//Action bouton modif
	@FXML
	private void clicBoutonModifier() {
			this.setNomTextField();
			this.setMailTextField();
			this.setPrenomTextField();
			this.setTelTextField();
			SqlRequete sql = new SqlRequete();
			sql.Connect("update benevoles set nom='"+this.Nom+"', prenom='"+this.Prenom
					+"', telephone ='"+this.Tel+"', mail='"+this.Mail+"' where id_benevoles=1;");
			
			sql.CloseConnexion();
			this.mainApp.showProfil();

	}
	
	//Clic bouton retour
	@FXML
	private void clicBoutonRetour() {
		this.mainApp.showAccueilGeneral();
	}
	
	//la méthode initialize permet de charger ce qu'on lui des qu'on fait appel à cette classe
	@FXML
	private void initialize() {
		SqlRequete requete = new SqlRequete();
		this.nom.setText(requete.getUneValeurBDD("nom", "benevoles","id_benevoles=1"));
		this.prenom.setText(requete.getUneValeurBDD("prenom", "benevoles","id_benevoles=1"));
		this.tel.setPromptText(requete.getUneValeurBDD("telephone", "benevoles","id_benevoles=1"));
		this.mail.setPromptText(requete.getUneValeurBDD("mail", "benevoles","id_benevoles=1"));
		requete.CloseConnexion();
		
	}
}
