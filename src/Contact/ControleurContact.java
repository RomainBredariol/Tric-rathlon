package Contact;

import Accueil.MainApp;
import BDD.SqlRequete;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControleurContact {
	
	@FXML
	private TextField prenom;
	@FXML
	private TextField nom;
	@FXML
	private TextField tel;
	@FXML
	private TextField mail;
	@FXML
	private TextField rue;
	@FXML
	private TextField complementAdr;
	@FXML
	private TextField cp;
	@FXML
	private TextField ville;
	
	@FXML
	private Button enregistrer;
	@FXML
	private Button annuler;
	@FXML
	private Button groupe;
	
	@FXML
	private Button ajouter;
	
	@FXML
	private Button modifier;
	
	@FXML
	private Button supprimer;
	
	@FXML
	private Button menu;
	
	@FXML
	private Button tache;
	
	@FXML
	private Button agenda;
	
	@FXML
	private Button contact;
	
	@FXML
	private Button document;
	
	private MainApp mainApp;
	
	public void setMainApp(MainApp main) {
		this.mainApp=main;
	}
	
	//Pour la methode showContact 3 valeurs possibles: ajouter, accueil ou groupe
	@FXML
	private void clicBoutonAjouter() {
		this.mainApp.showContact("ajouter");
	}
	
	@FXML
	private void clicBoutonMenu() {
		this.mainApp.showAccueilGeneral();
	}
	
	@FXML
	private void clicBoutonContact() {
		this.mainApp.showContact("accueil");
	}
	
	@FXML
	private void clicBoutonEnregistrer() {
		SqlRequete req = new SqlRequete();
		req.Connect("Insert into benevoles(nom, prenom, mail, telephone, commentaires) values('"+this.nom.getText()
		+"', '"+this.prenom.getText()+"', '"+this.mail.getText()+"', '"+this.tel.getText()+"', '"+
				this.rue.getText()+" "+this.complementAdr.getText()+" "+this.cp.getText()+" "+this.ville.getText()+"');");
		req.CloseConnexion();
		
	}

	
	
	
}
