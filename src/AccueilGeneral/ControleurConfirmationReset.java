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
		req.Connect("delete from benevoles;");
		req.Connect("alter table benevoles AUTO_INCREMENT=1;");
		req.Connect("delete from affecter;");
		req.Connect("delete from affilier;");
		req.Connect("delete from d�tailler;");
		req.Connect("delete from fichier;");
		req.Connect("alter table fichier AUTO_INCREMENT=1;");
		req.Connect("delete from groupe;");
		req.Connect("alter table groupe AUTO_INCREMENT=1;");
		req.Connect("delete from posseder;");
		req.Connect("delete from tache;");
		req.Connect("alter table tache AUTO_INCREMENT=1;");
		req.Connect("delete from triathlon;");
		req.Connect("alter table triathlon AUTO_INCREMENT=1;");
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
