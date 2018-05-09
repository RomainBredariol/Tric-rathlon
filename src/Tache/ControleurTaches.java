package Tache;

import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import Accueil.MainApp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ControleurTaches implements ChangeListener{

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
	private Button left;

	@FXML
	private Button right;

	@FXML
	private String mois;
	
	@FXML
	private GanttChart gantt; // regarder avec SwingNode
	
	public void setMainApp(MainApp mainApp) {
		// TODO Auto-generated method stub
		this.main=mainApp;
	}
	@Override
	public void changed(ObservableValue arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private void clicBoutonMoisPrecedent() {
		switch(mois) {
		case "Janvier" :
			this.mois = "Decembre";
			break;
		case "Fevrier" :
			this.mois = "Janvier";
			break;
		case "Mars" :
			this.mois = "Fevrier";
			break;
		case "Avril" :
			this.mois = "Mars";
			break;
		case "Mai" :
			this.mois = "Avril";
			break;
		case "Juin" :
			this.mois = "Mai";
			break;
		case "Juillet" :
			this.mois = "Juin";
			break;
		case "Aout" :
			this.mois = "Juillet";
			break;
		case "Septembre" :
			this.mois = "Aout";
			break;
		case "Octobre" :
			this.mois = "Septembre";
			break;
		case "Novembre" :
			this.mois = "Octobre";
			break;
		case "Decembre" :
			this.mois = "Novembre";
			break;
		}
	}
	
	@FXML
	private void clicBoutonMoisSuivant() {
		switch(mois) {
		case "Janvier" :
			this.mois = "Fevrier";
			break;
		case "Fevrier" :
			this.mois = "Mars";
			break;
		case "Mars" :
			this.mois = "Avril";
			break;
		case "Avril" :
			this.mois = "Mai";
			break;
		case "Mai" :
			this.mois = "Juin";
			break;
		case "Juin" :
			this.mois = "Juillet";
			break;
		case "Juillet" :
			this.mois = "Aout";
			break;
		case "Aout" :
			this.mois = "Septembre";
			break;
		case "Septembre" :
			this.mois = "Octobre";
			break;
		case "Octobre" :
			this.mois = "Novembre";
			break;
		case "Novembre" :
			this.mois = "Decembre";
			break;
		case "Decembre" :
			this.mois = "Janvier";
			break;
		}
	}
	
   public void showGantt() { //A tester
	      SwingUtilities.invokeLater(() -> {
	         gantt = new GanttChart("Triathlon");
	         gantt.setSize(800, 400);
	         gantt.setLocationRelativeTo(null);
	         //gantt.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	         gantt.setVisible(true);
	      });
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
		this.main.showDocumentsAccueil();
	}
	
	@FXML
	private void clicBoutonContact() {
		this.main.showContact("accueil");
	}
	
	@FXML
	private void clicBoutonSupprimer() {
		this.main.showSuppression();
	}
	
	@FXML
	private void clicBoutonAjouter() {
		this.main.showTacheAjout();
	}
}
