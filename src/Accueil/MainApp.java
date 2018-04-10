package Accueil;

import java.io.IOException;

import AccueilGeneral.ControleurAccueilGeneral;
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
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Test JavaFX");

		showProfil();		
	}


	public void showAccueil() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("Accueil_1ereConnexion.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			accueilControleur controleur = loader.getController();
			controleur.setMainApp(this);
			controleur.setModele();
			
			Scene scene = new Scene(personOverview);
			primaryStage.setScene(scene);
			primaryStage.show();
			

			// Set person overview into the center of root layout.
			//rootLayout.setCenter(personOverview);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
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
			

			// Set person overview into the center of root layout.
			//rootLayout.setCenter(personOverview);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the main stage.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
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
			

			// Set person overview into the center of root layout.
			//rootLayout.setCenter(personOverview);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}