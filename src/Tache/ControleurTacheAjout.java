package Tache;

import java.util.ArrayList;

import Accueil.MainApp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControleurTacheAjout implements ChangeListener{
	
	@FXML
	private Button valider;
	
	@FXML
	private Button annuler;
	
	@FXML
	private TextArea description; //TextArea : plusieurs lignes
	
	@FXML
	private Button ajouter;
	
	@FXML
	private ArrayList<CheckBox> contactList; //On en fait une liste
	
	@FXML
	private DatePicker date;
	
	@FXML
	private TextField nom;
	
	@FXML
	private RadioButton prioHaute;
	
	@FXML
	private RadioButton prioNorm;
	
	@FXML
	private RadioButton prioBasse;
	
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

	private MainApp mainApp;

	public void setMainApp(MainApp mainApp) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private void initialize() {
	}
	
	@FXML
	private void clicBoutonValider() {
		//A faire
		this.mainApp.showTacheAccueil();
	}
	
	@FXML
	private void clicBoutonAnnuler() {
		this.date = null;
		this.nom=null;
		this.contactList = null;
		this.description = null;
		this.prioBasse = null;
		this.prioNorm = null;
		this.prioHaute = null;
		mainApp.showTacheAccueil();
	}

	@Override
	public void changed(ObservableValue arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private void clicBoutonMenu() {
		this.mainApp.showAccueilGeneral();
	}
	
	@FXML
	private void clicBoutonTache() {
		this.mainApp.showTacheAccueil();
	}
	
	@FXML
	private void clicBoutonAgenda() {
		this.mainApp.showAgendaAccueil();
	}
	
	@FXML
	private void clicBoutonDocuments() {
		this.mainApp.showAccueilGeneral(); // A remplacer avec la méthode pour afficher la page de Documents
	}
	
	@FXML
	private void clicBoutonContact() {
		this.mainApp.showContact("accueil");
	}

}
