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
	private GanttChart gantt; // regarder avec SwingNode
	
	public void setMainApp(MainApp mainApp) {
		// TODO Auto-generated method stub
		this.main=mainApp;
	}
	@Override
	public void changed(ObservableValue arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
		
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

   public static void main(String[] args) {
       launch(args);
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
	
	@FXML
	private void clicBoutonAjouter() {
		this.main.showTacheAjout();
	}
}
