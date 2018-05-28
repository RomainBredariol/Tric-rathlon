package AccueilGeneral;

import MainApp.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControleurErreurChoixTriathlon {

	@FXML
	private Button ok;
	
	private MainApp mainApp;
	
	private Stage fenetre;
	
	@FXML
	public void clicBoutonOK() {
		this.fenetre.close();
	}
	
	public void setMainApp(MainApp main) {
		this.mainApp=main;
	}
	
	public void setFenetre(Stage fenetre) {
		this.fenetre=fenetre;
	}
}
