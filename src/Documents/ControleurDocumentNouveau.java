package Documents;

import java.io.File;

import BDD.SqlRequete;
import MainApp.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ControleurDocumentNouveau {

	private MainApp main;

	@FXML
	private Label fichierSelected;

	@FXML
	private TextField nomFichier;

	@FXML
	private Button annuler;

	@FXML
	private Button enregistrer;

	@FXML
	private Button importer;

	private File fichier;

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

	@FXML
	private void clicBoutonMenu() {
		this.main.showAccueilGeneral();
	}

	public void setMainApp(MainApp mainApp) {
		this.main = mainApp;
		this.idTriathlon = this.main.getIdTriathlon();

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
		req.CloseConnexion();
	}

	@FXML
	private void clicBoutonEnregistrer() {
		SqlRequete req = new SqlRequete();
		if (this.fichier != null) {
			if (!this.nomFichier.getText().equals("")) {
				req.Connect("insert into fichier(nom, taille, chemin, id_triathlon) values('"
						+ this.nomFichier.getText() + "','" + this.fichier.length() + "', '"
						+ this.fichier.getAbsolutePath() + "', " + this.idTriathlon + ");");
				this.main.showDocumentsAccueil();
			} else {
				req.Connect("insert into fichier(nom, taille, chemin, id_triathlon) values('"
						+ this.fichierSelected.getText() + "','" + this.fichier.length() + "', '"
						+ this.fichier.getAbsolutePath() + "', " + this.idTriathlon + ");");
				this.main.showDocumentsAccueil();
			}

		}
		
		req.CloseConnexion();
	}

	//ouvre un gestionnaire de document qui permet de charger un fichier
	@FXML
	private void clicBoutonImporter() {
		Stage fenetre = new Stage();
		FileChooser explorateur = new FileChooser();
		explorateur.setTitle("Explorateur");
		this.fichier = explorateur.showOpenDialog(fenetre);
		this.fichierSelected.setText(fichier.getName());
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
