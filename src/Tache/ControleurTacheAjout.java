package Tache;

import java.io.File;

import javax.swing.SwingUtilities;

import BDD.SqlRequete;
import MainApp.MainApp;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ControleurTacheAjout {

	@FXML
	private Button valider;

	@FXML
	private Button annuler;

	@FXML
	private TextField description;

	@FXML
	private Button ajouter;

	@FXML
	private DatePicker dateDebut;

	@FXML
	private DatePicker dateFin;

	@FXML
	private TextField nom;

	@FXML
	private RadioButton prioHaute;

	@FXML
	private RadioButton prioNorm;

	@FXML
	private RadioButton prioBasse;

	@FXML
	private Text mois;

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

	private MainApp mainApp;

	@FXML
	private AnchorPane anchorPaneGantt;

	@FXML
	private ListView listViewTache;
	
	@FXML
	private VBox vboxListContact;
	
	@FXML
	private Label fichierSelected;

	private SqlRequete req;

	private int nbTache;
	private int nbContact;
	
	private File fichier;

	private String[] tabIdTache;
	
	private int idTriathlon;
	
	private GanttChart gantt;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		//on recupere l'id du triathlon choisit
		this.idTriathlon=this.mainApp.getIdTriathlon();
		
		//creation swingNode qui convertit les elements swing de GanttChart en FXML
		SwingNode swingNode = new SwingNode();
		this.showGantt(swingNode);
		//on ajoute le swingNode dans le AnchorPane pour l'afficher
		this.anchorPaneGantt.getChildren().add(swingNode);

		// recuperation de diverses donees
		this.req = new SqlRequete();
		nbTache = Integer.parseInt(req.getUneValeurBDD("count(id_tache)", "tache", "id_triathlon="+this.idTriathlon));
		
		this.tabIdTache = new String[nbTache];
		this.req.getTabValeurBDD("id_tache", "tache", "id_triathlon="+this.idTriathlon, tabIdTache);

		// affiche toutes les taches dans listView
		for (int i = 0; i < nbTache; i++) {
			String nomTache = req.getUneValeurBDD("nom", "tache", "id_tache=" + tabIdTache[i]);
			String dateDebut = req.getUneValeurBDD("datedebut", "tache", "id_tache="+tabIdTache[i]);
			String dateFin = req.getUneValeurBDD("datefin", "tache", "id_tache="+tabIdTache[i]);
			this.listViewTache.getItems().add(new RadioButton(nomTache));
			
			//ajoute les taches dans le gantt
			this.addTache(nomTache, dateDebut, dateFin);
		}
		
		//affiche les contacts dans vbox
		nbContact = Integer.parseInt(req.getUneValeurBDD("count(id_benevoles)", "benevoles", "id_triathlon="+this.idTriathlon));
		String[] tabNomContact = new String[nbContact];
		String[] tabPrenomContact = new String[nbContact];
		req.getTabValeurBDD("nom", "benevoles", "id_triathlon="+this.idTriathlon, tabNomContact);
		req.getTabValeurBDD("prenom", "benevoles", "id_triathlon="+this.idTriathlon, tabPrenomContact);
		
		for(int i = 0; i<nbContact; i++) {
			this.vboxListContact.getChildren().add(new CheckBox(tabNomContact[i]+" "+tabPrenomContact[i]));
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
	private void clicBoutonValider() {
		this.req = new SqlRequete();
		String desc = this.description.getText();
		String nom = this.nom.getText();
		String dateDebut = this.dateDebut.getValue().toString();
		String dateFin = this.dateFin.getValue().toString();
		String prio = null;
		if(this.prioBasse.isSelected())
			prio="Faible";
		if(this.prioNorm.isSelected())
			prio="Normale";
		if(this.prioHaute.isSelected())
			prio="Urgente";
		
		req.Connect("insert into tache(nom, description, DateDebut, DateFin, priorite, id_triathlon) values('"+nom+"','"+desc+"'"
				+",'"+dateDebut+"','"+dateFin+"','"+prio+"', "+this.idTriathlon+")");
		
		CheckBox[] tabCheckBoxContact = new CheckBox[nbContact];
		String[] tabIdContact = new String[nbContact];
		req.getTabValeurBDD("id_benevoles", "benevoles", "id_triathlon="+this.idTriathlon, tabIdContact);
		int idTache = Integer.parseInt(req.getUneValeurBDD("id_tache", "tache", "nom='"+nom+"' and id_triathlon="+this.idTriathlon));
	
		for(int i = 0; i<nbContact; i++) {
			tabCheckBoxContact[i] = (CheckBox) this.vboxListContact.getChildren().get(i);
			if(tabCheckBoxContact[i].isSelected()) {
				req.Connect("insert into attacher(id_tache, id_benevoles) values("+idTache+", "+tabIdContact[i]+")");
			}
		}
		
		if (this.fichier != null) {
			this.req.Connect("insert into fichier(nom, taille, chemin, id_triathlon) values('" + this.fichier.getName()
					+ "','" + this.fichier.length() + "', '"
					+ this.fichier.getAbsolutePath() + "', "+this.idTriathlon+");");
			int idFichier = Integer
					.parseInt(req.getUneValeurBDD("id_fichier", "fichier", "nom='" + this.fichier.getName() + "' and id_triathlon="+this.idTriathlon));
			this.req.Connect("insert into posseder(id_tache, id_fichier) values("+idTache+","+idFichier+")");
		}
		
		req.CloseConnexion();

		this.mainApp.showTacheAccueil();
	}
	
	@FXML
	private void clicBoutonAjouterDocument() {
		Stage fenetre = new Stage();
		FileChooser explorateur = new FileChooser();
		explorateur.setTitle("Explorateur");
		this.fichier = explorateur.showOpenDialog(fenetre);
		this.fichierSelected.setText(fichier.getName());
	}

	@FXML
	private void clicBoutonAnnuler() {
		mainApp.showTacheAccueil();
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
