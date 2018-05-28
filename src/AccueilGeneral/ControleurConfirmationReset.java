package AccueilGeneral;

import MainApp.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControleurConfirmationReset{

	@FXML
	private Button boutonAnnuler;
	
	@FXML
	private Button boutonReset;
	
	private Stage fenetre;
	
	private MainApp main;
	
	public void setMainApp(MainApp mainApp) {
		this.main=mainApp;
	}
	
	
	@FXML
	private void clicBoutonAnnuler() {
		this.fenetre.close();
	}
	
	@FXML
	private void clicBoutonReset() throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("/AccueilGeneral/warningTriathlon.fxml"));
		Stage stage = new Stage();
		AnchorPane anchor = (AnchorPane) loader.load();
		ControleurWarningTriathlon controleur = loader.getController();
		controleur.setMainApp(this.main);
		controleur.setfenetre(stage);
		Scene scene = new Scene(anchor);
		stage.setScene(scene);
		this.fenetre.close();
		stage.show();
		
	}

	public void setfenetre(Stage fenetreConfirmation) {
		this.fenetre=fenetreConfirmation;
	}
}
