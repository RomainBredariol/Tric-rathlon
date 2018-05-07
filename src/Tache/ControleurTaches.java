package Tache;

import Accueil.MainApp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ControleurTaches extends MainApp implements ChangeListener{

	private MainApp main;
	
	@FXML
	private Button tache;
	
	@FXML
	private Button agenda;
	
	@FXML
	private Button contact;
	
	@FXML
	private Button document;
	
	@FXML
	private Button menu;
	
	@FXML
	private Button ajouter;

	@FXML
	private Button modifier;

	@FXML
	private Button supprimer;
	
	@FXML
	private GanttChart gantt = new GanttChart("Triathlon"); // A tester
	
	public void setMainApp(MainApp mainApp) {
		// TODO Auto-generated method stub
		this.main=mainApp;
	}
	@Override
	public void changed(ObservableValue arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private void clicBoutonMenu() {
		this.main.showAccueilGeneral();
	}
	
	@FXML
	private void clicBoutonTache() {
		this.main.showTacheAccueil();
	}
	
	@FXML
	private void clicBoutonAgenda() {
		this.main.showAgendaAccueil();
	}
	
	@FXML
	private void clicBoutonDocuments() {
		this.main.showAccueilGeneral(); // A remplacer avec la méthode pour afficher la page de Documents
	}
	
	@FXML
	private void clicBoutonContact() {
		this.main.showContact("accueil");
	}
	
	@FXML
	private void clicBoutonSupprimer() {
		this.main.showSuppression();
	}
}
