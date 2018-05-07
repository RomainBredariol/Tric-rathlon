package Agenda;

import Accueil.MainApp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ControleurAgendaAcceuil implements ChangeListener{
	
	@Override
	public void changed(ObservableValue arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private void initialize() {
	}
	
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
	private GridPane panneauMois; //il faut changer de mois en gardant la structure de la GridPane et stocker les données précedemment entrées
	
	public void setMainApp(MainApp mainApp) {
		// TODO Auto-generated method stub
		this.main=mainApp;
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
	private void clicBoutonAjouter() {
		this.main.showAgendaAjout();
	}
	
	@FXML
	private void clicBoutonSupprimer() {
		this.main.showSuppression();
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
	
}
