package ch.hszt.tierverwaltung.database.kunde;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ch.hszt.tierverwaltung.database.IDatabaseAccess;
import ch.hszt.tierverwaltung.database.tier.TierDatabaseAccess;
import ch.hszt.tierverwaltung.kunden.backend.Kunde;

public final class KundeDatabaseAccess implements IDatabaseAccess<Kunde> {

	private Connection conn;
	private static KundeDatabaseAccess instance;

	private KundeDatabaseAccess() {
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
 	 * @return KundeDatabaseAccess die einzige vorhandene Instanz dieser Klasse
 	 * 
 	 */
	public static synchronized KundeDatabaseAccess getInstance() {
		if (instance != null) {
			return instance;
		} else {
			instance = new KundeDatabaseAccess();
			return instance;
		}
	}
	/**
	 * Fügt einen neuen Kundeneintrag in die Datenbank kunde ein.
	 * 
	 * @param tier Kundeneintrag welcher in die Datenbank eingefügt werden soll
	 * @throws SQLException
	 */
	@Override
	public int insert(Kunde entry) throws SQLException{
		String sql;
		ResultSet rs = null;
		sql = "INSERT INTO 'kunde' VALUES (null, '" + entry.getName() + "', "
				+ "\'" + entry.getVorname() + "\', \'" + entry.getAdresse() + "\', '"
				+ entry.getPlz() + "\', \'" + entry.getOrt() + "\', \'"
				+ entry.getTelefon() + "\', \'" + entry.getEMail()
				+ "\');";
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
	public void update(Kunde entry) throws SQLException{
		String sql;
		sql = "UPDATE 'kunde' SET " +
			 "name = " + entry.getName() + ", " + 
			 "vorname = '" + entry.getVorname() + "', " +
			 "adresse = \'" + entry.getAdresse() + "\', " +
			 "plz = \'" + entry.getPlz() + "\', " + 
			 "ort = \'" + entry.getOrt() + "\', " +
			 "telefon = \'" + entry.getTelefon() + "\', "+ 
			 "eMail = \'" + entry.getEMail() + "\', " +
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
	public void delete(Kunde entry) throws SQLException{
		String sql;
		sql = "DELETE FROM 'kunde' WHERE kundeID = " + entry.getKundeID() + ";";
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
	public List<Kunde> getList() throws SQLException {
		String sql;
		sql = "SELECT * FROM 'kunde';";
		System.out.println(sql);

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<Kunde> kundenarray = new ArrayList<Kunde>();

		while (rs.next()) {

			Kunde kunde = new Kunde(rs.getInt("kundeID"), rs.getString("name"), rs.getString("vorname"), rs.getString("adresse"),
					rs.getString("plz"), rs.getString("ort"),
					rs.getString("telefon"), rs.getString("eMail"));
			
			kunde.setTiere(TierDatabaseAccess.getInstance().getTiereZuKunde(kunde.getKundeID()));

			kundenarray.add(kunde);
		}

		return kundenarray;
	}

	@Override
	public Kunde getEntry(int id) throws SQLException{
		String sql;
		sql = "SELECT * FROM 'kunde' WHERE kundeID = " + id + ";";
		System.out.println(sql);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);;
		
		while (rs.next()) {

			Kunde kunde = new Kunde(rs.getInt("kundeID"), rs.getString("name"), rs.getString("vorname"), rs.getString("adresse"),
					rs.getString("plz"), rs.getString("ort"),
					rs.getString("telefon"), rs.getString("eMail"));

			return kunde;
		}
		
		return null;
	}

}
