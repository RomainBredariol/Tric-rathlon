package Documents;

import BDD.SqlRequete;
import MainApp.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ControleurDocumentModification {

	private MainApp main;

	@FXML
	private TextField nomFichier;

	@FXML
	private Button annuler;

	@FXML
	private Button enregistrer;

	private int idTriathlon;

	@FXML
	private Button ajouter;
	@FXML
	private Button modifier;
	@FXML
	private Button supprimer;
	
	private SqlRequete req;

	@FXML
	private GridPane gridPaneDoc;
	
	private int idFichierAModifier;

	@FXML
	private void clicBoutonMenu() {
		this.main.showAccueilGeneral();
	}

	public void setMainApp(MainApp mainApp) {
		this.main = mainApp;
		this.idTriathlon = this.main.getIdTriathlon();
		this.idFichierAModifier=this.main.getValeurAConserver();

		this.req = new SqlRequete();
		int nbDoc = Integer
				.parseInt(req.getUneValeurBDD("count(id_fichier)", "fichier", "id_triathlon=" + this.idTriathlon));

		String[] tabNomDoc = new String[1000];
		req.getTabValeurBDD("nom", "fichier", "id_triathlon=" + this.idTriathlon, tabNomDoc);
		int k = 0;

		for (int ligne = 0; ligne < (int) Math.ceil((float) nbDoc / 3); ligne++) {
			for (int colonne = 0; colonne < 4; colonne++) {
				if (tabNomDoc[k] != null) {
					Pane pane= new Pane();
					
					javafx.scene.image.ImageView imageFichier = new javafx.scene.image.ImageView("/pics/fichier.png");
					imageFichier.setFitWidth(50);
					imageFichier.setFitHeight(50);
					
					Label nom = new Label(tabNomDoc[k]);
					nom.setLayoutY(70);
					nom.setPrefWidth(100);
					
					RadioButton bouton = new RadioButton();
					bouton.setLayoutX(50);
					bouton.setLayoutY(20);
					
					
					pane.getChildren().add(imageFichier);
					pane.getChildren().add(nom);
					pane.getChildren().add(bouton);

					
					this.gridPaneDoc.add(pane, colonne, ligne);
					k++;
				}

			}
		}
		
		String nom = req.getUneValeurBDD("nom", "fichier", "id_fichier="+this.idFichierAModifier);
		this.nomFichier.setText(nom);
		
		
		req.CloseConnexion();
	}

	@FXML
	private void clicBoutonEnregistrer() {
		SqlRequete req = new SqlRequete();
		req.Connect("update fichier set nom='"+this.nomFichier.getText()+"' where id_fichier="+this.idFichierAModifier);
		req.CloseConnexion();
		this.main.showDocumentsAccueil();
	}

	@FXML
	private void clicBoutonAnnuler() {
		this.main.showDocumentsAccueil();
	}

	@FXML
	private void clicBoutonTache() {
		this.main.showTacheAccueil();
	}

	@FXML
	private void clicBoutonAgenda() {
		this.main.showAgendaAccueil();
	}

	@FXML
	private void clicBoutonDocuments() {
		this.main.showDocumentsAccueil();
	}

	@FXML
	private void clicBoutonContact() {
		this.main.showContact("accueil");
	}

	@FXML
	private void clicBoutonAjouter() {
		this.main.showDocumentAjout();
	}

}
