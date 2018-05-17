package AccueilGeneral;

import BDD.SqlRequete;
import MainApp.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ControleurChoixTriathlon {

	@FXML
	private Button ok;
	
	@FXML
	private HBox hbox;
	
	private MainApp main;
	private SqlRequete req;
	
	private int nbTriathlon;
	
	private String[] tabIdTriathlon;

	private Stage fenetre;
	
	public void setMainApp(MainApp main) {
		this.main=main;
		this.req=new SqlRequete();
		
		nbTriathlon = Integer.parseInt(req.getUneValeurBDD("count(nom)", "triathlon", ""));
		this.tabIdTriathlon=new String[nbTriathlon];
		req.getTabValeurBDD("nom", "triathlon", "", tabIdTriathlon);
		for(int i = 0; i<tabIdTriathlon.length; i++) {
			this.hbox.getChildren().add(new RadioButton(tabIdTriathlon[i]));
		}
		this.req.CloseConnexion();
	}
	
	@FXML
	public void clicBoutonOK() {
		this.req = new SqlRequete();
		
		RadioButton[] tabRadioButtonTriathlon = new RadioButton[this.nbTriathlon];
		for(int i = 0; i<nbTriathlon; i++) {
			tabRadioButtonTriathlon[i] = (RadioButton) this.hbox.getChildren().get(i);
			if(tabRadioButtonTriathlon[i].isSelected()) {
				int id = Integer.parseInt(req.getUneValeurBDD("id_triathlon", "triathlon", "nom='"+tabIdTriathlon[i]+"'"));
				this.main.conserverIdTriathlon(id);
			}
		}
		this.req.CloseConnexion();
		this.fenetre.close();
		this.main.showTacheAccueil();
	}

	public void setfenetre(Stage stage) {
		this.fenetre=stage;
	}
	
	
}
