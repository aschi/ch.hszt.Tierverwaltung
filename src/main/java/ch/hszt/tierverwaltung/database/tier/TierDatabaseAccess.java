package ch.hszt.tierverwaltung.database.tier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ch.hszt.tierverwaltung.database.IDatabaseAccess;
import ch.hszt.tierverwaltung.tier.backend.Tier;

public final class TierDatabaseAccess implements IDatabaseAccess<Tier> {

	private Connection conn;
	private static TierDatabaseAccess instance;

	private TierDatabaseAccess() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("fehler db");
		}
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:tierverwaltung.db");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("fehler jdbc");
		}

	}

	/**
 	 * Gewährleistet, dass nur eine Instanz dieser Klasse vorhanden ist
 	 * und gibt diese zurück
 	 * @return TierDatabaseAccess die einzige vorhandene Instanz dieser Klasse
 	 * 
 	 * /
	public static synchronized TierDatabaseAccess getInstance() {
		if (instance != null) {
			return instance;
		} else {
			instance = new TierDatabaseAccess();
			return instance;
		}
	}
	/**
	 * Fügt einen neuen Tiereintrag in die Datenbank tier ein.
	 * 
	 * @param tier Tiereintrag welcher in die Datenbank eingefügt werden soll
	 * @throws SQLException
	 */
	@Override
	public int insert(Tier entry) throws SQLException{
		String sql;
		ResultSet rs = null;
		sql = "INSERT INTO 'tier' VALUES (null, null, '" + entry.getArt() + "', "
				+ "\'" + entry.getRasse() + "\', \'" + entry.getName() + "\', "
				+ entry.getTieralter() + ", " + entry.getGroesseID() + ", \'"
				+ entry.getKrankheitsbild() + "\', \'" + entry.getEssgewohnheit()
				+ "\', \'" + entry.getAuslauf() + "\', \'"
				+ entry.getUmgangTier() + "\', \'" + entry.getUmgangMensch()
				+ "\', \'" + entry.getAnmerkungen() + "\', "
				+ entry.getZusatzkosten() + ");";
		System.out.println(sql);
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		
		PreparedStatement pstmt = conn.prepareStatement("select max(tierID) max from 'tier';");
		rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("max");
		} else {
			return 0;
		}

	}

	@Override
	public void update(Tier entry) throws SQLException{
		String sql;
		sql = "UPDATE 'tier' SET " +
			 "fkKunde = " + entry.getFkKunde() + ", " + 
			 "art = '" + entry.getArt() + "', " +
			 "rasse = \'" + entry.getRasse() + "\', " +
			 "name = \'" + entry.getName() + "\', " +
			 "tieralter = " + entry.getTieralter() + ", " +
			 "groesseID = " + entry.getGroesseID() + ", " + 
			 "krankheitsbild = \'" + entry.getKrankheitsbild() + "\', " +
			 "essgewohnheit = \'" + entry.getEssgewohnheit() + "\', "+ 
			 "auslauf = \'" + entry.getAuslauf() + "\', " +
			 "umgangTier = \'" + entry.getUmgangTier() + "\', " +
			 "umgangMensch = \'" + entry.getUmgangMensch() + "\', " +
			 "anmerkungen = \'" + entry.getAnmerkungen() + "\', " +
			 "zusatzkosten = " + entry.getZusatzkosten() + 
			 " WHERE tierID = " + entry.getTierID() +
			 ";";
				
		System.out.println(sql);
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);

	}
	
	/**
	 * Diese Methode löscht einen Tiereintrag aus der Tabelle tier
	 * 
	 * @param tier Tiereintrag, welcher gelöscht werden soll
	 * @throws SQLException
	 */
	@Override
	public void delete(Tier entry) throws SQLException{
		String sql;
		sql = "DELETE FROM 'tier' WHERE tierID = " + entry.getTierID() + ";";
		System.out.println(sql);
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);
	}

	/**
	 * Diese Methode liefert eine ArrayList aller Tiereinträge zurück, welche in
	 * der Tabelle tier enthalten sind
	 * 
	 * @return Tiereinträge, welche in der Tabelle tier enthalten sind
	 * @throws SQLException
	 */
	@Override
	public List<Tier> getList() throws SQLException {
		String sql;
		sql = "SELECT * FROM 'tier';";
		System.out.println(sql);

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<Tier> tierarray = new ArrayList<Tier>();

		while (rs.next()) {
			char[] auslauf = rs.getString("auslauf").toCharArray();

			Tier tier = new Tier(rs.getInt("tierID"), rs.getInt("fkKunde"), rs.getString("art"), rs.getString("rasse"),
					rs.getString("name"), rs.getInt("tieralter"),
					rs.getInt("groesseID"), rs.getString("krankheitsbild"),
					rs.getString("essgewohnheit"), auslauf[0],
					rs.getString("umgangTier"), rs.getString("umgangMensch"),
					rs.getString("anmerkungen"), rs.getDouble("zusatzkosten"));

			tierarray.add(tier);
		}

		return tierarray;
	}

	@Override
	public Tier getEntry(int id) throws SQLException{
		String sql;
		sql = "SELECT * FROM 'tier' WHERE tierID = " + id + ";";
		System.out.println(sql);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);;
		
		while (rs.next()) {
			char[] auslauf = rs.getString("auslauf").toCharArray();

			Tier tier = new Tier(rs.getInt("tierID"), rs.getInt("fkKunde"), rs.getString("art"), rs.getString("rasse"),
					rs.getString("name"), rs.getInt("tieralter"),
					rs.getInt("groesseID"), rs.getString("krankheitsbild"),
					rs.getString("essgewohnheit"), auslauf[0],
					rs.getString("umgangTier"), rs.getString("umgangMensch"),
					rs.getString("anmerkungen"), rs.getDouble("zusatzkosten"));

			return tier;
		}
		
		return null;
	}

}
