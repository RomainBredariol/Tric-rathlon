package BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SqlRequete {
	
	

	public String requete() {
		Scanner sc = new Scanner(System.in);
		String requete = sc.nextLine();
		return requete;

	}
	
	public void Connect() {
		/* Connexion à la base de données */
		String url = "jdbc:mysql://localhost/tricerathlon";
		String utilisateur = "root";
		String motDePasse = "azerty";
		Connection connexion = null;
		ResultSet rs = null;
		Statement st = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver ok !");
			connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
			System.out.println("Connexion Effective !");
			st = connexion.createStatement();
			System.out.println("Veuillez entrer votre requete :");
			String rq = this.requete();
			
			//Requete de lecture
			if (rq.startsWith("select") || rq.startsWith("Select") || rq.startsWith("SELECT")) {
				rs = st.executeQuery(rq);
				// On récupère les MetaData
				ResultSetMetaData resultMeta = rs.getMetaData();

				System.out.println("\n**********************************");
				// On affiche le nom des colonnes
				for (int i = 1; i <= resultMeta.getColumnCount(); i++)
					System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");

				System.out.println("\n**********************************");

				while (rs.next()) {
					for (int i = 1; i <= resultMeta.getColumnCount(); i++)
						System.out.print("\t" + rs.getObject(i).toString() + "\t |");

					System.out.println("\n---------------------------------");
				}
			} else { // Requete d'ecriture
				st.executeUpdate(rq);
				System.out.println("Requete admise");
			}

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
