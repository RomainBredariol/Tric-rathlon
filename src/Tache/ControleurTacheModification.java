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

public class ControleurTacheModification {

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
	private int idTacheAModifier;

	private String[] nomContactAffecter;
	private String[] prenomContactAffecter;
	private String[] tabIdContact;
	
	private GanttChart gantt;
	

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		this.idTriathlon=this.mainApp.getIdTriathlon();
		this.idTacheAModifier=this.mainApp.getValeurAConserver();
		
		SwingNode swingNode = new SwingNode();
		this.showGantt(swingNode);
		this.anchorPaneGantt.getChildren().add(swingNode);

		// recup donees
		this.req = new SqlRequete();
		nbTache = Integer.parseInt(req.getUneValeurBDD("count(id_tache)", "tache", "id_triathlon="+this.idTriathlon));
		this.tabIdTache = new String[nbTache];
		this.req.getTabValeurBDD("id_tache", "tache", "id_triathlon="+this.idTriathlon, tabIdTache);

		// affiche tache dans listView
		for (int i = 0; i < nbTache; i++) {
			String nomTache = req.getUneValeurBDD("nom", "tache", "id_tache=" + tabIdTache[i]);
			String dateDebut = req.getUneValeurBDD("datedebut", "tache", "id_tache="+tabIdTache[i]);
			String dateFin = req.getUneValeurBDD("datefin", "tache", "id_tache="+tabIdTache[i]);
			this.listViewTache.getItems().add(new RadioButton(nomTache));
			this.addTache(nomTache, dateDebut, dateFin);
		}
		
		//affiche les contacts dans vbox
		nbContact = Integer.parseInt(req.getUneValeurBDD("count(id_benevoles)", "benevoles", "id_triathlon="+this.idTriathlon));
		this.tabIdContact = new String[nbContact];
		String[] tabNomContact = new String[nbContact];
		String[] tabPrenomContact = new String[nbContact];
		req.getTabValeurBDD("id_benevoles", "benevoles", "id_triathlon="+this.idTriathlon, tabIdContact);
		req.getTabValeurBDD("nom", "benevoles", "id_triathlon="+this.idTriathlon, tabNomContact);
		req.getTabValeurBDD("prenom", "benevoles", "id_triathlon="+this.idTriathlon, tabPrenomContact);
		
		for(int i = 0; i<nbContact; i++) {
			this.vboxListContact.getChildren().add(new CheckBox(tabNomContact[i]+" "+tabPrenomContact[i]));
		}
		
		//affiche les donnees de la tache a modifier
		this.nom.setText(req.getUneValeurBDD("nom", "tache", "id_tache="+this.idTacheAModifier));
		this.description.setText(req.getUneValeurBDD("description", "tache", "id_tache="+idTacheAModifier));
		this.dateDebut.setPromptText(req.getUneValeurBDD("DateDebut", "tache", "id_tache="+idTacheAModifier));
		this.dateFin.setPromptText(req.getUneValeurBDD("DateFin", "tache", "id_tache="+idTacheAModifier));
		String requeteFichier = req.getUneValeurBDD("fichier.nom", "fichier, posseder, tache", "fichier.id_fichier=posseder.id_fichier and"
				+ " posseder.id_tache=tache.id_tache and tache.id_tache="+idTacheAModifier);
		this.fichierSelected.setText(requeteFichier);
		
		
		this.nomContactAffecter = new String[1000];
		this.req.getTabValeurBDD("benevoles.nom", "benevoles, attacher, tache",
				"benevoles.ID_Benevoles=attacher.ID_Benevoles and "
						+ "attacher.id_tache=tache.id_tache and tache.id_tache="+idTacheAModifier+" order by benevoles.ID_Benevoles",
				nomContactAffecter);
		this.prenomContactAffecter = new String[1000];
		this.req.getTabValeurBDD("benevoles.prenom", "benevoles, attacher, tache",
				"benevoles.ID_Benevoles=attacher.ID_Benevoles and "
						+ "attacher.id_tache=tache.id_tache and tache.id_tache="+idTacheAModifier+" order by benevoles.ID_Benevoles",
				prenomContactAffecter);

