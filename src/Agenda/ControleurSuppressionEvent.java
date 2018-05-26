package Agenda;

import BDD.SqlRequete;
import MainApp.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControleurSuppressionEvent {

	@FXML
	private Button boutonAnnuler;
	
	@FXML
	private Button boutonSupprimer;
	
	private Stage fenetre;
	
	private MainApp main;
	
	private String dateId;
	private String heureId;
	
	public void setMainApp(MainApp main){
		this.main=main;
		this.dateId=this.main.getString1();
		this.heureId=this.main.getString2();
	}
	
	@FXML
	private void clicBoutonAnnuler() {
		this.fenetre.close();
	}
	
	@FXML
	private void clicBoutonSupprimer() {
		SqlRequete req= new SqlRequete();
		req.Connect("delete from evenement where date='"+dateId+"' and heure='"+heureId+"'");
		req.Connect("delete from participer where date='"+dateId+"' and heure='"+heureId+"'");
		req.CloseConnexion();
		this.fenetre.close();
		this.main.showAgendaAccueil();
	}

	

	public void setfenetre(Stage fenetreConfirmation) {
		this.fenetre=fenetreConfirmation;
	}
}
