package Contact;

import java.io.File;
import java.io.IOException;

import Accueil.MainApp;
import BDD.SqlRequete;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ControleurContact {

	// champs de l'ihm ajout et modification
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

	// bouton ihm ajout
	@FXML
	private Button enregistrer;
	@FXML
	private Button annuler;

	// bouton commun a toutes les ihm
	@FXML
	private Button ajouter;

	@FXML
	private Button modifier;

	@FXML
	private Button supprimer;

	@FXML
	private Button groupe;

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

	// recherche un contact
	@FXML
	private TextField barreRecherche;

	// affiche le fichier selectionne dans ajoutContact
	@FXML
	private Label selectedFile;

	// bouton dans ajoutContact
	@FXML
	private Button importerDoc;

	@FXML
	private Button modifierContact;

	@FXML
	private Button ok;

	private MainApp mainApp;

	private File file;

	/*
	 * cet id va determiner dans quel page on se situe il prend 4 valeurs :
	 * "Identit�" pour la page contactAjout "Nom du groupe" pour la page
	 * contact_nvx_groupe "Accueil" pour la page accueil "Modification" pour la page
	 * modifier
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
	private ComboBox<String> choixGroupe;

	@FXML
	private AnchorPane anchorPaneContact;

	public void setMainApp(MainApp main) {
		this.mainApp = main;

		// Sert a modifier un contact, je l'ai mise ici car il fallait recuperer une
		// valeur d'une autre ihm
		if (this.idPage.getText().equals("Modification")) {
			SqlRequete req = new SqlRequete();
			this.idContactAModifier = this.mainApp.getValeurAConserver();
			this.nom.setText(req.getUneValeurBDD("nom", "benevoles", "id_benevoles=" + this.idContactAModifier));
			this.prenom.setText(req.getUneValeurBDD("prenom", "benevoles", "id_benevoles=" + this.idContactAModifier));
			this.mail.setText(req.getUneValeurBDD("mail", "benevoles", "id_benevoles=" + this.idContactAModifier));
			this.adr.setText(
					req.getUneValeurBDD("commentaires", "benevoles", "id_benevoles=" + this.idContactAModifier));
			this.tel.setText(req.getUneValeurBDD("telephone", "benevoles", "id_benevoles=" + this.idContactAModifier));
			req.CloseConnexion();
		}

	}

	private int nbContact;
	private int idContactAModifier=0;

	@FXML
	public void initialize() {
		SqlRequete req = new SqlRequete();

		// cette requete recupere le nb de contact contenue dans la bd
		this.nbContact = Integer.parseInt(req.getUneValeurBDD("count(nom)", "benevoles", ""));
		
		//ce tabId est un tableau qui stocke tout les id des benevoles 
		String[] tabId = new String[nbContact];
		req.getTabValeurBDD("id_benevoles", "benevoles", tabId);
	
		// cette boucle affiche tous les contacts avec leurs donnees
		for (int i = 0; i < tabId.length; i++) {
			String nomContact = req.getUneValeurBDD("nom", "benevoles", "id_benevoles=" + tabId[i]);
			String telContact = req.getUneValeurBDD("telephone", "benevoles", "id_benevoles=" + tabId[i]);
			String mailContact = req.getUneValeurBDD("mail", "benevoles", "id_benevoles=" + tabId[i]);
			String adrContact = req.getUneValeurBDD("commentaires", "benevoles", "id_benevoles=" + tabId[i]);

			// l'architecture fxml que l'on a ici est comme ceci : VBox <- Pane <- Image,
			// Label etc
			// Donc on cree le pane et y insere tous les elements necessaire
			Pane paneContact = new Pane();

			// on defenit un objet et on lui attribut ses caracteristiques
			javafx.scene.image.ImageView imageUser = new javafx.scene.image.ImageView("/pics/user.png");
			imageUser.setLayoutX(14);
			imageUser.setLayoutY(11);
			imageUser.setFitHeight(105);
			imageUser.setFitWidth(107);
			imageUser.setPickOnBounds(true);
			imageUser.setPreserveRatio(true);
			imageUser.setEffect(new Lighting());

			Label labelNomContact = new Label(nomContact);
			labelNomContact.setLayoutX(133);
			labelNomContact.setLayoutY(11);
			labelNomContact.setPrefHeight(33);
			labelNomContact.setPrefWidth(142);
			labelNomContact.setFont(new Font(22));

			Label labelAdresseContact = new Label(adrContact);
			labelAdresseContact.setLayoutX(142);
			labelAdresseContact.setLayoutY(86);
			labelAdresseContact.setPrefHeight(21);
			labelAdresseContact.setPrefWidth(141);
			labelAdresseContact.setFont(new Font(11));

			Label labelTelContact = new Label(telContact);
			labelTelContact.setLayoutX(142);
			labelTelContact.setLayoutY(44);
			labelTelContact.setPrefHeight(21);
			labelTelContact.setPrefWidth(141);
			labelTelContact.setFont(new Font(11));

			Label labelMailContact = new Label(mailContact);
			labelMailContact.setLayoutX(143);
			labelMailContact.setLayoutY(65);
			labelMailContact.setPrefHeight(21);
			labelMailContact.setPrefWidth(142);
			labelMailContact.setFont(new Font(11));

			RadioButton boutonSelection = new RadioButton();
			boutonSelection.setLayoutX(505);
			boutonSelection.setLayoutY(90);

			// on ajoute les elements dans le pane
			paneContact.getChildren().add(imageUser);
			paneContact.getChildren().add(labelNomContact);
			paneContact.getChildren().add(labelTelContact);
			paneContact.getChildren().add(labelMailContact);
			paneContact.getChildren().add(labelAdresseContact);
			paneContact.getChildren().add(boutonSelection);

			// on ajoute le pane dans la VBox
			this.listContact.getChildren().add(paneContact);
		}

		// injecte dans le fxml le nb de checkbox en fonction du nb de contact dans la
		// bdd lorsqu'on se trouve dans l'ihm nvx groupe
		if (this.idPage.getText().equals("Nom du groupe")) {
			for (int i = 1; i <= nbContact; i++) {
				String contact = req.getUneValeurBDD("nom", "benevoles", "id_benevoles=" + i);
				this.listContactGroupe.getChildren().add(new CheckBox(contact));
			}
		}

		// pas fini
		if (this.idPage.getText().equals("Identit�")) {
			int nbGroupe = Integer.parseInt(req.getUneValeurBDD("count(nom)", "groupe", ""));
			ObservableList<String> listGroupe = FXCollections.observableArrayList("a", "b");
			this.choixGroupe = new ComboBox<String>();
			for (int i = 1; i <= nbGroupe; i++) {
				String groupe = req.getUneValeurBDD("nom", "groupe", "id_groupe =" + i);

			}

		}

		req.CloseConnexion();
	}

	// Pour la methode showContact 4 valeurs possibles: ajouter, accueil, groupe ou
	// modification
	@FXML
	private void clicBoutonAjouter() {
		this.mainApp.showContact("ajouter");
	}

	// ouvre un gestionnaire de fichier o� on peut choisir un fichier pour envoyer
	// ses donnees (nom taille etc) a la bddd
	@FXML
	private void clicBoutonImporter() {
		Stage fenetre = new Stage();
		FileChooser explorateur = new FileChooser();
		explorateur.setTitle("Explorateur");
		this.file = explorateur.showOpenDialog(fenetre);
		this.selectedFile.setText(file.getName());

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
		int idRecherche = Integer.parseInt(req.getUneValeurBDD("id_benevoles", "benevoles", "nom like '"
				+ this.barreRecherche.getText() + "%' OR prenom like'" + this.barreRecherche.getText() + "%'"));
		this.idContactAModifier = idRecherche;

		for (int i = 0; i < this.nbContact; i++) {
			this.anchorPaneContact.getChildren().remove(this.listContact);
		}

		this.listContact = new VBox();
		Pane paneContact = new Pane();

		// on defenit un objet et on lui attribut ses caracteristiques
		javafx.scene.image.ImageView imageUser = new javafx.scene.image.ImageView("/pics/user.png");
		imageUser.setLayoutX(14);
		imageUser.setLayoutY(11);
		imageUser.setFitHeight(105);
		imageUser.setFitWidth(107);
		imageUser.setPickOnBounds(true);
		imageUser.setPreserveRatio(true);
		imageUser.setEffect(new Lighting());

		Label labelNomContact = new Label(nomRecherche);
		labelNomContact.setLayoutX(133);
		labelNomContact.setLayoutY(11);
		labelNomContact.setPrefHeight(33);
		labelNomContact.setPrefWidth(142);
		labelNomContact.setFont(new Font(22));

		Label labelAdresseContact = new Label(adrRecherche);
		labelAdresseContact.setLayoutX(142);
		labelAdresseContact.setLayoutY(86);
		labelAdresseContact.setPrefHeight(21);
		labelAdresseContact.setPrefWidth(141);
		labelAdresseContact.setFont(new Font(11));

		Label labelTelContact = new Label(telRecherche);
		labelTelContact.setLayoutX(142);
		labelTelContact.setLayoutY(44);
		labelTelContact.setPrefHeight(21);
		labelTelContact.setPrefWidth(141);
		labelTelContact.setFont(new Font(11));

		Label labelMailContact = new Label(mailRecherche);
		labelMailContact.setLayoutX(143);
		labelMailContact.setLayoutY(65);
		labelMailContact.setPrefHeight(21);
		labelMailContact.setPrefWidth(142);
		labelMailContact.setFont(new Font(11));

		RadioButton boutonSelection = new RadioButton();
		boutonSelection.setLayoutX(505);
		boutonSelection.setLayoutY(90);

		// on ajoute les elements dans le pane
		paneContact.getChildren().add(imageUser);
		paneContact.getChildren().add(labelNomContact);
		paneContact.getChildren().add(labelTelContact);
		paneContact.getChildren().add(labelMailContact);
		paneContact.getChildren().add(labelAdresseContact);
		paneContact.getChildren().add(boutonSelection);

		// on ajoute le pane dans la VBox
		this.listContact.getChildren().add(paneContact);

		// on ajoute la vbox dans l'anchorPane
		this.anchorPaneContact.getChildren().add(this.listContact);

		req.CloseConnexion();
	}

	@FXML
	private void clicBoutonGroupe() {
		this.mainApp.showContact("groupe");
	}

	@FXML
	private void clicBoutonModifier() throws Exception {

		// Sert a identifier le radioButton selectionne
		if (this.idContactAModifier != 0) {
			this.mainApp.aConserver(this.idContactAModifier);
		} else {
			// on cree un tableaux de pane
			Pane[] paneContact = new Pane[this.nbContact];
			// pour chaque contact on cree un pane
			for (int i = 0; i < this.nbContact; i++) {
				paneContact[i] = (Pane) this.listContact.getChildren().get(i);
			}
			// on cree un tableau de radioButton qu'on va tester par la suite
			RadioButton[] boutonSelection = new RadioButton[this.nbContact];
			// pour chaque contact on cree un radioButton
			for (int i = 0; i < this.nbContact; i++) {
				boutonSelection[i] = (RadioButton) paneContact[i].getChildren().get(5);
			}
			// le "numero" du pane equivaut a id_benevoles de la bdd
			for (int i = 0; i < boutonSelection.length; i++) {
				if (boutonSelection[i].isSelected()) {
					Label nom = (Label) paneContact[i].getChildren().get(1);
					SqlRequete req = new SqlRequete();
					int id = Integer.parseInt(req.getUneValeurBDD("id_benevoles", "benevoles", "nom='"+nom.getText()+"'"));
					this.idContactAModifier=id;
				}
			}
			// on conserve cette valeur
			this.mainApp.aConserver(this.idContactAModifier);
		}

		// si aucun radioButton nest selectionne on affche une erreur
		if (this.mainApp.getValeurAConserver() == 0) {
			// Affiche une fenetre popUp qui signale une erreur
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/Contact/erreurRadioButtonNonSelectione.fxml"));
			Stage stage = new Stage();
			AnchorPane anchor = (AnchorPane) loader.load();
			ControleurErreur controleur = loader.getController();
			controleur.setFenetre(stage);
			Scene scene = new Scene(anchor);
			stage.setScene(scene);
			stage.show();
		} else {
			this.mainApp.showContact("modification");
		}

	}

	@FXML
	private void clicBoutonModifierContact() {
		SqlRequete req = new SqlRequete();
		req.Connect("update benevoles set nom='" + this.nom.getText() + "', prenom='" + this.prenom.getText()
				+ "', telephone='" + this.tel.getText() + "', mail='" + this.mail.getText() + "', commentaires='"
				+ this.adr.getText() + "' where id_benevoles=" + this.idContactAModifier);
	}

	public void clicBoutonSupprimer() throws Exception {
		// Sert a identifier le radioButton selectionne
		if (this.idContactAModifier != 0) {
			this.mainApp.aConserver(this.idContactAModifier);
		} else {
			// on cree un tableaux de pane
			Pane[] paneContact = new Pane[this.nbContact];
			// pour chaque contact on cree un pane
			for (int i = 0; i < this.nbContact; i++) {
				paneContact[i] = (Pane) this.listContact.getChildren().get(i);
			}
			// on cree un tableau de radioButton
			RadioButton[] boutonSelection = new RadioButton[this.nbContact];
			// pour chaque contact on cree un radioButton
			for (int i = 0; i < this.nbContact; i++) {
				boutonSelection[i] = (RadioButton) paneContact[i].getChildren().get(5);
			}
			// le "numero" du pane equivaut a id_benevoles de la bdd
			for (int i = 0; i < boutonSelection.length; i++) {
				if (boutonSelection[i].isSelected()) {
					Label nom = (Label) paneContact[i].getChildren().get(1);
					SqlRequete req = new SqlRequete();
					int id = Integer.parseInt(req.getUneValeurBDD("id_benevoles", "benevoles", "nom='"+nom.getText()+"'"));
					this.idContactAModifier=id;
				}
			}
			// on conserve cette valeur
			this.mainApp.aConserver(this.idContactAModifier);
		}

		// si aucun radioButton nest selectionne on affche une erreur
		if (this.mainApp.getValeurAConserver() == 0) {
			// Affiche une fenetre popUp qui signale une erreur
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/Contact/erreurRadioButtonNonSelectione.fxml"));
			Stage stage = new Stage();
			AnchorPane anchor = (AnchorPane) loader.load();
			ControleurErreur controleur = loader.getController();
			controleur.setFenetre(stage);
			Scene scene = new Scene(anchor);
			stage.setScene(scene);
			stage.show();
		} else {
			// Affiche une fenetre popUp qui signale une erreur
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/Contact/confirmationSuppressionContact.fxml"));
			Stage stage = new Stage();
			AnchorPane anchor = (AnchorPane) loader.load();
			ControleurSuppressionContact controleur = loader.getController();
			controleur.setfenetre(stage);
			controleur.setMainApp(this.mainApp);
			Scene scene = new Scene(anchor);
			stage.setScene(scene);
			stage.show();
		}
	}

	@FXML
	private void clicBoutonEnregistrer() {
		SqlRequete req = new SqlRequete();
		// ajoute user
		req.Connect("Insert into benevoles(nom, prenom, mail, telephone, commentaires) values('" + this.nom.getText()
				+ "', '" + this.prenom.getText() + "', '" + this.mail.getText() + "', '" + this.tel.getText() + "', '"
				+ this.adr.getText() + " " + this.cp.getText() + " " + this.ville.getText() + "');");
		// ajoute un fichier
		if(this.file != null) {
			req.Connect("insert into fichier(nom, descriptif, taille, chemin) values('" + this.file.getName()
			+ "', 'affecter au contact " + this.nom.getText() + "','" + this.file.length() + "', '"
			+ this.file.getAbsolutePath() + "');");
		}
		
		req.CloseConnexion();
		this.mainApp.showContact("accueil");
	}
	
	@FXML
	private void clicBoutonMenu() {
		this.mainApp.showAccueilGeneral();
	}
	
	@FXML
	private void clicBoutonTache() {
		this.mainApp.showTacheAccueil();
	}
	
	@FXML
	private void clicBoutonAgenda() {
		this.mainApp.showAgendaAccueil();
	}
	
	@FXML
	private void clicBoutonDocuments() {
		this.mainApp.showDocumentsAccueil();
	}
	
	@FXML
	private void clicBoutonContact() {
		this.mainApp.showContact("accueil");
	}

}
