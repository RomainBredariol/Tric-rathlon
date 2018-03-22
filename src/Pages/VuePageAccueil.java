package Pages;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JTextField;

public class VuePageAccueil {
	
	private JTextField champNom;
	private JTextField champPrenom;
	private JTextField champTel;
	private JTextField champMail;
	public JButton boutonValider;
	
	public VuePageAccueil() {
		this.boutonValider.setEnabled(false);
	}

	public JTextField getNom() {
		return this.champNom;
	}

	public JTextField getPrenom() {
		return this.champPrenom;
	}

	public JTextField getTel() {
		return this.champTel;
	}

	public JTextField getMail() {
		return this.champMail;
	}

	public void activerBoutonValider() {
		this.boutonValider.setEnabled(true);	
	}

}
