package Agenda;

import java.time.YearMonth;

import BDD.SqlRequete;
import Contact.ControleurErreur;
import MainApp.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
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
	private Text mois;

	@FXML
	private ListView listViewEvent;

	@FXML
	private AnchorPane anchorPaneCalendar;

	private int nbEvent;
	private String[] tabDateEvent;
	private String[] tabHeureEvent;
	
	private int idTriathlon;
	
	
	private FullCalendarView calendar;
	private YearMonth currentYearMonth;

	public void setMainApp(MainApp mainApp) {
		this.main = mainApp;
		currentYearMonth = YearMonth.now();
		this.calendar = new FullCalendarView(currentYearMonth);
		Button prev = calendar.getButtonPrev();
		Button next = calendar.getButtonNext();
		prev.setOnAction(e -> clicBoutonPrev());
		next.setOnAction(e -> clicBoutonNext());
	
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
			String couleurEvent = req.getUneValeurBDD("couleur", "evenement", "date='" + tabDateEvent[i] + 
					"' and heure='" + tabHeureEvent[i] + "' and id_triathlon="+this.idTriathlon);
			this.listViewEvent.getItems().add(new RadioButton(nomEvent));
			this.listViewEvent.getItems().add(new Label(tabHeureEvent[i] + " || " + tabDateEvent[i]));
			this.calendar.addEvent(nomEvent, tabDateEvent[i], couleurEvent);
		}
		this.anchorPaneCalendar.getChildren().add(calendar.getView());
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

}
