package Agenda;

import java.io.IOException;
import java.sql.Date;

import Accueil.MainApp;
import BDD.SqlRequete;
import Contact.ControleurErreur;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControleurAgendaAcceuil {

	private MainApp main;
	private SqlRequete req;

	@FXML
	private Button tache;

	@FXML
	private Button agenda;

	@FXML
	private Button contact;

	@FXML
	private Button document;

	@FXML
	private Button menu;

	@FXML
	private Button ajouter;

	@FXML
	private Button modifier;

	@FXML
	private Button supprimer;

	@FXML
	private Button left;

	@FXML
	private Button right;

	@FXML
	private Text mois;

	@FXML
	private ListView listViewEvent;

	@FXML
	private GridPane panneauMois; // il faut changer de mois en gardant la structure de la GridPane et stocker les
									// données précedemment entrées

	private int nbEvent;
	private String[] tabDateEvent;
	private String[] tabHeureEvent;
	
	private int idTriathlon;

	public void setMainApp(MainApp mainApp) {
		this.main = mainApp;
		
		this.idTriathlon = this.main.getIdTriathlon();
		
		this.req = new SqlRequete();

		nbEvent = Integer.parseInt(req.getUneValeurBDD("count(nom)", "evenement", "id_triathlon="+this.idTriathlon));
		tabDateEvent = new String[nbEvent];
		req.getTabValeurBDD("date", "evenement", "id_triathlon="+this.idTriathlon, tabDateEvent);
		tabHeureEvent = new String[nbEvent];
		req.getTabValeurBDD("heure", "evenement", "id_triathlon="+this.idTriathlon, tabHeureEvent);

		// ajout des RadioButton pour chaque event
		for (int i = 0; i < nbEvent; i++) {
			String nomEvent = req.getUneValeurBDD("nom", "evenement", "date='" + tabDateEvent[i] + 
					"' and heure='" + tabHeureEvent[i] + "' and id_triathlon="+this.idTriathlon);
			this.listViewEvent.getItems().add(new RadioButton(nomEvent));
			this.listViewEvent.getItems().add(new Label(tabHeureEvent[i] + " || " + tabDateEvent[i]));
		}
	}

	@FXML
	private void initialize() {
		
		
	}

	@FXML
	private void clicBoutonModifier() throws Exception {
		RadioButton[] tabRadioButtonEvent = new RadioButton[nbEvent];
		int nbRadioButtonSelected =0 ;
		
		int j = 0;
		for (int i = 0; i < nbEvent; i++) {
			tabRadioButtonEvent[i] = (RadioButton) this.listViewEvent.getItems().get(j);
			j = j + 2;
		}
		for (int i = 0; i < tabRadioButtonEvent.length; i++) {
			if (tabRadioButtonEvent[i].isSelected()) {
				nbRadioButtonSelected++;
				String dateId = tabDateEvent[i];
				String heureID = tabHeureEvent[i];
				heureID = heureID.substring(0, heureID.length() - 3);
				this.main.stringAConserver(dateId, heureID);
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
			this.main.showAgendaModification();
		}
		
	}

	@FXML
	private void clicBoutonMenu() {
		this.main.showAccueilGeneral();
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
		this.main.showAgendaAjout();
	}

	@FXML
	private void clicBoutonSupprimer() throws Exception {
		RadioButton[] tabRadioButtonEvent = new RadioButton[nbEvent];
		int j = 0;
		int nbRadioButtonSelected = 0;
		for (int i = 0; i < nbEvent; i++) {
			tabRadioButtonEvent[i] = (RadioButton) this.listViewEvent.getItems().get(j);
			j = j + 2;
		}
		for (int i = 0; i < tabRadioButtonEvent.length; i++) {
			if (tabRadioButtonEvent[i].isSelected()) {
				nbRadioButtonSelected++;
				String dateId = tabDateEvent[i];
				String heureID = tabHeureEvent[i];
				
				this.main.stringAConserver(dateId, heureID);
			}
		}
		if (nbRadioButtonSelected != 1) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/Agenda/erreurRadioButtonNonSelectionne.fxml"));
			Stage stage = new Stage();
			AnchorPane anchor = (AnchorPane) loader.load();
			ControleurErreur controleur = loader.getController();
			controleur.setFenetre(stage);
			Scene scene = new Scene(anchor);
			stage.setScene(scene);
			stage.show();
		} else {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/Agenda/confirmationSuppressionEvent.fxml"));
			Stage stage = new Stage();
			AnchorPane anchor = (AnchorPane) loader.load();
			ControleurSuppressionEvent controleur = loader.getController();
			controleur.setMainApp(this.main);
			controleur.setfenetre(stage);
			Scene scene = new Scene(anchor);
			stage.setScene(scene);
			stage.show();
		}
	}

	@FXML
	private void clicBoutonMoisPrecedent() {
		switch (mois.getText()) {
		case "Janvier":
			this.mois.setText("Decembre");
			break;
		case "Fevrier":
			this.mois.setText("Janvier");
			break;
		case "Mars":
			this.mois.setText("Fevrier");
			break;
		case "Avril":
			this.mois.setText("Mars");
			break;
		case "Mai":
			this.mois.setText("Avril");
			break;
		case "Juin":
			this.mois.setText("Mai");
			break;
		case "Juillet":
			this.mois.setText("Juin");
			break;
		case "Aout":
			this.mois.setText("Juillet");
			break;
		case "Septembre":
			this.mois.setText("Aout");
			break;
		case "Octobre":
			this.mois.setText("Septembre");
			break;
		case "Novembre":
			this.mois.setText("Octobre");
			break;
		case "Decembre":
			this.mois.setText("Novembre");
			break;
		}
	}

	@FXML
	private void clicBoutonMoisSuivant() {
		switch (mois.getText()) {
		case "Janvier":
			this.mois.setText("Fevrier");
			break;
		case "Fevrier":
			this.mois.setText("Mars");
			break;
		case "Mars":
			this.mois.setText("Avril");
			break;
		case "Avril":
			this.mois.setText("Mai");
			break;
		case "Mai":
			this.mois.setText("Juin");
			break;
		case "Juin":
			this.mois.setText("Juillet");
			break;
		case "Juillet":
			this.mois.setText("Aout");
			break;
		case "Aout":
			this.mois.setText("Septembre");
			break;
		case "Septembre":
			this.mois.setText("Octobre");
			break;
		case "Octobre":
			this.mois.setText("Novembre");
			break;
		case "Novembre":
			this.mois.setText("Decembre");
			break;
		case "Decembre":
			this.mois.setText("Janvier");
			break;
		}
	}

}
