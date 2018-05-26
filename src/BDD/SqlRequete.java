package BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlRequete {

	private Connection connexion;

	public SqlRequete() {
		//On declare url, login et mdp de la bdd
		String url = "jdbc:mysql://localhost/tricerathlon";
		String utilisateur = "root";
		String motDePasse = "azerty";
		this.connexion = null;
		try {
			// ici on charge les drivers jdbc
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Driver ok !");
		try {
			//on se connecte
			connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Connexion Effective !");
	}

	// methode qui envoie une requetre � la bdd (sert � ecrire et modifier la bdd)
	public void Connect(String requete) {
		/* Connexion � la bdd */
		// le resultset est l'�l�ment que va nous renvoyer la bdd
		ResultSet rs = null;
		// le statement est un element qui va transmettre la requete
		Statement st = null;
		try {

			st = connexion.createStatement();

			String rq = requete;

			// Requete de lecture
			if (rq.startsWith("select") || rq.startsWith("Select") || rq.startsWith("SELECT")) {
				// executeQuery pour de la lecture et executeUpdate pour une modif (cf. else suivant)
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

	// cette m�thode permet de demander une donn�e en particulier
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

	// cette methode permet de demander jeu de donnee en particulier (ex:
	// select id_benevoles from benevoles) et le stocke dans un tableau de String
	public void getTabValeurBDD(String colonne, String table, String condition, String[] tab) {

		ResultSet rs = null;
		Statement st = null;
		
		try {

			st = connexion.createStatement();
			String rq;
			
			if(condition != "") {
				rq = "Select " + colonne + " from " + table + " where "+condition;
			}else {
				rq = "Select " + colonne + " from " + table + ";";
			}
			
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
