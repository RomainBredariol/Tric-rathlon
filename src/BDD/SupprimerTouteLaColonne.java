package BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SupprimerTouteLaColonne {

	public SupprimerTouteLaColonne() {
		/* Connexion � la base de donn�es */
		String url = "jdbc:mysql://localhost/test";
		String utilisateur = "root";
		String motDePasse = "azerty";
		Connection connexion = null;
		;
		Statement st = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
			st = connexion.createStatement();
			String requete = "DELETE FROM `user`;";

			st.executeUpdate(requete);

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}
		try {
			connexion.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
