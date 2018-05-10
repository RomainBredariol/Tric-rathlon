package BDD;

public class test {

	public static void main(String[]a) {
		SqlRequete requete = new SqlRequete();
		int nbtotal = Integer.parseInt(requete.getUneValeurBDD("count(nom)", "benevoles", ""));
		String[] tabId = new String[nbtotal];
		requete.getTabValeurBDD("id_benevoles", "benevoles", tabId);
		for(int i = 0; i<tabId.length; i++) {
			System.out.println(tabId[i]);
		}
	}
}
