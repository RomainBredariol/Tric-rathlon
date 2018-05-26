package Agenda;

import java.time.LocalDate;
import java.time.YearMonth;

import BDD.SqlRequete;
import MainApp.MainApp;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ControleurAgendaAjout  {

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
	
	private int idTriathlon;

	private YearMonth currentYearMonth;

	private FullCalendarView calendar;
	
	private int nbEvent;
	private String[] tabDateEvent;
	private String[] tabHeureEvent;
	
	@FXML
	private AnchorPane anchorPaneCalendar;

	public void setMainApp(MainApp main) {
		this.mainApp = main;
		this.idTriathlon = this.mainApp.getIdTriathlon();
		
		currentYearMonth = YearMonth.now();
		this.calendar = new FullCalendarView(currentYearMonth);
		Button prev = calendar.getButtonPrev();
		Button next = calendar.getButtonNext();
		prev.setOnAction(e -> clicBoutonPrev());
		next.setOnAction(e -> clicBoutonNext());
		
		this.req = new SqlRequete();
		nbContact = Integer.parseInt(req.getUneValeurBDD("count(id_benevoles)", "benevoles", "id_triathlon="+this.idTriathlon));

		tabIdContact = new String[nbContact];
		req.getTabValeurBDD("id_benevoles", "benevoles", "id_triathlon="+this.idTriathlon, tabIdContact);

		nbEvent = Integer.parseInt(req.getUneValeurBDD("count(nom)", "evenement", "id_triathlon="+this.idTriathlon));
		this.tabDateEvent = new String[nbEvent];
		req.getTabValeurBDD("date", "evenement", "id_triathlon="+this.idTriathlon, tabDateEvent);
		this.tabHeureEvent = new String[nbEvent];
		req.getTabValeurBDD("heure",  "evenement", "id_triathlon="+this.idTriathlon, tabHeureEvent);

		// ajout des checkBox pour chaque contact
		for (int i = 0; i < nbContact; i++) {
			String nomContact = req.getUneValeurBDD("nom", "benevoles", "id_benevoles=" + tabIdContact[i]);
			String prenomContact = req.getUneValeurBDD("prenom", "benevoles", "id_benevoles=" + tabIdContact[i]);
			this.vboxListeContact.getChildren().add(new CheckBox(nomContact + " " + prenomContact));
		}
		// ajout des RadioButton pour chaque event
		for (int i = 0; i < nbEvent; i++) {
			String nomEvent = req.getUneValeurBDD("nom", "evenement",
					"date='" + tabDateEvent[i] + "' and heure='" + tabHeureEvent[i] + "' and id_triathlon="+this.idTriathlon);
			this.listViewEvent.getItems().add(new RadioButton(nomEvent));
			this.listViewEvent.getItems().add(new Label(tabHeureEvent[i] + " || " + tabDateEvent[i]));
			String couleurEvent = req.getUneValeurBDD("couleur", "evenement", "date='" + tabDateEvent[i] + 
					"' and heure='" + tabHeureEvent[i] + "' and id_triathlon="+this.idTriathlon);
			this.calendar.addEvent(nomEvent, tabDateEvent[i], couleurEvent);
		}
		this.anchorPaneCalendar.getChildren().add(this.calendar.getView());
		this.horaires.getItems().addAll(hours);
		this.req.CloseConnexion();
	}
	
	@FXML
	private void clicBoutonNext() {
		this.req = new SqlRequete();
		this.calendar.nextMonth();
		for(int i = 0; i < nbEvent; i++) {
			String nomEvent = req.getUneValeurBDD("nom", "evenement", "date='" + tabDateEvent[i] + 
					"' and heure='" + tabHeureEvent[i] + "' and id_triathlon="+this.idTriathlon);
			String couleurEvent = req.getUneValeurBDD("couleur", "evenement", "date='" + tabDateEvent[i] + 
					"' and heure='" + tabHeureEvent[i] + "' and id_triathlon="+this.idTriathlon);
			this.calendar.addEvent(nomEvent, tabDateEvent[i], couleurEvent);
		}
		this.anchorPaneCalendar.getChildren().setAll(this.calendar.getView());
		this.req.CloseConnexion();
	}
	
	@FXML
	private void clicBoutonPrev() {
		this.req = new SqlRequete();
		this.calendar.previousMonth();
		for(int i = 0; i < nbEvent; i++) {
			String nomEvent = req.getUneValeurBDD("nom", "evenement", "date='" + tabDateEvent[i] + 
					"' and heure='" + tabHeureEvent[i] + "' and id_triathlon="+this.idTriathlon);
			String couleurEvent = req.getUneValeurBDD("couleur", "evenement", "date='" + tabDateEvent[i] + 
					"' and heure='" + tabHeureEvent[i] + "' and id_triathlon="+this.idTriathlon);
			this.calendar.addEvent(nomEvent, tabDateEvent[i], couleurEvent);
		}
		this.anchorPaneCalendar.getChildren().setAll(this.calendar.getView());
		this.req.CloseConnexion();
	}

	private int nbContact;

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
		
		String requete = "insert into evenement(nom, description, date, couleur, heure, id_triathlon)"
				+ " values('"+nom+"','"+desc+"','"+datePicker.toString()+"','"+couleur.toString()+"','"+heure+"', "+this.idTriathlon+");";		
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
