package Documents;

import Accueil.MainApp;
import BDD.SqlRequete;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class ControleurDocumentsAccueil {

	private MainApp mainApp;
	private SqlRequete req;
	
	@FXML
	private Button ajouter;
	@FXML
	private Button modifier;
	@FXML
	private Button supprimer;
	
	@FXML
	private Pane paneDocument;

	public void setMainApp(MainApp mainApp) {
		this.mainApp=mainApp;
	}
	
	@FXML
	public void initialize() {
		this.req = new SqlRequete();
		int nbDoc = Integer.parseInt(req.getUneValeurBDD("count(id_fichier)", "fichier", ""));
		
		String[] tabIdDoc = new String[nbDoc];
		req.getTabValeurBDD("id_fichier", "fichier", tabIdDoc);
		
		for(int i = 0; i<nbDoc ; i++) {
			String nomFichier = req.getUneValeurBDD("nom", "fichier", "id_fichier="+tabIdDoc[i]);
			Label labelFichier = new Label(nomFichier);
			
			javafx.scene.image.ImageView imageFichier = new javafx.scene.image.ImageView("/pics/fichier.png");
			
			this.paneDocument.getChildren().add(imageFichier);
			this.paneDocument.getChildren().add(labelFichier);
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
	
	@FXML
	private void clicBoutonAjouter() {
		this.mainApp.showContact("accueil");
	}
	
	@FXML
	private void clicBoutonModifier() {
		this.mainApp.showContact("accueil");
	}
	
	@FXML
	private void clicBoutonSupprimer() {
		this.mainApp.showContact("accueil");
	}

}
