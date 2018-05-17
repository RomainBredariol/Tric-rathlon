package AccueilGeneral;

import java.net.URL;
import java.util.ResourceBundle;

import BDD.SqlRequete;
import MainApp.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
	private void clicBoutonReset() {
		SqlRequete req= new SqlRequete();
		req.Connect("delete from benevoles;");
		req.Connect("alter table benevoles AUTO_INCREMENT=1;");
		req.Connect("insert into benevoles(nom, prenom, mail, telephone) values('admin', 'admin', 'mail', 'tel')");
		req.Connect("delete from affilier;");
		req.Connect("delete from attacher;");
		req.Connect("delete from evenement;");
		req.Connect("delete from lier;");
		req.Connect("delete from participer;");
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
		this.main.showAccueilGeneral();
	}

	public void setfenetre(Stage fenetreConfirmation) {
		this.fenetre=fenetreConfirmation;
	}
}
