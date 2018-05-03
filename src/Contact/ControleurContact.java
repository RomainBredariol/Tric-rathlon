package Contact;

import java.io.File;
import java.net.URL;



import Accueil.MainApp;
import BDD.SqlRequete;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
	private Label selectedFile;
	
	@FXML
	private Button importerDoc;
	
	private MainApp mainApp;
	
	private File file;
	
	public void setMainApp(MainApp main) {
		this.mainApp=main;
	}
	
	@FXML
	public void initialize() {
		SqlRequete req = new SqlRequete();
		int nbContact = Integer.parseInt(req.getUneValeurBDD("count(nom)", "benevoles", ""));
		
		//Recupere les valeurs d'un contact
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
	
	//ouvre un gestionnaire de fichier où on peut choisir un fichier pour l'envoyer a la bdd
	@FXML
	private void clicBoutonImporter() {
		Stage fenetre = new Stage();
		FileChooser explorateur = new FileChooser();
		explorateur.setTitle("Explorateur");
		this.file = explorateur.showOpenDialog(fenetre);
		this.selectedFile.setText(file.getName());
		
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
		//ajoute user
		req.Connect("Insert into benevoles(nom, prenom, mail, telephone, commentaires) values('"+this.nom.getText()
		+"', '"+this.prenom.getText()+"', '"+this.mail.getText()+"', '"+this.tel.getText()+"', '"+
				this.adr.getText()+" "+this.cp.getText()+" "+this.ville.getText()+"');");
		//ajoute un fichier
		req.Connect("insert into fichier(nom, descriptif, taille, chemin) values('"+this.file.getName()+"', 'affecter au contact "+this.nom.getText()+"','"
				+this.file.length()+"', '"+this.file.getAbsolutePath()+"');");
		req.CloseConnexion();
		this.mainApp.showContact("accueil");
	}

	
	
	
}
