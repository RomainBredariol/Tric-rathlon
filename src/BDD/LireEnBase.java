package BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LireEnBase {

	public LireEnBase(String table) {

		/* Connexion à la base de données */
		String url = "jdbc:mysql://localhost/test";
		String utilisateur = "root";
		String motDePasse = "azerty";
		Connection connexion = null;
		Statement st = null;
		ResultSet rs = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
			st = connexion.createStatement();
			String requete = "SELECT * FROM " + table + ";";

			rs = st.executeQuery(requete);
			while (rs.next()) {
			System.out.println(rs.getString("userName"));
		}

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} finally {
			try {
				connexion.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
