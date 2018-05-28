package Documents;

import BDD.SqlRequete;
import MainApp.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControleurSuppressionDocument {

	@FXML
	private Button boutonAnnuler;
	
	@FXML
	private Button boutonSupprimer;
	
	private Stage fenetre;
	
	private MainApp main;
	private int idDoc;

	
	public void setMainApp(MainApp main){
		this.main=main;
		this.idDoc = this.main.getValeurAConserver();
	}
	
	@FXML
	private void clicBoutonAnnuler() {
		this.fenetre.close();
	}
	
	@FXML
	private void clicBoutonSupprimer() {
		SqlRequete req= new SqlRequete();
		req.Connect("delete from fichier where id_fichier="+this.idDoc);
		req.Connect("delete from posseder where id_fichier="+this.idDoc);
		req.Connect("delete from lier where id_fichier="+this.idDoc);
		req.CloseConnexion();
		this.fenetre.close();
		this.main.showDocumentsAccueil();
	}

	

	public void setfenetre(Stage fenetreConfirmation) {
		this.fenetre=fenetreConfirmation;
	}
}