		// selection des contacts participant dans la liste
		int j = 0;
		for (int i = 0; i < nbContact; i++) {
			CheckBox[] tabContact = new CheckBox[nbContact];
			tabContact[i] = (CheckBox) this.vboxListContact.getChildren().get(i);
			if (tabContact[i].getText().equals(nomContactAffecter[j] + " " + prenomContactAffecter[j])) {
				tabContact[i].setSelected(true);
				j++;
			}
		}
		
		String prioriteTache = req.getUneValeurBDD("priorite", "tache", "id_tache="+idTacheAModifier);
		switch (prioriteTache) {
		case "Urgente":
			this.prioHaute.setSelected(true);
			break;
		case "Normale":
			this.prioNorm.setSelected(true);
			break;
		case "Faible":
			this.prioBasse.setSelected(true);
			break;
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
		String dateDebutModif = null;
		if (this.dateDebut.getValue() != null) {
			dateDebutModif = this.dateDebut.getValue().toString();
		} else {
			dateDebutModif = this.dateDebut.getPromptText();
		}
		
		String dateFinModif = null;
		if (this.dateFin.getValue() != null) {
			dateFinModif = this.dateFin.getValue().toString();
		} else {
			dateFinModif = this.dateFin.getPromptText();
		}
		
		String prio = null;
		if(this.prioBasse.isSelected())
			prio="Faible";
		if(this.prioNorm.isSelected())
			prio="Normale";
		if(this.prioHaute.isSelected())
			prio="Urgente";
		
		
		req.Connect("update tache set nom='"+nom+"', description='"+desc+"', DateDebut='"+dateDebutModif+"', DateFin='"+dateFinModif+"', priorite='"+prio+"'"
				+ " where id_tache="+this.idTacheAModifier);
		
		//ajoute ou supprime contact affecter a une tache
		int j = 0;
		for (int i = 0; i < nbContact; i++) {
			CheckBox[] tabContact = new CheckBox[nbContact];
			tabContact[i] = (CheckBox) this.vboxListContact.getChildren().get(i);

			if (tabContact[i].isSelected() && tabContact[i].getText().equals(this.nomContactAffecter[j] + " " + this.prenomContactAffecter[j])) {
				j++;
			}else {
				if (tabContact[i].isSelected() == false && tabContact[i].getText().equals(this.nomContactAffecter[j] + " " + this.prenomContactAffecter[j])) {
					req.Connect("delete from attacher where id_tache="+idTacheAModifier+" and id_benevoles="+tabIdContact[i]);
					j++;
				}else {
					if (tabContact[i].isSelected() && !tabContact[i].getText().equals(this.nomContactAffecter[j] + " " + this.prenomContactAffecter[j])) {
						req.Connect("insert into attacher(id_tache, id_benevoles) values("+idTacheAModifier+", "+tabIdContact[i]+")");
						j++;
					}
				}
			}
			
		}
		
		//ajoute un fichier lier a la tache
		if (this.fichier != null) {
			this.req.Connect("insert into fichier(nom, descriptif, taille, chemin, id_triathlon) values('" + this.fichier.getName()
					+ "', 'affecter a la tache " + this.nom.getText() + "','" + this.fichier.length() + "', '"
					+ this.fichier.getAbsolutePath() + "', "+this.idTriathlon+");");
			int idFichier = Integer
					.parseInt(req.getUneValeurBDD("id_fichier", "fichier", "nom='" + this.fichier.getName() + "' and id_triathlon="+this.idTriathlon));
			this.req.Connect("insert into posseder(id_tache, id_fichier) values("+idTacheAModifier+","+idFichier+")");
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
