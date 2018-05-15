package Agenda;

import java.time.LocalDate;
import java.util.ArrayList;

import Accueil.MainApp;
import BDD.SqlRequete;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ControleurAgendaAjout implements ChangeListener {

	@Override
	public void changed(ObservableValue arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	// Un peu sale, mais à part utiliser java.util.Calendar, qui a l'air imbuvable,
	// ça reste le meilleur moyen
	ObservableList<String> hours = FXCollections.observableArrayList("00:00", "00:15", "00:30", "00:45", "01:00",
			"01:15", "01:30", "01:45", "02:00", "03:15", "04:30", "04:45", "05:00", "05:15", "05:30", "05:45", "06:00",
			"06:15", "06:30", "06:45", "07:00", "07:15", "07:30", "07:45", "08:00", "08:15", "08:30", "08:45", "09:00",
			"09:15", "09:30", "09:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45", "12:00",
			"12:15", "12:30", "12:45", "13:00", "13:15", "13:30", "13:45", "14:00", "14:15", "14:30", "14:45", "15:00",
			"15:15", "15:30", "15:45", "16:00", "16:15", "16:30", "16:45", "17:00", "17:15", "17:30", "17:45", "18:00",
			"18:15", "18:30", "18:45", "19:00", "19:15", "19:30", "19:45", "20:00", "20:15", "20:30", "20:45", "21:00",
			"21:15", "21:30", "21:45", "22:00", "22:15", "22:30", "22:45", "23:00", "23:15", "23:30", "23:45");

	@FXML
	private Button valider;

	@FXML
	private Button annuler;

	@FXML
	private TextField description;

	@FXML
	private ColorPicker couleur;

	@FXML
	private ChoiceBox<String> horaires ;

	@FXML
	private DatePicker date;

	@FXML
	private TextField nom;

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
	private VBox vboxListeContact;

	@FXML
	private ListView listViewEvent;

	private MainApp mainApp;

	private SqlRequete req;
	
	private String[] tabIdContact;

	public void setMainApp(MainApp main) {
		this.mainApp = main;
		
		 
	}

	private int nbContact;
	
	@FXML
	private void initialize() {
		this.req = new SqlRequete();
		nbContact = Integer.parseInt(req.getUneValeurBDD("count(id_benevoles)", "benevoles", ""));

		tabIdContact = new String[nbContact];
		req.getTabValeurBDD("id_benevoles", "benevoles", "", tabIdContact);

		int nbEvent = Integer.parseInt(req.getUneValeurBDD("count(nom)", "evenement", ""));
		String[] tabDateEvent = new String[nbEvent];
		req.getTabValeurBDD("date", "evenement", "", tabDateEvent);
		String[] tabHeureEvent = new String[nbEvent];
		req.getTabValeurBDD("heure",  "evenement", "", tabHeureEvent);

		// ajout des checkBox pour chaque contact
		for (int i = 0; i < nbContact; i++) {
			String nomContact = req.getUneValeurBDD("nom", "benevoles", "id_benevoles=" + tabIdContact[i]);
			String prenomContact = req.getUneValeurBDD("prenom", "benevoles", "id_benevoles=" + tabIdContact[i]);
			this.vboxListeContact.getChildren().add(new CheckBox(nomContact + " " + prenomContact));
		}
		// ajout des RadioButton pour chaque event
		for (int i = 0; i < nbEvent; i++) {
			String nomEvent = req.getUneValeurBDD("nom", "evenement",
					"date='" + tabDateEvent[i] + "' and heure='" + tabHeureEvent[i] + "'");
			this.listViewEvent.getItems().add(new RadioButton(nomEvent));
			this.listViewEvent.getItems().add(new Label(tabHeureEvent[i] + " || " + tabDateEvent[i]));
		}
		
		this.horaires.getItems().addAll(hours);
	}

	@FXML
	private void clicBoutonValider() {
		LocalDate datePicker = null;
		SqlRequete event = new SqlRequete();
		
		datePicker = this.date.getValue();
		Color couleur = this.couleur.getValue();
		String desc = this.description.getText();
		String nom = this.nom.getText();
		String heure = this.horaires.getValue();
		
		for(int i = 0; i<nbContact; i++) {
			CheckBox[] tabContact = new CheckBox[nbContact];
			tabContact[i] = (CheckBox) this.vboxListeContact.getChildren().get(i);
			if(tabContact[i].isSelected()) {
				event.Connect("insert into participer(id_benevoles, date, heure) values("+this.tabIdContact[i]+", '"+datePicker.toString()+"', '"+heure+"')");
			}
		}
		//Il faudra ajouter l'id_triathlon en creant une methodes dans mainapp
		String requete = "insert into evenement(nom, description, date, couleur, heure)"
				+ " values('"+nom+"','"+desc+"','"+datePicker.toString()+"','"+couleur.toString()+"','"+heure+"');";		
		event.Connect(requete);
		event.CloseConnexion();
		mainApp.showAgendaAccueil();
	}

	@FXML
	private void clicBoutonAnnuler() {
		mainApp.showAgendaAccueil();
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
}
