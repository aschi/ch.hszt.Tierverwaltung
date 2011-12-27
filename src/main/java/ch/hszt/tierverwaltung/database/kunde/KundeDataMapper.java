package ch.hszt.tierverwaltung.database.kunde;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ch.hszt.tierverwaltung.backend.Customer;
import ch.hszt.tierverwaltung.backend.Pet;
import ch.hszt.tierverwaltung.backend.ValidationException;
import ch.hszt.tierverwaltung.database.DBConnection;
import ch.hszt.tierverwaltung.database.IDataMapper;
import ch.hszt.tierverwaltung.database.tier.TierDataMapper;

public final class KundeDataMapper implements IDataMapper<Customer> {

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
	public int insert(Customer entry) throws SQLException {

		String sql;
		ResultSet rs = null;
		sql = "INSERT INTO 'kunde' VALUES (null, '" + entry.getName() + "', "
				+ "\'" + entry.getFirstName() + "\', \'" + entry.getAddress()
				+ "\', '" + entry.getZip() + "\', \'" + entry.getCity()
				+ "\', \'" + entry.getPhoneNo() + "\', \'" + entry.getEMail()
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
	public void update(Customer entry) throws SQLException {
		String sql;
		sql = "UPDATE 'kunde' SET " + "name = \'" + entry.getName() + "\', "
				+ "vorname = '" + entry.getFirstName() + "', " + "adresse = \'"
				+ entry.getAddress() + "\', " + "plz = \'" + entry.getZip()
				+ "\', " + "ort = \'" + entry.getCity() + "\', "
				+ "telefon = \'" + entry.getPhoneNo() + "\', " + "eMail = \'"
				+ entry.getEMail() + "\'" + ";";

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
	public void delete(Customer entry) throws SQLException {
		//Beziehungen zum Tier entfernen
		for(Pet t : entry.getPets()){
			t.setFkKunde(-1);
			try {
				new TierDataMapper().save(t);
			} catch (ValidationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		String sql;
		sql = "DELETE FROM 'kunde' WHERE kundeID = " + entry.getCustomerId() + ";";
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
	public List<Customer> getList() throws SQLException {
		String sql;
		sql = "SELECT * FROM 'kunde';";
		System.out.println(sql);

		Statement stmt = dbConnection.getConn().createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<Customer> kundenarray = new ArrayList<Customer>();

		while (rs.next()) {

			Customer kunde = new Customer(rs.getInt("kundeID"), rs.getString("name"),
					rs.getString("vorname"), rs.getString("adresse"),
					rs.getString("plz"), rs.getString("ort"),
					rs.getString("telefon"), rs.getString("eMail"));

			kunde.setPets(new TierDataMapper().getTiereZuKunde(kunde
					.getCustomerId()));

			kundenarray.add(kunde);
		}
		return kundenarray;
	}

	@Override
	public Customer getEntry(int id) throws SQLException {
		String sql;
		sql = "SELECT * FROM 'kunde' WHERE kundeID = " + id + ";";
		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		;

		while (rs.next()) {

			Customer kunde = new Customer(rs.getInt("kundeID"), rs.getString("name"),
					rs.getString("vorname"), rs.getString("adresse"),
					rs.getString("plz"), rs.getString("ort"),
					rs.getString("telefon"), rs.getString("eMail"));

			return kunde;
		}

		return null;
	}

	@Override
	public void save(Customer entry) throws SQLException, ValidationException {
		entry.validate();
		if (entry.getCustomerId() <= 0) {
			entry.setCustomerId(insert(entry));
		} else {
			update(entry);
		}
	}

}
