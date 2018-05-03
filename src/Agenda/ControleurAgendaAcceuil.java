package Agenda;

import Accueil.MainApp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ControleurAgendaAcceuil implements ChangeListener{

	private MainApp main;
	
	public void setMainApp(MainApp mainApp) {
		// TODO Auto-generated method stub
		this.main=mainApp;
	}

	@Override
	public void changed(ObservableValue arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
		
	}

}
