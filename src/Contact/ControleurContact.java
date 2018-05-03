package Contact;

import Accueil.MainApp;
import BDD.SqlRequete;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
	private TextField adr;
	@FXML
	private TextField cp;
	@FXML
	private TextField ville;
	
	@FXML
	private Button enregistrer;
	@FXML
	private Button annuler;
	
	
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
	
	@FXML
	private Button groupe;
	
	@FXML
	private Label nomContact;
	@FXML
	private Label telContact;
	@FXML
	private Label mailContact;
	@FXML
	private Label adrContact;
	
	@FXML
	private AnchorPane [] listContact;
	
	private MainApp mainApp;
	
	public void setMainApp(MainApp main) {
		this.mainApp=main;
	}
	
	@FXML
	public void initialize() {
		SqlRequete req = new SqlRequete();
		int nbContact = Integer.parseInt(req.getUneValeurBDD("count(nom)", "benevoles", ""));
		this.nomContact.setText(req.getUneValeurBDD("nom", "benevoles", "id_benevoles = 2"));
		this.telContact.setText(req.getUneValeurBDD("telephone", "benevoles", "id_benevoles = 2"));
		this.mailContact.setText(req.getUneValeurBDD("mail", "benevoles", "id_benevoles = 2"));
		this.adrContact.setText(req.getUneValeurBDD("commentaires", "benevoles", "id_benevoles = 2"));
		req.CloseConnexion();
		
		
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
	private void clicBoutonGroupe() {
		this.mainApp.showContact("groupe");
	}
	
	@FXML
	private void clicBoutonEnregistrer() {
		SqlRequete req = new SqlRequete();
		req.Connect("Insert into benevoles(nom, prenom, mail, telephone, commentaires) values('"+this.nom.getText()
		+"', '"+this.prenom.getText()+"', '"+this.mail.getText()+"', '"+this.tel.getText()+"', '"+
				this.adr.getText()+" "+this.cp.getText()+" "+this.ville.getText()+"');");
		req.CloseConnexion();
		this.mainApp.showContact("accueil");
	}

	
	
	
}
