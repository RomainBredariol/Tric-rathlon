package Tache;

import javax.swing.SwingUtilities;

import BDD.SqlRequete;
import Contact.ControleurErreur;
import MainApp.MainApp;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControleurTaches {

	private MainApp main;

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
	private AnchorPane anchorPaneGantt;
	
	@FXML
	private Button ok;
	
	@FXML
	private RadioButton urgente;
	@FXML
	private RadioButton normale;
	@FXML
	private RadioButton faible;

	@FXML
	private ListView listViewTache;

	private int nbTache;
	private SqlRequete req;
	
	private int idTriathlon;
	private int idTacheAModifier = 0;
	
	private GanttChart gantt;

	private String[] tabIdTache;

	public void setMainApp(MainApp mainApp) {
		this.main = mainApp;
		this.idTriathlon=this.main.getIdTriathlon();
		
		// affiche le gantt dans l'anchorPane central
		SwingNode swingNode = new SwingNode();
		this.showGantt(swingNode);
		this.anchorPaneGantt.getChildren().add(swingNode);
		

		// recup donees
		this.req = new SqlRequete();
		nbTache = Integer.parseInt(req.getUneValeurBDD("count(id_tache)", "tache", "id_triathlon=" + this.idTriathlon));
		this.tabIdTache = new String[nbTache];
		this.req.getTabValeurBDD("id_tache", "tache", "id_triathlon=" + this.idTriathlon, tabIdTache);

		// affiche tache dans listView
		for (int i = 0; i < nbTache; i++) {
			String nomTache = req.getUneValeurBDD("nom", "tache", "id_tache=" + tabIdTache[i]);
			String dateDebut = req.getUneValeurBDD("datedebut", "tache", "id_tache="+tabIdTache[i]);
			String dateFin = req.getUneValeurBDD("datefin", "tache", "id_tache="+tabIdTache[i]);
			this.listViewTache.getItems().add(new RadioButton(nomTache));
			this.addTache(nomTache, dateDebut, dateFin);	
		}

		this.req.CloseConnexion();
		
	}

	@FXML
	public void clicBoutonModifier() throws Exception {
		RadioButton[] tabRadioButtonTache = new RadioButton[nbTache];
		int nbRadioButtonSelected =0;
		this.req=new SqlRequete();
		for(int i = 0;i<nbTache; i++) {
			tabRadioButtonTache[i] = (RadioButton) this.listViewTache.getItems().get(i);
			if(tabRadioButtonTache[i].isSelected()) {
				nbRadioButtonSelected++;
				idTacheAModifier = Integer.parseInt(req.getUneValeurBDD("id_tache", "tache", "nom='"+tabRadioButtonTache[i].getText()+"' and id_triathlon="+this.idTriathlon));
				this.main.aConserver(idTacheAModifier);
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
			this.main.showTacheModification();
		}
		this.req.CloseConnexion();
	}
	

	private void addTache(String nom, String dateDebut, String dateFin) {
		SwingUtilities.invokeLater(() -> {
			this.gantt.addTache(nom, dateDebut, dateFin);
		});
	}

	private void showGantt(SwingNode swingNode) {
		SwingUtilities.invokeLater(() -> {
			this.gantt = new GanttChart(swingNode);
		});
	}
	
	@FXML
	private void clicBoutonOKFiltres() {
		this.req = new SqlRequete();
		if(this.urgente.isSelected()) {
			int nbTacheUrgente = Integer.parseInt(req.getUneValeurBDD("count(id_tache)", "tache", "id_triathlon="+this.idTriathlon+" and priorite='Urgente'"));
			String[] nomTacheUrgent = new String[nbTacheUrgente];
			String[] dateDebutTacheUrgent = new String[nbTacheUrgente];
			String[] dateFinTacheUrgente = new String[nbTacheUrgente];
			
			req.getTabValeurBDD("nom", "tache", "id_triathlon="+this.idTriathlon+" and priorite='Urgente'", nomTacheUrgent);
			req.getTabValeurBDD("dateDebut", "tache", "id_triathlon="+this.idTriathlon+" and priorite='Urgente'", dateDebutTacheUrgent);
			req.getTabValeurBDD("dateFin", "tache", "id_triathlon="+this.idTriathlon+" and priorite='Urgente'", dateFinTacheUrgente);
			
			SwingNode swingNode = new SwingNode();
			this.anchorPaneGantt.getChildren().remove(0);
			this.showGantt(swingNode);
			this.anchorPaneGantt.getChildren().add(swingNode);
			for(int i = 0 ; i<nbTacheUrgente; i++) {
				this.addTache(nomTacheUrgent[i], dateDebutTacheUrgent[i], dateFinTacheUrgente[i]);
			}
		}
		if(this.faible.isSelected()) {
			int nbTacheFaible = Integer.parseInt(req.getUneValeurBDD("count(id_tache)", "tache", "id_triathlon="+this.idTriathlon+" and priorite='Faible'"));
			String[] nomTache = new String[nbTacheFaible];
			String[] dateDebutTache = new String[nbTacheFaible];
			String[] dateFinTache = new String[nbTacheFaible];
			
			req.getTabValeurBDD("nom", "tache", "id_triathlon="+this.idTriathlon+" and priorite='Faible'", nomTache);
			req.getTabValeurBDD("dateDebut", "tache", "id_triathlon="+this.idTriathlon+" and priorite='Faible'", dateDebutTache);
			req.getTabValeurBDD("dateFin", "tache", "id_triathlon="+this.idTriathlon+" and priorite='Faible'", dateFinTache);
			
			SwingNode swingNode = new SwingNode();
			this.anchorPaneGantt.getChildren().remove(0);
			this.showGantt(swingNode);
			this.anchorPaneGantt.getChildren().add(swingNode);
			for(int i = 0 ; i<nbTacheFaible; i++) {
				this.addTache(nomTache[i], dateDebutTache[i], dateFinTache[i]);
			}
			
		}
		if(this.normale.isSelected()) {
			int nbTacheNormale = Integer.parseInt(req.getUneValeurBDD("count(id_tache)", "tache", "id_triathlon="+this.idTriathlon+" and priorite='Normale'"));
			String[] nomTache = new String[nbTacheNormale];
			String[] dateDebutTache = new String[nbTacheNormale];
			String[] dateFinTache = new String[nbTacheNormale];
			
			req.getTabValeurBDD("nom", "tache", "id_triathlon="+this.idTriathlon+" and priorite='Normale'", nomTache);
			req.getTabValeurBDD("dateDebut", "tache", "id_triathlon="+this.idTriathlon+" and priorite='Normale'", dateDebutTache);
			req.getTabValeurBDD("dateFin", "tache", "id_triathlon="+this.idTriathlon+" and priorite='Normale'", dateFinTache);
			
			SwingNode swingNode = new SwingNode();
			this.anchorPaneGantt.getChildren().remove(0);
			this.showGantt(swingNode);
			this.anchorPaneGantt.getChildren().add(swingNode);
			for(int i = 0 ; i<nbTacheNormale; i++) {
				this.addTache(nomTache[i], dateDebutTache[i], dateFinTache[i]);
			}		
		}
			
		this.req.CloseConnexion();
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
	public void clicBoutonSupprimer() throws Exception {
		RadioButton[] tabRadioButtonTache = new RadioButton[nbTache];
		int nbRadioButtonSelected =0;
		this.req=new SqlRequete();
		for(int i = 0;i<nbTache; i++) {
			tabRadioButtonTache[i] = (RadioButton) this.listViewTache.getItems().get(i);
			if(tabRadioButtonTache[i].isSelected()) {
				nbRadioButtonSelected++;
				idTacheAModifier = Integer.parseInt(req.getUneValeurBDD("id_tache", "tache", "nom='"+tabRadioButtonTache[i].getText()+"' and id_triathlon="+this.idTriathlon));
				this.main.aConserver(idTacheAModifier);
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
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/Tache/confirmationSuppressionTache.fxml"));
			Stage stage = new Stage();
			AnchorPane anchor = (AnchorPane) loader.load();
			ControleurSuppressionTache controleur = loader.getController();
			controleur.setMainApp(this.main);
			controleur.setfenetre(stage);
			Scene scene = new Scene(anchor);
			stage.setScene(scene);
			stage.show();
		}
	}

	@FXML
	private void clicBoutonAjouter() {
		this.main.showTacheAjout();
	}
}
