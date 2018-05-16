package Accueil;

import java.io.IOException;
import java.util.HashMap;

import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import AccueilGeneral.ControleurAccueilGeneral;
import AccueilGeneral.ControleurConfirmationReset;
import AccueilGeneral.ControleurErreurChoixTriathlon;
import Agenda.ControleurAgendaAcceuil;
import Agenda.ControleurAgendaAjout;
import Agenda.ControleurAgendaModifier;
import Contact.ControleurContact;
import Documents.ControleurDocumentsAccueil;
import AccueilGeneral.ControleurNouveauTriathlon;
import Profil.ControleurProfil;
import Tache.ControleurTacheAjout;
import Tache.ControleurTaches;
import Tache.GanttChart;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

//ASTUCE : POUR MIEUX VOIR LES METHODES VOUS POUVEZ FAIRE UN CTRL+MAJ+/

//La MainApp va nous permettre de lancer chaque pages fxml. Tout se lancera � partir d'ici!
public class MainApp extends Application {

	private Stage primaryStage;
	private HashMap<String, String> contact ;
	
	private int valeurAConserver;
	private String stringAConserver;
	private String string2AConserver;
	
	private int idTriathlon;
	
	// en javafx il y a plusieurs sorte de fenetres, ici on utilise le stage et la
	// scene (sachant que la scene est a l'interieur du stage cf.image google)

	@Override
	public void start(Stage primaryStage) {
		
		//on stocke les pages contact dans une hashmap
		this.contact = new HashMap<String, String>();
		contact.put("ajouter", "/Contact/ContactAjout.fxml");
		contact.put("accueil", "/Contact/contact_accueil.fxml");
		contact.put("groupe", "/Contact/Contact_nvx_groupe.fxml");
		contact.put("modification", "/Contact/contactModification.fxml");
		
		// on charge le stage
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("tricerathlon");
		
		// appel de la methode qui charge la scene AccueilGeneral dans le stage
		showAccueilGeneral();
	}
	
	//Ces 2 methodes servent � conserevr une valeur � travers les ihm il faut cependant 
	//l'affecter en local dans la methode setMainApp
	public void aConserver(int valeur) {
		this.valeurAConserver=valeur;
	}
	
	public int getValeurAConserver() {
		return this.valeurAConserver;
	}
	
	public void stringAConserver(String valeur, String valeur2) {
		this.stringAConserver=valeur;
		this.string2AConserver=valeur2;
	}
	
	public String getString1() {
		return this.stringAConserver;
	}
	
	public String getString2() {
		return this.string2AConserver;
	}
	
	public void conserverIdTriathlon(int id) {
		this.idTriathlon=id;
	}
	
	public int getIdTriathlon() {
		return this.idTriathlon;
	}


	// charge la page accueil
	public void showAccueil() {
		try {
			// le loader va permettre de charger la page
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("Accueil_1ereConnexion.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// charge le controleur
			accueilControleur controleur = loader.getController();
			// charge MainApp
			controleur.setMainApp(this);
			// Charge modele
			controleur.setModele();

			// declare la scene
			Scene scene = new Scene(personOverview);
			// charge scene dans stage
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// charge page AccueilGeneral
	public void showAccueilGeneral() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/AccueilGeneral/Accueil_general.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			ControleurAccueilGeneral controleur = loader.getController();
			controleur.setMainApp(this);

			Scene scene = new Scene(personOverview);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showConfirmationReset() {
		try {
			// le loader va permettre de charger la page
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/AccueilGeneral/ConfirmationReset.fxml"));

			// ici on declare un nouveau stage pour avoir une fenetre "pop up"
			Stage fenetreConfirmation = new Stage();
			fenetreConfirmation.setTitle("Confirmation");

			fenetreConfirmation.initOwner(primaryStage);
			AnchorPane personOverview = (AnchorPane) loader.load();

			// charge le controleur
			ControleurConfirmationReset controleur = loader.getController();
			// charge MainApp
			controleur.setfenetre(fenetreConfirmation);
			controleur.setMainApp(this);

			// declare la scene
			Scene scene = new Scene(personOverview);
			// charge scene dans stage
			fenetreConfirmation.setScene(scene);
			fenetreConfirmation.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showErreurChoixTriathlon() {
		try {
			// le loader va permettre de charger la page
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/AccueilGeneral/erreurChoixTriathlon.fxml"));

			// ici on declare un nouveau stage pour avoir une fenetre "pop up"
			Stage fenetreErreur = new Stage();
			fenetreErreur.setTitle("Erreur");

			fenetreErreur.initOwner(primaryStage);
			AnchorPane personOverview = (AnchorPane) loader.load();

			// charge le controleur
			ControleurErreurChoixTriathlon controleur = loader.getController();
			// charge MainApp
			controleur.setFenetre(fenetreErreur);
			controleur.setMainApp(this);

			// declare la scene
			Scene scene = new Scene(personOverview);
			// charge scene dans stage
			fenetreErreur.setScene(scene);
			fenetreErreur.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showProfil() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/Profil/Profil.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			ControleurProfil controleur = loader.getController();
			controleur.setMainApp(this);

			Scene scene = new Scene(personOverview);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void showContact(String page) {
		try {
			
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(MainApp.class.getResource(this.contact.get(page)));
			AnchorPane personOverview = (AnchorPane) loader.load();
			ControleurContact controleur = loader.getController();
			controleur.setMainApp(this);

			Scene scene = new Scene(personOverview);
			
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void showNouveauTriathlon() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/AccueilGeneral/NouveauTriathlon.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			ControleurNouveauTriathlon controleur = loader.getController();
			controleur.setMainApp(this);

			Scene scene = new Scene(personOverview);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showAgendaAccueil(){
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/Agenda/agendaAcceuil.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			ControleurAgendaAcceuil controleur = loader.getController();
			controleur.setMainApp(this);

			Scene scene = new Scene(personOverview);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}	
	
	public void showAgendaAjout(){
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/Agenda/agendaAjout.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			ControleurAgendaAjout controleur = loader.getController();
			controleur.setMainApp(this);

			Scene scene = new Scene(personOverview);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void showAgendaModification(){
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/Agenda/agendaModification.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			ControleurAgendaModifier controleur = loader.getController();
			controleur.setMainApp(this);

			Scene scene = new Scene(personOverview);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void showDocumentsAccueil(){
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/Documents/Document_accueil.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			ControleurDocumentsAccueil controleur = loader.getController();
			controleur.setMainApp(this);

			Scene scene = new Scene(personOverview);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}	
	public void showSuppression(){
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/Agenda/ConfirmationSuppressionTache.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			ControleurTaches controleur = loader.getController();
			controleur.setMainApp(this);

			Scene scene = new Scene(personOverview);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void showTacheAccueil(){
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/Tache/tacheAcceuil.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			ControleurTaches controleur = loader.getController();
			controleur.setMainApp(this);

			Scene scene = new Scene(personOverview);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void showTacheAjout(){
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/Tache/tacheAjout.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			ControleurTacheAjout controleur = loader.getController();
			controleur.setMainApp(this);

			Scene scene = new Scene(personOverview);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	// Lance la MainApp
	public static void main(String[] args) {
		launch(args);
	}


}