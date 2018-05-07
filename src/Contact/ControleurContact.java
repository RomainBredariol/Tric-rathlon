package Contact;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.text.html.ImageView;

import com.sun.glass.ui.Size;

import Accueil.MainApp;
import BDD.SqlRequete;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ControleurContact extends MainApp {
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
	private TextField barreRecherche;

	@FXML
	private Button groupe;

	@FXML
	private Label selectedFile;

	@FXML
	private Button importerDoc;

	private MainApp mainApp;

	private File file;
<<<<<<< HEAD
=======
	
	/*cet id va determiner dans quel page on se situe
	 * il prend 3 valeurs : 
	 * "Identit�" pour la page contactAjout
	 * "Nom du groupe" pour la page contact_nvx_groupe
	 * "Accueil" pour la page accueil
	*/
	@FXML
	private Label idPage; 
	
>>>>>>> IHM

	/*
	 * cet id va determiner dans quel page on se situe il prend 3 valeurs :
	 * "Identit�" pour la page contactAjout "Nom du groupe" pour la page
	 * contact_nvx_groupe "Accueil" pour la page accueil
	 */
	@FXML
	private Label idPage;

	@FXML
	private CheckBox nomContactGroupe;

	@FXML
	private VBox listContactGroupe;

	@FXML
	private VBox listContact;
	
	@FXML
	private Pane paneContact;
	
	@FXML
	private AnchorPane anchorPaneContact;

	public void setMainApp(MainApp main) {
		this.mainApp = main;
	}
	
	private int nbContact;

	@FXML
	public void initialize() {
		SqlRequete req = new SqlRequete();
		
		//cette requete recupere le nb de contact contenue dans la bd
		nbContact = Integer.parseInt(req.getUneValeurBDD("count(nom)", "benevoles", ""));
		
		//cette boucle affiche tous les contacts avec leurs donnees 
		for(int i = 1; i<=nbContact; i++) {
			String nomContact = req.getUneValeurBDD("nom", "benevoles", "id_benevoles="+i);
			String telContact = req.getUneValeurBDD("telephone", "benevoles", "id_benevoles="+i);
			String mailContact = req.getUneValeurBDD("mail", "benevoles", "id_benevoles="+i);
			String adrContact = req.getUneValeurBDD("commentaires", "benevoles", "id_benevoles="+i);
			
			//l'architecture fxml que l'on a ici est comme ceci : VBox <- Pane <- Image, Label etc
			//Donc on cr�e le pane et y insere tous les elements necessaire
			this.paneContact = new Pane();
			
			//on defenit un objet et on lui attribut ses caracteristiques
			javafx.scene.image.ImageView imageUser = new javafx.scene.image.ImageView("/pics/user.png");
			imageUser.setLayoutX(14); imageUser.setLayoutY(11); imageUser.setFitHeight(105); imageUser.setFitWidth(107);
			imageUser.setPickOnBounds(true); imageUser.setPreserveRatio(true);
			imageUser.setEffect(new Lighting()); 
			
			Label labelNomContact = new Label(nomContact);
			labelNomContact.setLayoutX(133); labelNomContact.setLayoutY(11); labelNomContact.setPrefHeight(33); labelNomContact.setPrefWidth(142);
			labelNomContact.setFont(new Font(22)); 
			
			Label labelAdresseContact = new Label(adrContact);
			labelAdresseContact.setLayoutX(142); labelAdresseContact.setLayoutY(86); labelAdresseContact.setPrefHeight(21); labelAdresseContact.setPrefWidth(141);
			labelAdresseContact.setFont(new Font(11)); 
			
			Label labelTelContact = new Label(telContact);
			labelTelContact.setLayoutX(142); labelTelContact.setLayoutY(44); labelTelContact.setPrefHeight(21); labelTelContact.setPrefWidth(141);
			labelTelContact.setFont(new Font(11)); 
			
			Label labelMailContact = new Label(mailContact);
			labelMailContact.setLayoutX(143); labelMailContact.setLayoutY(65); labelMailContact.setPrefHeight(21); labelMailContact.setPrefWidth(142);
			labelMailContact.setFont(new Font(11)); 
			
			//on ajoute les elements dans le pane
			this.paneContact.getChildren().add(imageUser);
			this.paneContact.getChildren().add(labelNomContact);
			this.paneContact.getChildren().add(labelTelContact);
			this.paneContact.getChildren().add(labelMailContact);
			this.paneContact.getChildren().add(labelAdresseContact);
			
			//on ajoute le pane dans la VBox
			this.listContact.getChildren().add(this.paneContact);
		}
		
	
		//ALLELUIA : injecte dans le fxml le nb de checkbox en fonction du nb de contact dans la bdd lorsqu'on se trouve dans l'ihm nvx groupe
		if(this.idPage.getText().equals("Nom du groupe")) {
			for(int i = 1; i<=nbContact; i++) {
				String contact = req.getUneValeurBDD("nom", "benevoles", "id_benevoles="+i);
				this.listContactGroupe.getChildren().add(new CheckBox(contact));
			}
		}
		req.CloseConnexion();
	}

	// Pour la methode showContact 3 valeurs possibles: ajouter, accueil ou groupe
	@FXML
	private void clicBoutonAjouter() {
		this.mainApp.showContact("ajouter");
		//this.mainApp.getPrimaryStage().setTitle("ajouter");
	}

	@FXML
	private void clicBoutonMenu() {
		this.mainApp.showAccueilGeneral();
	}
<<<<<<< HEAD

	// ouvre un gestionnaire de fichier o� on peut choisir un fichier pour envoyer
	// ses donnees (nom taille etc) a la bddd
=======
	
	//ouvre un gestionnaire de fichier o� on peut choisir un fichier pour l'envoyer a la bdd
>>>>>>> IHM
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
		//this.mainApp.getPrimaryStage().setTitle("accueil");
	}

	@FXML
	private void rechercheContact() {
		SqlRequete req = new SqlRequete();
		String nomRecherche = req.getUneValeurBDD("nom", "benevoles", "nom like '" + this.barreRecherche.getText()
				+ "%' OR prenom like'" + this.barreRecherche.getText() + "%'");
		String adrRecherche = req.getUneValeurBDD("commentaires", "benevoles", "nom like '"
				+ this.barreRecherche.getText() + "%' OR prenom like'" + this.barreRecherche.getText() + "%'");
		String mailRecherche = req.getUneValeurBDD("mail", "benevoles", "nom like '" + this.barreRecherche.getText()
				+ "%' OR prenom like'" + this.barreRecherche.getText() + "%'");
		String telRecherche = req.getUneValeurBDD("telephone", "benevoles", "nom like '" + this.barreRecherche.getText()
				+ "%' OR prenom like'" + this.barreRecherche.getText() + "%'");

		for(int i = 0; i<this.nbContact; i++) {
			this.anchorPaneContact.getChildren().remove(this.listContact);
		}
		
		this.listContact = new VBox();
		this.paneContact = new Pane();
		
		//on defenit un objet et on lui attribut ses caracteristiques
		javafx.scene.image.ImageView imageUser = new javafx.scene.image.ImageView("/pics/user.png");
		imageUser.setLayoutX(14); imageUser.setLayoutY(11); imageUser.setFitHeight(105); imageUser.setFitWidth(107);
		imageUser.setPickOnBounds(true); imageUser.setPreserveRatio(true);
		imageUser.setEffect(new Lighting()); 
		
		Label labelNomContact = new Label(nomRecherche);
		labelNomContact.setLayoutX(133); labelNomContact.setLayoutY(11); labelNomContact.setPrefHeight(33); labelNomContact.setPrefWidth(142);
		labelNomContact.setFont(new Font(22)); 
		
		Label labelAdresseContact = new Label(adrRecherche);
		labelAdresseContact.setLayoutX(142); labelAdresseContact.setLayoutY(86); labelAdresseContact.setPrefHeight(21); labelAdresseContact.setPrefWidth(141);
		labelAdresseContact.setFont(new Font(11)); 
		
		Label labelTelContact = new Label(telRecherche);
		labelTelContact.setLayoutX(142); labelTelContact.setLayoutY(44); labelTelContact.setPrefHeight(21); labelTelContact.setPrefWidth(141);
		labelTelContact.setFont(new Font(11)); 
		
		Label labelMailContact = new Label(mailRecherche);
		labelMailContact.setLayoutX(143); labelMailContact.setLayoutY(65); labelMailContact.setPrefHeight(21); labelMailContact.setPrefWidth(142);
		labelMailContact.setFont(new Font(11)); 
		
		//on ajoute les elements dans le pane
		this.paneContact.getChildren().add(imageUser);
		this.paneContact.getChildren().add(labelNomContact);
		this.paneContact.getChildren().add(labelTelContact);
		this.paneContact.getChildren().add(labelMailContact);
		this.paneContact.getChildren().add(labelAdresseContact);
		
		//on ajoute le pane dans la VBox
		this.listContact.getChildren().add(this.paneContact);
		
		//on ajoute la vbox dans l'anchorPane
		this.anchorPaneContact.getChildren().add(this.listContact);
		

		req.CloseConnexion();
	}

	@FXML
	private void clicBoutonGroupe() {
		this.mainApp.showContact("groupe");
		//this.mainApp.getPrimaryStage().setTitle("groupe");
	}

	@FXML
	private void clicBoutonEnregistrer() {
		SqlRequete req = new SqlRequete();
		// ajoute user
		req.Connect("Insert into benevoles(nom, prenom, mail, telephone, commentaires) values('" + this.nom.getText()
				+ "', '" + this.prenom.getText() + "', '" + this.mail.getText() + "', '" + this.tel.getText() + "', '"
				+ this.adr.getText() + " " + this.cp.getText() + " " + this.ville.getText() + "');");
		// ajoute un fichier
		req.Connect("insert into fichier(nom, descriptif, taille, chemin) values('" + this.file.getName()
				+ "', 'affecter au contact " + this.nom.getText() + "','" + this.file.length() + "', '"
				+ this.file.getAbsolutePath() + "');");
		req.CloseConnexion();
		this.mainApp.showContact("accueil");
	}
<<<<<<< HEAD

=======
>>>>>>> IHM
}
