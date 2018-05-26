package AccueilGeneral;

import BDD.SqlRequete;
import MainApp.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControleurSuppressionTriathlon {

	private Stage fenetre;
	private MainApp main;
	private int idTriathlon;
	
	@FXML
	private Button annuler;
	
	@FXML
	private Button confirmer;

	public void setfenetre(Stage stage) {
		this.fenetre=stage;
	}

	public void setMainApp(MainApp main) {
		this.main=main;
		this.idTriathlon=this.main.getIdTriathlon();
	}
	
	@FXML
	private void clicBoutonAnnuler() {
		this.fenetre.close();
	}
	
	@FXML
	private void clicBoutonConfirmer() {
		SqlRequete req = new SqlRequete();
		
		int nbBenevoles=Integer.parseInt(req.getUneValeurBDD("count(id_benevoles)", "benevoles", "id_triathlon="+this.idTriathlon));
		int nbTache=Integer.parseInt(req.getUneValeurBDD("count(id_tache)", "tache", "id_triathlon="+this.idTriathlon));
		
		String[] tabIdBenevole = new String[nbBenevoles];
		String[] tabIdTache = new String[nbTache];
		req.getTabValeurBDD("id_benevoles", "benevoles", "id_triathlon="+this.idTriathlon, tabIdBenevole);
		req.getTabValeurBDD("id_tache", "tache", "id_triathlon="+this.idTriathlon, tabIdTache);
		
		for(int i = 0; i<Math.max(nbTache, nbBenevoles); i++) {
			req.Connect("delete from affilier where id_benevoles="+tabIdBenevole[i]);
			req.Connect("delete from attacher where id_benevoles="+tabIdBenevole[i]);
			req.Connect("delete from lier where id_benevoles="+tabIdBenevole[i]);
			req.Connect("delete from participer where id_benevoles"+tabIdBenevole[i]);
			req.Connect("delete from posseder where id_tache="+tabIdTache[i]);
		}
		
		req.Connect("delete from benevoles where id_triathlon="+this.idTriathlon);	
		req.Connect("delete from evenement where id_triathlon="+this.idTriathlon);
		req.Connect("delete from fichier where id_triathlon="+this.idTriathlon);
		req.Connect("delete from groupe where id_triathlon="+this.idTriathlon);
		req.Connect("delete from tache where id_triathlon="+this.idTriathlon);
		req.Connect("delete from triathlon where id_triathlon="+this.idTriathlon);
		this.fenetre.close();
		this.main.showAccueilGeneral();
	}
	
	

}
