package AccueilGeneral;

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

public class ControleurChoixTriathlon {

	@FXML
	private Button ok;
	
	@FXML
	private Button supprimer;
	
	private MainApp main;
	private SqlRequete req;

	private int nbTriathlon;

	private String[] tabIdTriathlon;

	private Stage fenetre;
	
	@FXML
	private GridPane gridPaneTriathlon;

	public void setMainApp(MainApp main) {
		this.main = main;
		this.req = new SqlRequete();

		nbTriathlon = Integer.parseInt(req.getUneValeurBDD("count(nom)", "triathlon", ""));
		this.tabIdTriathlon = new String[100];
		req.getTabValeurBDD("nom", "triathlon", "", tabIdTriathlon);
		
		int k =0;
		for (int ligne = 0; ligne < (int) Math.ceil((float) nbTriathlon / 4); ligne++) {
			for (int colonne = 0; colonne < 5; colonne++) {
				if (tabIdTriathlon[k] != null) {
					Pane pane= new Pane();
					
					javafx.scene.image.ImageView imageFichier = new javafx.scene.image.ImageView("/pics/dossier.png");
					imageFichier.setFitWidth(50);
					imageFichier.setFitHeight(50);
					
					Label nom = new Label(tabIdTriathlon[k]);
					nom.setLayoutY(70);
					nom.setPrefWidth(100);
					
					RadioButton bouton = new RadioButton();
					bouton.setLayoutX(50);
					bouton.setLayoutY(20);
					
					
					pane.getChildren().add(imageFichier);
					pane.getChildren().add(nom);
					pane.getChildren().add(bouton);

					
					this.gridPaneTriathlon.add(pane, colonne, ligne);
					k++;
				}

			}
		}
		this.req.CloseConnexion();
	}
	
	@FXML
	public void clicBoutonSupprimer() throws Exception {
		this.req = new SqlRequete();

		Pane[] pane= new Pane[this.nbTriathlon];
		RadioButton[] tabRadioButtonTriathlon = new RadioButton[this.nbTriathlon];
		
		int nbRadioButtonSelected=0;
		for (int i = 0; i < nbTriathlon; i++) {
			pane[i]= (Pane) this.gridPaneTriathlon.getChildren().get(i);
			tabRadioButtonTriathlon[i]= (RadioButton) pane[i].getChildren().get(2);
			if (tabRadioButtonTriathlon[i].isSelected()) {
				int id = Integer
						.parseInt(req.getUneValeurBDD("id_triathlon", "triathlon", "nom='" + tabIdTriathlon[i] + "'"));
				this.main.conserverIdTriathlon(id);
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
			loader.setLocation(MainApp.class.getResource("/AccueilGeneral/TriathlonSuppression.fxml"));
			Stage stage = new Stage();
			AnchorPane anchor = (AnchorPane) loader.load();
			ControleurSuppressionTriathlon controleur = loader.getController();
			controleur.setfenetre(stage);
			controleur.setMainApp(this.main);
			Scene scene = new Scene(anchor);
			stage.setScene(scene);
			stage.show();
			
		}
	}
	

	@FXML
	public void clicBoutonOK() {
		this.req = new SqlRequete();

		Pane[] pane= new Pane[this.nbTriathlon];
		RadioButton[] tabRadioButtonTriathlon = new RadioButton[this.nbTriathlon];
		
		for (int i = 0; i < nbTriathlon; i++) {
			pane[i]= (Pane) this.gridPaneTriathlon.getChildren().get(i);
			tabRadioButtonTriathlon[i]= (RadioButton) pane[i].getChildren().get(2);
			if (tabRadioButtonTriathlon[i].isSelected()) {
				int id = Integer
						.parseInt(req.getUneValeurBDD("id_triathlon", "triathlon", "nom='" + tabIdTriathlon[i] + "'"));
				this.main.conserverIdTriathlon(id);
			}
		}

		this.req.CloseConnexion();
		this.fenetre.close();
		this.main.showTacheAccueil();
	}

	public void setfenetre(Stage stage) {
		this.fenetre = stage;
	}

}
