package BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SqlRequete {

	private Connection connexion;

	public SqlRequete() {
		// Comme en php, ici c'est l'url de notre bdd avec login et mdp
		String url = "jdbc:mysql://localhost/tricerathlon";
		String utilisateur = "root";
		String motDePasse = "azerty";
		this.connexion = null;
		try {
			// ici on charge les drivers de jdbc
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Driver ok !");
		try {
			// ici on se connecte
			connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Connexion Effective !");
	}

	// methode qui envoie une requetre � la bdd CELLE CI N'AFFICHE QUE DANS LA
	// CONSOLE (cf. methode suivante)
	public void Connect(String requete) {
		/* Connexion � la base de donn�es */
		// le resultset est le r�sultat que va nous renvoyer la bdd
		ResultSet rs = null;
		// le statement est, en gros, un element qui va transmettre la requete
		Statement st = null;
		try {

			st = connexion.createStatement();

			String rq = requete;

			// Requete de lecture
			if (rq.startsWith("select") || rq.startsWith("Select") || rq.startsWith("SELECT")) {
				// executeQuery pour de la lecture et executeUpdate pour une modif (cf. else
				// suivant)
				rs = st.executeQuery(rq);
				// On r�cup�re les MetaData (= Nom de colonne, nom table etc)
				ResultSetMetaData resultMeta = rs.getMetaData();

				System.out.println("\n****************************************************************************");
				// On affiche le nom des colonnes
				for (int i = 1; i <= resultMeta.getColumnCount(); i++)
					System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");

				System.out.println("\n****************************************************************************");

				// On affiche les donn�es d'une table
				while (rs.next()) {
					for (int i = 1; i <= resultMeta.getColumnCount(); i++)
						System.out.print("\t" + rs.getObject(i).toString() + "\t |");

					System.out.println("\n------------------------------------------------------------------------");
				}
			} else {
				// Requete de modification ou d'�criture (donc avec executeUpdate)
				st.executeUpdate(rq);
				System.out.println("Requete admise");
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
		try {

			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// cette m�thode permet de demander une donn�e en particulier (ex: select
	// nom from user where id=1)
	// De plus celle ci n'affiche pas dans la console mais dans un objet genre un
	// champ de texte d'une page (ex: page profil)
	public String getUneValeurBDD(String colonne, String table, String condition) {
		/* Connexion � la base de donn�es */

		ResultSet rs = null;
		Statement st = null;
		String valeur = null;
		try {

			st = connexion.createStatement();
			String rq;
			if (condition != "") {
				rq = "Select " + colonne + " from " + table + " where " + condition + ";";
			} else {
				rq = "Select " + colonne + " from " + table + ";";
			}

			rs = st.executeQuery(rq);
			if (rs.next() == true)
				valeur = rs.getObject(1).toString();

		} catch (SQLException e) {
			e.printStackTrace();

		}
		try {

			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return valeur;

	}

	// cette methode permet de demander un tableau de donnee en particulier (ex:
	// select id_benevoles from benevoles)
	public void getTabValeurBDD(String colonne, String table, String[] tab) {

		ResultSet rs = null;
		Statement st = null;
		
		try {

			st = connexion.createStatement();
			String rq;

			rq = "Select " + colonne + " from " + table + ";";

			rs = st.executeQuery(rq);

			for (int i = 0; i <= tab.length; i++) {
				if (rs.next() == true)
					tab[i] = rs.getObject(1).toString();
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
		try {

			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// METHODE IMPORTANTE: Elle ferme la connexion effectu�e entre jdbc et bdd
	// donc il faudra le faire syst�matiquement quand on utilise les methodes du
	// dessus
	public void CloseConnexion() {
		try {
			this.connexion.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
