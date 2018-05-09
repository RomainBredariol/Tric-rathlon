package Documents;

import Accueil.MainApp;
import javafx.fxml.FXML;

public class ControleurDocumentsAccueil {

	private MainApp mainApp;

	public void setMainApp(MainApp mainApp) {
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
		this.mainApp.showDocumentsAccueil();
	}
	
	@FXML
	private void clicBoutonContact() {
		this.mainApp.showContact("accueil");
	}

}
