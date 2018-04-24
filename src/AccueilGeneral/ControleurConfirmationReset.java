package AccueilGeneral;

import java.net.URL;
import java.util.ResourceBundle;

import Accueil.MainApp;
import BDD.SqlRequete;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControleurConfirmationReset implements Initializable{

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
	private void clicBoutonReset() {
		SqlRequete req= new SqlRequete();
//		req.Connect("delete from benevoles; delete from affecter; delete from affiler;"
//				+ "delete from détailler; delete from fichier; delete from groupe;"
//				+ "delete from posseder; delete from tache; delete from triathlon;");
		req.CloseConnexion();
		this.fenetre.close();
		this.main.showAccueil();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	public void setfenetre(Stage fenetreConfirmation) {
		this.fenetre=fenetreConfirmation;
	}
}
