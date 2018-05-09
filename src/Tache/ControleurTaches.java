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
import javafx.scene.text.Text;

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
	private Text mois;
	
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
		switch(mois.getText()) {
		case "Janvier" :
			this.mois.setText("Decembre");
			break;
		case "Fevrier" :
			this.mois.setText("Janvier");
			break;
		case "Mars" :
			this.mois.setText("Fevrier");
			break;
		case "Avril" :
			this.mois.setText("Mars");
			break;
		case "Mai" :
			this.mois.setText("Avril");
			break;
		case "Juin" :
			this.mois.setText("Mai");
			break;
		case "Juillet" :
			this.mois.setText("Juin");
			break;
		case "Aout" :
			this.mois.setText("Juillet");
			break;
		case "Septembre" :
			this.mois.setText("Aout");
			break;
		case "Octobre" :
			this.mois.setText("Septembre");
			break;
		case "Novembre" :
			this.mois.setText("Octobre");
			break;
		case "Decembre" :
			this.mois.setText("Novembre");
			break;
		}
	}
	
	@FXML
	private void clicBoutonMoisSuivant() {
		switch(mois.getText()) {
		case "Janvier" :
			this.mois.setText("Fevrier");
			break;
		case "Fevrier" :
			this.mois.setText("Mars");
			break;
		case "Mars" :
			this.mois.setText("Avril");
			break;
		case "Avril" :
			this.mois.setText("Mai");
			break;
		case "Mai" :
			this.mois.setText("Juin");
			break;
		case "Juin" :
			this.mois.setText("Juillet");
			break;
		case "Juillet" :
			this.mois.setText("Aout");
			break;
		case "Aout" :
			this.mois.setText("Septembre");
			break;
		case "Septembre" :
			this.mois.setText("Octobre");
			break;
		case "Octobre" :
			this.mois.setText("Novembre");
			break;
		case "Novembre" :
			this.mois.setText("Decembre");
			break;
		case "Decembre" :
			this.mois.setText("Janvier");
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
