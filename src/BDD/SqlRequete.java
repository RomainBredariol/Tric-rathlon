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
		String url = "jdbc:mysql://localhost/tricerathlon";
		String utilisateur = "root";
		String motDePasse = "azerty";
		 this.connexion = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Driver ok !");
		try {
			connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Connexion Effective !");
	}
	
	public void Connect(String requete) {
		/* Connexion à la base de données */
		ResultSet rs = null;
		Statement st = null;
		try {

			st = connexion.createStatement();
			
			String rq= requete;
			
			//Requete de lecture
			if (rq.startsWith("select") || rq.startsWith("Select") || rq.startsWith("SELECT")) {
				rs = st.executeQuery(rq);
				// On récupère les MetaData
				ResultSetMetaData resultMeta = rs.getMetaData();

				System.out.println("\n****************************************************************************");
				// On affiche le nom des colonnes
				for (int i = 1; i <= resultMeta.getColumnCount(); i++)
					System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");

				System.out.println("\n****************************************************************************");

				while (rs.next()) {
					for (int i = 1; i <= resultMeta.getColumnCount(); i++)
						System.out.print("\t" + rs.getObject(i).toString() + "\t |");

					System.out.println("\n------------------------------------------------------------------------");
				}
			} else { // Requete d'ecriture
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
	
	public String getUneValeurBDD(String colonne, String table, String condition) {
		/* Connexion à la base de données */

		ResultSet rs = null;
		Statement st = null;
		String valeur = null;
		try {


			st = connexion.createStatement();
			String rq;
			if (condition != "") {
				rq= "Select "+colonne+" from "+table+" where "+condition+";";
			}else {
				rq= "Select "+colonne+" from "+table+";";
			}
			
				rs = st.executeQuery(rq);
			if(rs.next()==true)
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
	
	public void CloseConnexion() {
		try {
			this.connexion.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
	}

}
