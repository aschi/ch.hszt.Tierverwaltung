package ch.hszt.tierverwaltung.database.kunde;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ch.hszt.tierverwaltung.backend.Kunde;
import ch.hszt.tierverwaltung.backend.ValidationException;
import ch.hszt.tierverwaltung.database.DBConnection;
import ch.hszt.tierverwaltung.database.IDataMapper;
import ch.hszt.tierverwaltung.database.tier.TierDataMapper;

public final class KundeDataMapper implements IDataMapper<Kunde> {

	DBConnection dbConnection = DBConnection.getInstance();

	public KundeDataMapper() {

	}

	/**
	 * Fügt einen neuen Kundeneintrag in die Datenbank kunde ein.
	 * 
	 * @param kunde
	 *            Kundeneintrag welcher in die Datenbank eingefügt werden soll
	 * @throws SQLException
	 */
	@Override
	public int insert(Kunde entry) throws SQLException {

		String sql;
		ResultSet rs = null;
		sql = "INSERT INTO 'kunde' VALUES (null, '" + entry.getName() + "', "
				+ "\'" + entry.getVorname() + "\', \'" + entry.getAdresse()
				+ "\', '" + entry.getPlz() + "\', \'" + entry.getOrt()
				+ "\', \'" + entry.getTelefon() + "\', \'" + entry.getEMail()
				+ "\');";
		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		stmt.executeUpdate(sql);

		PreparedStatement pstmt = dbConnection.getConn().prepareStatement(
				"select max(kundeID) max from 'kunde';");
		rs = pstmt.executeQuery();
		if (rs.next()) {
			;
			return rs.getInt("max");
		} else {
			return 0;
		}
	}

	@Override
	public void update(Kunde entry) throws SQLException {
		String sql;
		sql = "UPDATE 'kunde' SET " + "name = \'" + entry.getName() + "\', "
				+ "vorname = '" + entry.getVorname() + "', " + "adresse = \'"
				+ entry.getAdresse() + "\', " + "plz = \'" + entry.getPlz()
				+ "\', " + "ort = \'" + entry.getOrt() + "\', "
				+ "telefon = \'" + entry.getTelefon() + "\', " + "eMail = \'"
				+ entry.getEMail() + "\'" + 
				"WHERE kundeID = " + entry.getID() + ";";

		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		stmt.executeUpdate(sql);

	}

	/**
	 * Diese Methode löscht einen Kundeneintrag aus der Tabelle kunde
	 * 
	 * @param kunde
	 *            Kundeneintrag, welcher gelöscht werden soll
	 * @throws SQLException
	 */
	@Override
	public void delete(Kunde entry) throws SQLException {
		String sql;
		sql = "DELETE FROM 'kunde' WHERE kundeID = " + entry.getKundeID() + ";";
		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		stmt.executeUpdate(sql);
	}

	/**
	 * Diese Methode liefert eine ArrayList aller Kundeneinträge zurück, welche
	 * in der Tabelle kunde enthalten sind
	 * 
	 * @return Kundeneinträge, welche in der Tabelle kunde enthalten sind
	 * @throws SQLException
	 */
	@Override
	public List<Kunde> getList() throws SQLException {
		String sql;
		sql = "SELECT * FROM 'kunde';";
		System.out.println(sql);

		Statement stmt = dbConnection.getConn().createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<Kunde> kundenarray = new ArrayList<Kunde>();

		while (rs.next()) {

			Kunde kunde = new Kunde(rs.getInt("kundeID"), rs.getString("name"),
					rs.getString("vorname"), rs.getString("adresse"),
					rs.getString("plz"), rs.getString("ort"),
					rs.getString("telefon"), rs.getString("eMail"));

			kunde.setTiere(new TierDataMapper().getTiereZuKunde(kunde
					.getKundeID()));

			kundenarray.add(kunde);
		}
		return kundenarray;
	}

	@Override
	public Kunde getEntry(int id) throws SQLException {
		String sql;
		sql = "SELECT * FROM 'kunde' WHERE kundeID = " + id + ";";
		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		;

		while (rs.next()) {

			Kunde kunde = new Kunde(rs.getInt("kundeID"), rs.getString("name"),
					rs.getString("vorname"), rs.getString("adresse"),
					rs.getString("plz"), rs.getString("ort"),
					rs.getString("telefon"), rs.getString("eMail"));

			return kunde;
		}

		return null;
	}

	@Override
	public void save(Kunde entry) throws SQLException, ValidationException {
		entry.validate();
		if (entry.getKundeID() <= 0) {
			entry.setKundeID(insert(entry));
		} else {
			update(entry);
		}
	}

}
