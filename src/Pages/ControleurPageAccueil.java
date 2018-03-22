package Pages;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

public class ControleurPageAccueil {

	private VuePageAccueil vue;
	
	public ControleurPageAccueil (VuePageAccueil vue) {
		this.vue=vue;
	}
	
	public void actionPerformed(ActionEvent action) {
		JButton bouton = (JButton) action.getSource();
		
		String nom = this.vue.getNom().getText();
		String prenom = this.vue.getPrenom().getText();
		String tel = this.vue.getTel().getText();
		String mail = this.vue.getMail().getText();
	
		if(nom != null && prenom != null && tel != null && mail != null) {
			this.vue.activerBoutonValider();
			if(bouton == this.vue.boutonValider) {
				//envoyer les requêtes à la BDD
			}
			
		}
	}
}
