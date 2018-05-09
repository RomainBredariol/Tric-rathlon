package Tache;

import java.time.LocalDate;
import java.util.ArrayList;

import Accueil.MainApp;
import BDD.SqlRequete;
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
	private DatePicker dateDebut;
	
	@FXML
	private DatePicker dateFin;
	
	@FXML
	private TextField nom;
	
	@FXML
	private RadioButton prioHaute;
	
	@FXML
	private RadioButton prioNorm;
	
	@FXML
	private RadioButton prioBasse;
	
	@FXML
	private Button left;

	@FXML
	private Button right;

	@FXML
	private String mois;
	
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
		//A finir
		LocalDate datePickerDebut =null;
		LocalDate datePickerFin =null;
		SqlRequete event = new SqlRequete();
		datePickerDebut = this.dateDebut.getValue();
		datePickerFin = this.dateFin.getValue();
		String desc = this.description.getText();
		String nom = this.nom.getText();
		String prio = null;
		if(this.prioBasse.isSelected()) {
			prio = "haute";
		} else if(this.prioNorm.isSelected()) {
			prio = "normale";
		}else {
			prio = "basse";
		}
		
		event.Connect("INSERT INTO tache");
		event.CloseConnexion();
		
		this.mainApp.showTacheAccueil();
	}
	
	@FXML
	private void clicBoutonAnnuler() {
		this.dateDebut = null;
		this.dateFin = null;
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
		this.mainApp.showDocumentsAccueil();
	}
	
	@FXML
	private void clicBoutonContact() {
		this.mainApp.showContact("accueil");
	}

}
