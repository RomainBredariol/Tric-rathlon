package Agenda;

import Accueil.MainApp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;


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
	private ChoiceBox horaires; 
	
	@FXML
	private CheckBox contact;

	@FXML
	private DatePicker date;

	private MainApp main;
	
	public void setMainApp(MainApp mainApp) {
		this.main=mainApp;
	}
	
	@FXML
	private void initialize() {
	}
	
	
	public ControleurAgendaAjout() {
		
	}
	
	@FXML
	private void clicBoutonValider() {
		
	}
	
	@FXML
	private void clicBoutonAnnuler() {
		
	}
}
