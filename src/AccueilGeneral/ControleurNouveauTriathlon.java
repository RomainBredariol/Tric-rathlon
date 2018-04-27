package AccueilGeneral;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import Accueil.MainApp;
import BDD.SqlRequete;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class ControleurNouveauTriathlon implements Initializable {

	private MainApp main;

	public void setMainApp(MainApp mainApp) {
		this.main=mainApp;
	}

	@FXML
	public void initialize() {
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private Button valider;
	
	//Genre
	@FXML
	private RadioButton amical;
	@FXML
	private RadioButton competition;
	@FXML
	private RadioButton championnat;
	
	//Type
	@FXML
	private RadioButton xs;
	@FXML
	private RadioButton s;
	@FXML
	private RadioButton m;
	@FXML
	private RadioButton l;
	@FXML
	private RadioButton half;
	@FXML
	private RadioButton xl;
	@FXML
	private RadioButton xxl;
	
	//Taches
	@FXML
	private CheckBox choix;
	@FXML
	private CheckBox validations;
	@FXML
	private CheckBox autorisations;
	@FXML
	private CheckBox premiersContacts;
	@FXML
	private CheckBox commandesFermes;
	@FXML
	private CheckBox infrastructure;
	@FXML
	private CheckBox rH;
	@FXML
	private CheckBox affectations;
	@FXML
	private CheckBox verifications;
	@FXML
	private CheckBox pub;
	@FXML
	private CheckBox verificationAchatMarchandises;
	@FXML
	private CheckBox majSite;
	@FXML
	private CheckBox ravitaillment;
	@FXML
	private CheckBox relances;
	@FXML
	private CheckBox installationZone;
	@FXML
	private CheckBox gestionVeilleEpreuve;
	@FXML
	private CheckBox Accueil;
	
	//Renseignement
	@FXML
	private TextField nom;
	@FXML
	private DatePicker dateDebut;
	@FXML
	private DatePicker dateFin;
	@FXML
	private TextField ville;
	
	
	
	@FXML
	private void clicBoutonValider() {
		SqlRequete req = new SqlRequete();
		if(this.nom.getText() != null) {
			req.Connect("insert into triathlon(nom, lieu) values('"+this.nom.getText()+"','"
		+this.ville.getText()+"');");
			if(this.dateDebut.getValue() != null)
				req.Connect("update triathlon set date="+this.dateDebut.getValue()+" where nom="+this.nom.getText());
			
		}
		
		
		
		req.CloseConnexion();
	}
	
	
	
	
	
	

}
