package Documents;

import BDD.SqlRequete;
import Contact.ControleurErreur;
import MainApp.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
	private AnchorPane anchorPaneDoc;

	@FXML
	private GridPane gridPaneDoc;

	private int idTriathlon;
	private int nbDoc;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		this.idTriathlon = this.mainApp.getIdTriathlon();

		this.req = new SqlRequete();
		this.nbDoc = Integer
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
					bouton.setLayoutX(60);
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
		this.mainApp.showDocumentAjout();
	}

	@FXML
	private void clicBoutonModifier() throws Exception {
		Pane[] tabPane = new Pane[nbDoc];
		this.req = new SqlRequete();
		for (int i = 0; i<nbDoc; i++) {
			tabPane[i]= (Pane) this.gridPaneDoc.getChildren().get(i);
		}
		
		int nbRadioButtonSelected = 0;
		RadioButton[] tabBouton = new RadioButton[nbDoc];
		for(int i = 0; i<nbDoc; i++) {
			tabBouton[i] = (RadioButton) tabPane[i].getChildren().get(2);
			if(tabBouton[i].isSelected()) {
				nbRadioButtonSelected++;
				Label nomFichier = (Label) tabPane[i].getChildren().get(1);
				int idDoc = Integer.parseInt(req.getUneValeurBDD("id_fichier", "fichier", "nom='"+nomFichier.getText()+"' and id_triathlon="+this.idTriathlon));
				this.mainApp.aConserver(idDoc);
			}
		}
		
		if(nbRadioButtonSelected != 1) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/Agenda/erreurRadioButtonNonSelectionne.fxml"));
			Stage stage = new Stage();
			AnchorPane anchor = (AnchorPane) loader.load();
			ControleurErreur controleur = loader.getController();
			controleur.setFenetre(stage);
			Scene scene = new Scene(anchor);
			stage.setScene(scene);
			stage.show();
		}else {
			this.mainApp.showDocumentModification();
		}
		this.req.CloseConnexion();
	}

	@FXML
	private void clicBoutonSupprimer() throws Exception {
		Pane[] tabPane = new Pane[nbDoc];
		this.req = new SqlRequete();
		for (int i = 0; i<nbDoc; i++) {
			tabPane[i]= (Pane) this.gridPaneDoc.getChildren().get(i);
		}
		int nbRadioButtonSelected = 0;
		RadioButton[] tabBouton = new RadioButton[nbDoc];
		for(int i = 0; i<nbDoc; i++) {
			tabBouton[i] = (RadioButton) tabPane[i].getChildren().get(2);
			if(tabBouton[i].isSelected()) {
				Label nomFichier = (Label) tabPane[i].getChildren().get(1);
				int idDoc = Integer.parseInt(req.getUneValeurBDD("id_fichier", "fichier", "nom='"+nomFichier.getText()+"' and id_triathlon="+this.idTriathlon));
				this.mainApp.aConserver(idDoc);
				nbRadioButtonSelected++;
			}
		}
		this.req.CloseConnexion();
		
		if(nbRadioButtonSelected != 1) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/Agenda/erreurRadioButtonNonSelectionne.fxml"));
			Stage stage = new Stage();
			AnchorPane anchor = (AnchorPane) loader.load();
			ControleurErreur controleur = loader.getController();
			controleur.setFenetre(stage);
			Scene scene = new Scene(anchor);
			stage.setScene(scene);
			stage.show();
		}else {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/Documents/DocumentSuppression.fxml"));
			Stage stage = new Stage();
			AnchorPane anchor = (AnchorPane) loader.load();
			ControleurSuppressionDocument controleur = loader.getController();
			controleur.setfenetre(stage);
			controleur.setMainApp(this.mainApp);
			Scene scene = new Scene(anchor);
			stage.setScene(scene);
			stage.show();
			
		}
	}

}
