package Accueil;

import java.io.IOException;
import java.util.HashMap;

import AccueilGeneral.ControleurAccueilGeneral;
import AccueilGeneral.ControleurConfirmationReset;
import AccueilGeneral.ControleurNouveauTriathlon;
import Profil.ControleurProfil;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


//La MainApp va nous permettre de lancer chaque pages fxml. Tout se lancera à partir d'ici!
public class MainApp extends Application {

	private Stage primaryStage;
	
	
	
	//en javafx il y a plusieurs sorte de fenetres, ici on utilise le stage et la scene (sachant que la scene est à l'intérieur du stage cf.image google)

	@Override
	public void start(Stage primaryStage) {
		
		
		//on charge le stage
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Test JavaFX");
		
		
		//appel à la méthode qui charge la scene AccueilGeneral dans le stage
		showAccueilGeneral();
	}
	
	
	

	//charge la page accueil
	public void showAccueil() {
		try {
			//le loader va permettre de charger la page
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("Accueil_1ereConnexion.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			
			//charge le controleur
			accueilControleur controleur = loader.getController();
			//charge MainApp
			controleur.setMainApp(this);
			//Charge modele
			controleur.setModele();
			
			//declare la scene 
			Scene scene = new Scene(personOverview);
			//charge scene dans stage
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//charge page AccueilGeneral
	public void showAccueilGeneral() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/AccueilGeneral/Accueil_général.fxml"));
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
			//le loader va permettre de charger la page
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/AccueilGeneral/ConfirmationReset.fxml"));
			
			//ici on declare un nouveau stage pour avoir une fenetre "pop up"
			Stage fenetreConfirmation = new Stage();
			fenetreConfirmation.setTitle("Confirmation");
			
			fenetreConfirmation.initOwner(primaryStage);
			AnchorPane personOverview = (AnchorPane) loader.load();
			
			//charge le controleur
			ControleurConfirmationReset controleur = loader.getController();
			//charge MainApp
			controleur.setfenetre(fenetreConfirmation);
			controleur.setMainApp(this);
			
			//declare la scene 
			Scene scene = new Scene(personOverview);
			//charge scene dans stage
			fenetreConfirmation.setScene(scene);
			fenetreConfirmation.show();

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
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	//Lance la MainApp
	public static void main(String[] args) {
		launch(args);
	}

}