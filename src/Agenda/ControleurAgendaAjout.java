package Agenda;

import java.time.LocalDate;

import Accueil.MainApp;
import BDD.SqlRequete;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


public class ControleurAgendaAjout implements ChangeListener{

	@Override
	public void changed(ObservableValue arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
		
	}

	@FXML
	private Button valider;
	
	@FXML
	private Button annuler;
	
	@FXML
	private TextArea description; //TextArea : plusieurs lignes
	
	@FXML
	private ColorPicker couleur; 
	
	@FXML
	private ChoiceBox<?> horaires; //Check/ChoiceBoxs avec plusieurs choix, impossible à anticiper pour le traitement...
	
	@FXML
	private CheckBox contact; //Check/ChoiceBoxs avec plusieurs choix, impossible à anticiper pour le traitement...

	@FXML
	private DatePicker date;
	
	@FXML
	private TextField nom;

	private MainApp main;
	
	public void setMainApp(MainApp mainApp) {
		this.main=mainApp;
	}
	
	@FXML
	private void initialize() {
	}
	
	@FXML
	private void clicBoutonValider() {
		LocalDate datePicker =null;
		SqlRequete event = new SqlRequete();
		datePicker = this.date.getValue();
		Color couleur = this.couleur.getValue();
		String desc = this.description.getText();
		String nom = this.nom.getText();
		
		//heure et contacts compliqués à mettre dans la requete car impossible(?) de traiter les checkboxs
		event.Connect("INSERT INTO evenement(Date,heure,Nom,Description,Couleur,Contact,ID_Triathlon)"
				+ " values('"+datePicker.toString()+"', '"/*ici l'heure*/ +nom.toString()+"', '"+desc.toString()+"', '"+couleur.toString()/* ici les contacts*/);
		event.CloseConnexion();
	}
	
	@FXML
	private void clicBoutonAnnuler() {
		//fermeture du menu ?
		this.date = null;
		this.nom=null;
		this.contact = null;
		this.horaires = null;
		this.description = null;
		this.couleur = null;
	}
}
