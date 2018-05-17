package Agenda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import com.sun.javafx.css.converters.ColorConverter;

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
import javafx.util.converter.LocalDateStringConverter;

public class ControleurAgendaModifier {

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
	private ChoiceBox<String> horaires;

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

	private String heureId;
	private String dateId;
	
	private int idTriathlon;
	private int nbContact;

	private String[] tabIdContact;
	private String[] nomContactParticpant;
	private String[] prenomContactParticpant;

	public void setMainApp(MainApp main) {
		this.mainApp = main;
		this.req = new SqlRequete();
		this.idTriathlon=this.mainApp.getIdTriathlon();
		
		nbContact = Integer.parseInt(req.getUneValeurBDD("count(id_benevoles)", "benevoles", "id_triathlon="+this.idTriathlon));
		
		tabIdContact = new String[nbContact];
		req.getTabValeurBDD("id_benevoles", "benevoles", "id_triathlon="+this.idTriathlon, tabIdContact);

		int nbEvent = Integer.parseInt(req.getUneValeurBDD("count(nom)", "evenement", "id_triathlon="+this.idTriathlon));
		String[] tabDateEvent = new String[nbEvent];
		req.getTabValeurBDD("date", "evenement", "id_triathlon="+this.idTriathlon, tabDateEvent);
		String[] tabHeureEvent = new String[nbEvent];
		req.getTabValeurBDD("heure", "evenement", "id_triathlon="+this.idTriathlon, tabHeureEvent);

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

		// recuperation des donnees
		heureId = this.mainApp.getString2();
		dateId = this.mainApp.getString1();
		this.nom.setText(req.getUneValeurBDD("nom", "evenement", "heure='" + heureId + "' and date ='" + dateId + "' and id_triathlon="+this.idTriathlon));
		String descriptionText = req.getUneValeurBDD("description", "evenement",
				"heure='" + heureId + "' and date ='" + dateId + "' and id_triathlon="+this.idTriathlon);
		this.description.setText(descriptionText);
		this.date.setPromptText(dateId);
		String couleurString = req.getUneValeurBDD("couleur", "evenement",
				"heure='" + heureId + "' and date ='" + dateId + "' and id_triathlon="+this.idTriathlon);
		this.couleur.setValue(Color.valueOf(couleurString));
		this.horaires.setValue(heureId);

		// recupere nom et prenom de benevoles participant a un event
		nomContactParticpant = new String[1000];
		req.getTabValeurBDD("benevoles.nom", "benevoles, participer, evenement",
				"benevoles.ID_Benevoles=participer.ID_Benevoles and "
						+ "participer.Date=evenement.Date and participer.heure=evenement.heure and evenement.heure = '"
						+ heureId + "' and evenement.Date='" + dateId + "' and evenement.id_triathlon="+this.idTriathlon+" order by benevoles.ID_Benevoles",
				nomContactParticpant);
		prenomContactParticpant = new String[1000];
		req.getTabValeurBDD("benevoles.prenom", "benevoles, participer, evenement",
				"benevoles.ID_Benevoles=participer.ID_Benevoles and "
						+ "participer.Date=evenement.Date and participer.heure=evenement.heure and evenement.heure = '"
						+ heureId + "' and evenement.Date='" + dateId + "' and evenement.id_triathlon="+this.idTriathlon+" order by benevoles.ID_Benevoles",
				prenomContactParticpant);

		// selection des contacts participant dans la liste
		int j = 0;
		for (int i = 0; i < nbContact; i++) {
			CheckBox[] tabContact = new CheckBox[nbContact];
			tabContact[i] = (CheckBox) this.vboxListeContact.getChildren().get(i);
			if (tabContact[i].getText().equals(nomContactParticpant[j] + " " + prenomContactParticpant[j])) {
				tabContact[i].setSelected(true);
				j++;
			}
		}

	}

	@FXML
	private void clicBoutonValider() {
		this.req = new SqlRequete();
		String datePicker = null;
		if (this.date.getValue() != null) {
			datePicker = this.date.getValue().toString();
		} else {
			datePicker = this.date.getPromptText();
		}

		Color couleur = this.couleur.getValue();
		String desc = this.description.getText();
		String nom = this.nom.getText();
		String horraire = this.horaires.getValue();
		this.heureId = this.heureId + ":00";

		req.Connect("update evenement set nom='" + nom + "', description='" + desc + "', date='" + datePicker.toString()
				+ "', couleur='" + couleur.toString() + "', heure='" + horraire + "' where heure='" + this.heureId
				+ "' and date='" + this.dateId + "'");

		//contact ajouter ou supprimer
		int j = 0;
		for (int i = 0; i < nbContact; i++) {
			CheckBox[] tabContact = new CheckBox[nbContact];
			tabContact[i] = (CheckBox) this.vboxListeContact.getChildren().get(i);

			if (tabContact[i].isSelected() && tabContact[i].getText().equals(this.nomContactParticpant[j] + " " + this.prenomContactParticpant[j])) {
				j++;
			}else {
				if (tabContact[i].isSelected() == false && tabContact[i].getText().equals(this.nomContactParticpant[j] + " " + this.prenomContactParticpant[j])) {
					req.Connect("delete from participer where id_benevoles=" + tabIdContact[i] + " and date ='" + dateId
						+ "' and heure='" + heureId + "'");
					j++;
				}else {
					if (tabContact[i].isSelected() && !tabContact[i].getText().equals(this.nomContactParticpant[j] + " " + this.prenomContactParticpant[j])) {
						req.Connect("insert into participer(id_benevoles, date, heure) values(" + tabIdContact[i] + ", '"
						+ dateId + "', '" + heureId + "')");
						j++;
					}
				}
			}
			
		}

		req.CloseConnexion();
		mainApp.showAgendaAccueil();
	}

	@FXML
	private void clicBoutonAnnuler() {
		this.date = null;
		this.nom = null;
		this.horaires = null;
		this.description = null;
		this.couleur = null;
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
