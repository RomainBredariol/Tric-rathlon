package Contact;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControleurErreur {

	@FXML
	private Button ok;
	
	private Stage stage;
	
	public void setFenetre(Stage stage) {
		this.stage=stage;
	}
	
	@FXML
	public void clicBoutonOK() {
		this.stage.close();
	}
}
