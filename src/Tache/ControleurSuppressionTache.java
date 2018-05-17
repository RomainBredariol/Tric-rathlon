package Tache;

import Accueil.MainApp;
import BDD.SqlRequete;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControleurSuppressionTache {

	@FXML
	private Button boutonAnnuler;
	
	@FXML
	private Button boutonSupprimer;
	
	private Stage fenetre;
	
	private MainApp main;
	

	private int idTacheAModifier;
	
	public void setMainApp(MainApp main){
		this.main=main;
		this.idTacheAModifier=this.main.getValeurAConserver();
	}
	
	@FXML
	private void clicBoutonAnnuler() {
		this.fenetre.close();
	}
	
	@FXML
	private void clicBoutonSupprimer() {
		SqlRequete req= new SqlRequete();
		req.Connect("delete from tache where id_tache="+this.idTacheAModifier);
		req.Connect("delete from attacher where id_tache="+this.idTacheAModifier);
		req.CloseConnexion();
		this.fenetre.close();
		this.main.showTacheAccueil();
	}

	

	public void setfenetre(Stage fenetreConfirmation) {
		this.fenetre=fenetreConfirmation;
	}
}
