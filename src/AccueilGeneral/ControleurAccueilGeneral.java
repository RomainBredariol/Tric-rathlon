package AccueilGeneral;

import MainApp.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControleurAccueilGeneral {

	@FXML
	private Button boutonProfil;

	@FXML
	private Button boutonNouveau;

	@FXML
	private Button boutonReprendre;

	@FXML
	private Button boutonReset;

	private MainApp mainApp;

	public void setMainApp(MainApp main) {
		this.mainApp = main;
	}

	// action du clic bouton profil qui va charger dans la mainapp la page profil
	@FXML
	private void clicBoutonProfil() {
		this.mainApp.showProfil();
	}

	// Action bouton Reset: supprime toutes les données de la bdd et charge
	// l'accueil de 1ere connexion
	@FXML
	private void clicBoutonReset() {
		this.mainApp.showConfirmationReset();
	}

	@FXML
	private void clicBoutonNouveau() {
		this.mainApp.showNouveauTriathlon();
	}

	@FXML
	private void clicBoutonReprendre() throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("/AccueilGeneral/choixTriathlon.fxml"));
		Stage stage = new Stage();
		AnchorPane anchor = (AnchorPane) loader.load();
		ControleurChoixTriathlon controleur = loader.getController();
		controleur.setfenetre(stage);
		controleur.setMainApp(this.mainApp);
		Scene scene = new Scene(anchor);
		stage.setScene(scene);
		stage.show();
	}

}
