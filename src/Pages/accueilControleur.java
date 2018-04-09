package Pages;

import BDD.SqlRequete;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class accueilControleur implements ChangeListener{
	
	@FXML
	private TextField nom;
	
	@FXML
	private void setNomTextField() {
		this.Nom = nom.getText();
	}
	
	@FXML
	private TextField prenom;
	
	@FXML
	private void setPrenomTextField() {
		this.Prenom = prenom.getText();
	}
	
	@FXML
	private TextField tel;
	
	@FXML
	private void setTelTextField() {
		this.Tel = tel.getText();
	}
	
	@FXML
	private TextField mail;
	
	@FXML
	private void setMailTextField() {
		this.Mail = mail.getText();
	}
	
	@FXML
	private Button boutonEnregistrer;
	
	private MainApp mainApp;
	private String Nom ;
	private String Prenom;
	private String Tel;
	private String Mail;
	private modeleAccueil modele;
	
	public void setModele() {
		this.modele= new modeleAccueil(this.Nom, this.Prenom, this.Tel, this.Mail);
	}
	

	@FXML
	private void initialize() {
	}
	
	
	public void setMainApp(MainApp main) {
		this.mainApp=main;	
	}
	
	
	@FXML
	private boolean champRemplis() {
		if (this.Nom.length()!=0 && this.Prenom.length() != 0 && this.Tel.length() != 0 && this.Mail.length() != 0){
			return true;
		}else {
			return false;
		}
	}
	
	@FXML
	private void clicBoutonEnregistrer() {
		//if(this.champRemplis()==true) {
			SqlRequete sql = new SqlRequete();
			sql.Connect("Insert into name(nom, prenom, tel, mail, comm) values('"+this.Nom+"', '"+this.Prenom+"', '"+this.Tel
					+"', '"+this.Mail+"', '');");
			sql.Connect("select * from name;");
//		}else {
//			System.out.println("tout les champs ne sont pas remplis");
//		}
	}


	@Override
	public void changed(ObservableValue arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
		
	}
	
	
//	  
}
//
