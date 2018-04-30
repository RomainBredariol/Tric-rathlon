package Accueil;

import BDD.SqlRequete;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class accueilControleur implements ChangeListener{
	
	@FXML
	private TextField nom;
	
	//ces methodes set*** vont permettre de prendre en compte les changements effectués dans une zone de texte 
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
	private Button boutonEnregistrer;
	
	
	
	private MainApp mainApp;
	private String Nom ;
	private String Prenom;
	private String Tel;
	private String Mail;
	private modeleAccueil modele;
	
	public void setModele() {
		this.modele= new modeleAccueil(this.Nom, this.Prenom, this.Tel, this.Mail);
	}
	

	@FXML
	private void initialize() {
	}
	
	//charge la mainApp
	public void setMainApp(MainApp main) {
		this.mainApp=main;	
	}
	
	//action lors du clic sur le bouton enregistrer
	@FXML
	private void clicBoutonEnregistrer() {
		
			SqlRequete sql = new SqlRequete();
			sql.Connect("Insert into benevoles(nom, prenom, mail, telephone, commentaires) values('"+this.Nom+"', '"+this.Prenom+"', '"+this.Mail
					+"', '"+this.Tel+"', '');");
			sql.Connect("select * from benevoles;");
			sql.CloseConnexion();
			this.mainApp.showAccueilGeneral();

	}
	
	
	

	//ne pas faire attention a celle ci
	@Override
	public void changed(ObservableValue arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
		
	}
	
	
//	  
}
//
