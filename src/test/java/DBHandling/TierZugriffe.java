package DBHandling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ch.hszt.tierverwaltung.tier.backend.Tiereintrag;

final class TierZugriffe {
	
	private Connection conn;
	
	public TierZugriffe() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:tierverwaltung.db");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Fügt einen neuen Tiereintrag in die Datenbank tier ein.
	 * @param tier Tiereintrag welcher in die Datenbank eingefügt werden soll
	 * @throws SQLException
	 */
	public void insertNewTier(Tiereintrag tier) throws SQLException {
		
		String sql;
		sql = "INSERT INTO 'tier' VALUES (null, '" + tier.getArt() + "', " +
				"\'" + tier.getRasse() +"\', \'" + tier.getName() + "\', " + tier.getTieralter() + ", "
				+ tier.getGroesseID() + ", \'" + tier.getKrankheitsbild() + "\', \'" + tier.getEssgewohnheit() + "\', \'" 
				+ tier.getAuslauf() + "\', \'" + tier.getUmgangTier() + "\', \'" + tier.getUmgangMensch() + "\', \'" + tier.getAnmerkungen() + "\', " 
				+ tier.getZusatzkosten() + ");";
		System.out.println(sql);
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
	}
	
	/**
	 * Diese Methode löscht einen Tiereintrag aus der Tabelle tier
	 * @param tier Tiereintrag, welcher gelöscht werden soll
	 * @throws SQLException
	 */
	public void deleteTier(Tiereintrag tier) throws SQLException {
		String sql;
		sql = "DELETE FROM 'tier' t " +
				"WHERE t.art = \'" +tier.getArt() + "\' and" +
				" t.rassse = \'" +tier.getRasse() + "\' and" +
				" t.name = \'" +tier.getName() + "\' and" +
				" t.tieralter = " + tier.getTieralter() + " and " +
				" t.groesseID = " + tier.getGroesseID() + " and" + 
				" t.krankheitsbild = \'" +tier.getKrankheitsbild() + "\' and" +
				" t.essgewohnheit = \'" +tier.getEssgewohnheit() + "\' and" +
				" t.auslauf = \'" +tier.getAuslauf() + "\' and" +
				" t.umgangTier = \'" +tier.getUmgangTier() + "\' and" +
				" t.umgangMensch = \'" +tier.getUmgangMensch() + "\' and" +
				" t.anmerkungen = \'" +tier.getAnmerkungen() + "\' and" +
				" t.zusatzkosten= \'" +tier.getZusatzkosten() + "\' ;";
		System.out.println(sql);
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
	}
	
	/**
	 * Diese Methode liefert eine ArrayList aller Tiereinträge zurück, welche in der Tabelle tier enthalten sind
	 * @return Tiereinträge, welche in der Tabelle tier enthalten sind
	 * @throws SQLException
	 */
	public ArrayList<Tiereintrag> selectTier() throws SQLException {
		String sql;
		sql = "SELECT * FROM 'tier';";

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<Tiereintrag> tierarray = new ArrayList<Tiereintrag>();

		while (rs.next()) {
			char[] auslauf = rs.getString("auslauf").toCharArray();
			
			Tiereintrag tier = new Tiereintrag(rs.getString("art"), rs.getString("rasse"), rs.getString("name"), 
					rs.getInt("tieralter"), rs.getInt("groesseID"), rs.getString("krankheitsbild"), rs.getString("essgewohnheit"), 
					auslauf[0], rs.getString("umgangTier"), rs.getString("umgangMensch"), rs.getString("anmerkungen"), 
					rs.getDouble("zusatzkosten"));
					
			tierarray.add(tier);
		}

		rs.close();
		conn.close();
		
		return tierarray;
	}

}
