package AccueilGeneral;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;

import Accueil.MainApp;
import BDD.SqlRequete;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class ControleurNouveauTriathlon implements ChangeListener {

	private MainApp main;

	public void setMainApp(MainApp mainApp) {
		this.main = mainApp;
	}

	@FXML
	public void initialize() {

	}

	@FXML
	private Button valider;

	// Genre
	@FXML
	private RadioButton amical;
	@FXML
	private RadioButton competition;
	@FXML
	private RadioButton championnat;

	// Type
	@FXML
	private RadioButton xs;
	@FXML
	private RadioButton s;
	@FXML
	private RadioButton m;
	@FXML
	private RadioButton l;
	@FXML
	private RadioButton half;
	@FXML
	private RadioButton xl;
	@FXML
	private RadioButton xxl;

	// Taches
	@FXML
	private CheckBox choix;
	@FXML
	private CheckBox validations;
	@FXML
	private CheckBox autorisations;
	@FXML
	private CheckBox premiersContacts;
	@FXML
	private CheckBox commandesFermes;
	@FXML
	private CheckBox infrastructure;
	@FXML
	private CheckBox rH;
	@FXML
	private CheckBox affectations;
	@FXML
	private CheckBox verifications;
	@FXML
	private CheckBox pub;
	@FXML
	private CheckBox verificationAchatMarchandises;
	@FXML
	private CheckBox majSite;
	@FXML
	private CheckBox ravitaillment;
	@FXML
	private CheckBox relances;
	@FXML
	private CheckBox installationZone;
	@FXML
	private CheckBox gestionVeilleEpreuve;
	@FXML
	private CheckBox Accueil;

	// Renseignement
	@FXML
	private TextField nom;
	@FXML
	private DatePicker dateDebut;
	@FXML
	private DatePicker dateFin;
	@FXML
	private TextField ville;

	@FXML
	private void clicBoutonValider() {
	//REQUETE CONCERNANT LA TABLE TRIATHLON
		// creation requete
		SqlRequete req = new SqlRequete();
		LocalDate date = null;

		// créer un element triathlon dans la bdd
		if (this.nom.getText() != null) {
			// charge la date selectionné
			date = this.dateDebut.getValue();
			// charge le nom
			String nom = this.nom.getText();
			// charge la ville
			String lieu = this.ville.getText();

			// championnat
			int championnat = 0;
			if (this.championnat.isSelected()) {
				// =1 si champ_nat est selctioné sinon =0
				championnat = 1;
			}

			// format xs selectionné
			if (this.xs.isSelected()) {
				String format = "xs";
				// requete
				req.Connect("insert into triathlon(nom, date, format, lieu, champ_nat)" + "values('" + nom + "', '"
						+ date.toString() + "', '" + format + "', '" + lieu + "', " + championnat + ");");
			}
			if (this.s.isSelected()) {
				String format = "s";
				req.Connect("insert into triathlon(nom, date, format, lieu, champ_nat)" + "values('" + nom + "', '"
						+ date.toString() + "', '" + format + "', '" + lieu + "', " + championnat + ");");
			}
			if (this.m.isSelected()) {
				String format = "m";
				req.Connect("insert into triathlon(nom, date, format, lieu, champ_nat)" + "values('" + nom + "', '"
						+ date.toString() + "', '" + format + "', '" + lieu + "', " + championnat + ");");
			}
			if (this.l.isSelected()) {
				String format = "l";
				req.Connect("insert into triathlon(nom, date, format, lieu, champ_nat)" + "values('" + nom + "', '"
						+ date.toString() + "', '" + format + "', '" + lieu + "', " + championnat + ");");
			}
			if (this.half.isSelected()) {
				String format = "half";
				req.Connect("insert into triathlon(nom, date, format, lieu, champ_nat)" + "values('" + nom + "', '"
						+ date.toString() + "', '" + format + "', '" + lieu + "', " + championnat + ");");
			}
			if (this.xl.isSelected()) {
				String format = "xl";
				req.Connect("insert into triathlon(nom, date, format, lieu, champ_nat)" + "values('" + nom + "', '"
						+ date.toString() + "', '" + format + "', '" + lieu + "', " + championnat + ");");
			}
			if (this.xxl.isSelected()) {
				String format = "xxl";
				req.Connect("insert into triathlon(nom, date, format, lieu, champ_nat)" + "values('" + nom + "', '"
						+ date.toString() + "', '" + format + "', '" + lieu + "', " + championnat + ");");
			}

		}
		
		
	//REQUETE CONCERNANT LA TABLE TACHE
		
		if(this.choix.isSelected()) {
			req.Connect("insert into tache(nom) values('Choix(date, lieu...)');");
		}
		if(this.validations.isSelected()) {
			req.Connect("insert into tache(nom) values('Validations');");
		}
		if(this.autorisations.isSelected()) {
			req.Connect("insert into tache(nom) values('Autorisations');");
		}
		if(this.premiersContacts.isSelected()) {
			req.Connect("insert into tache(nom) values('Premiers Contact');");
		}
		if(this.commandesFermes.isSelected()) {
			req.Connect("insert into tache(nom) values('Commandes');");
		}
		if(this.infrastructure.isSelected()) {
			req.Connect("insert into tache(nom) values('Infrastructure');");
		}
		if(this.rH.isSelected()) {
			req.Connect("insert into tache(nom) values('Ressources Humaines');");
		}
		if(this.affectations.isSelected()) {
			req.Connect("insert into tache(nom) values('Affectations');");
		}
		if(this.verifications.isSelected()) {
			req.Connect("insert into tache(nom) values('Verifications');");
		}
		if(this.pub.isSelected()) {
			req.Connect("insert into tache(nom) values('Publicite');");
		}
		if(this.verificationAchatMarchandises.isSelected()) {
			req.Connect("insert into tache(nom) values('Verification Achat Marchandises');");
		}
		if(this.majSite.isSelected()) {
			req.Connect("insert into tache(nom) values('Mise a jour du Site');");
		}
		if(this.ravitaillment.isSelected()) {
			req.Connect("insert into tache(nom) values('Ravitaillement');");
		}
		if(this.relances.isSelected()) {
			req.Connect("insert into tache(nom) values('Relances');");
		}
		if(this.installationZone.isSelected()) {
			req.Connect("insert into tache(nom) values('Installation de la Zone');");
		}
		if(this.gestionVeilleEpreuve.isSelected()) {
			req.Connect("insert into tache(nom) values('Gestion veille Epreuve');");
		}
		if(this.Accueil.isSelected()) {
			req.Connect("insert into tache(nom) values('Accueil');");
		}
		
		
		req.CloseConnexion();
	}

	@Override
	public void changed(ObservableValue arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

}
