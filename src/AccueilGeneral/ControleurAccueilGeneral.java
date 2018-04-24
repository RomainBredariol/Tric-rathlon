package AccueilGeneral;

import Accueil.MainApp;
import Accueil.modeleAccueil;
import BDD.SqlRequete;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControleurAccueilGeneral implements ChangeListener {

	//Ne pas faire attention (c'est pour éviter une erreur qu'il cette methode non implémenter)
	@Override
	public void changed(ObservableValue observable, Object oldValue, Object newValue) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	@FXML
	private Button boutonProfil;
	
	@FXML
	private Button boutonNouveau;
	
	@FXML
	private Button boutonReprendre;
	
	
	
	private MainApp mainApp;
	

	@FXML
	private void initialize() {
	}
	
	
	public void setMainApp(MainApp main) {
		this.mainApp=main;	
	}
	
	//action du clic bouton profil qui va charger dans la mainapp la page profil
	@FXML
	private void clicBoutonProfil() {
		this.mainApp.showProfil();
	}
	
	@FXML
	private void clicBoutonNouveau() {
		
	}
	
	@FXML
	private void clicBoutonReprendre() {
		
	}
	

}
