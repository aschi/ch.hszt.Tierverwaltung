package ch.hszt.tierverwaltung.database.tierplatz;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ch.hszt.tierverwaltung.backend.Tierplatz;
import ch.hszt.tierverwaltung.backend.ValidationException;
import ch.hszt.tierverwaltung.database.DBConnection;
import ch.hszt.tierverwaltung.database.IDataMapper;

public final class TierplatzDataMapper implements IDataMapper<Tierplatz> {

	DBConnection dbConnection = DBConnection.getInstance();

	public TierplatzDataMapper() {

	}

	/**
	 * Inserts a new petplace-entry into the table petplace
	 * 
	 * @param pet
	 *            pet-entry to be insertet into the table
	 * @throws SQLException
	 */
	@Override
	public int insert(Tierplatz entry) throws SQLException {
		String sql;
		ResultSet rs = null;
		sql = "INSERT INTO 'tierplatz' VALUES (null, " + entry.getGeeignetFuerTierID()
				+ ", " + entry.getGroesse() + ", \'" + entry.getAusstattung()
				+ "\', " + entry.getAnzahlTiere() + ", \'" + entry.getAuslauf()
				+ "\', " + entry.getAuslaufGroesse() + ");";
		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		stmt.executeUpdate(sql);

		PreparedStatement pstmt = dbConnection.getConn()
				.prepareStatement("select max(tierplatzID) max from 'tierplatz';");
		rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("max");
		} else {
			return 0;
		}

	}

	@Override
	public void update(Tierplatz entry) throws SQLException {
		String sql;
		sql = "UPDATE 'tierplatz' SET " 
				+ "geeignetFuerTierID = " + entry.getGeeignetFuerTierID() + ", "
				+ "groesse = " + entry.getGroesse() + ", " 
				+ "ausstattung = \'"	+ entry.getAusstattung() + "\', " 
				+ "anzahlTiere = " + entry.getAnzahlTiere() + ", " 
				+ "auslauf = \'" + entry.getAuslauf() + "\', "
				+ "auslaufGroesse = " + entry.getAuslaufGroesse() + " "
				+ "WHERE tierplatzID = " + entry.getID() + ";";

		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		stmt.executeUpdate(sql);

	}
	

	//Returns an ArrayList with all petplaces from the Resultset
	private ArrayList<Tierplatz> fillPetplaces(ResultSet rs) throws SQLException {
		ArrayList<Tierplatz> tierarray = new ArrayList<Tierplatz>();

		while (rs.next()) {
			char[] auslauf = rs.getString("auslauf").toCharArray();

			Tierplatz tierplatz = new Tierplatz(rs.getInt("geeignetFuerTierID"),
					rs.getInt("groesse"), rs.getString("ausstattung"),
					rs.getInt("anzahlTiere"), auslauf[0], rs.getInt("auslaufGroesse"), rs.getInt("tierplatzID"));

			tierarray.add(tierplatz);
		}
		
		return tierarray;
	}

	/**
	 * This method deletes a petplace-entry from table petplace
	 * 
	 * @param petplace
	 *            petplace to be deleted
	 * @throws SQLException
	 */
	@Override
	public void delete(Tierplatz entry) throws SQLException {
		String sql;
		sql = "DELETE FROM 'tierplatz' WHERE tierplatzID = " + entry.getID() + ";";
		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		stmt.executeUpdate(sql);
	}

	/**
	 * this method retuns an ArrayList with all petplace-entries from table petplace
	 * 
	 * @return petplaceentries from petplace-table
	 * @throws SQLException
	 */
	@Override
	public List<Tierplatz> getList() throws SQLException {
		String sql;
		sql = "SELECT * FROM 'tierplatz';";
		System.out.println(sql);

		Statement stmt = dbConnection.getConn().createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		return fillPetplaces(rs);
	}

	@Override
	public Tierplatz getEntry(int id) throws SQLException {
		String sql;
		sql = "SELECT * FROM 'tierplatz' WHERE tierplatzID = " + id + ";";
		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		;

		while (rs.next()) {
			char[] auslauf = rs.getString("auslauf").toCharArray();

			Tierplatz tierplatz = new Tierplatz(rs.getInt("geeignetFuerTierID"),
					rs.getInt("groesse"), rs.getString("ausstattung"),
					rs.getInt("anzahlTiere"), auslauf[0], rs.getInt("auslaufGroesse"), rs.getInt("tierplatzID"));

			return tierplatz;
		}

		return null;
	}

	public ArrayList<Tierplatz> getTiereZuKunde(int kundeID) throws SQLException {
		String sql;
		sql = "SELECT * FROM 'tier' WHERE fkKunde = " + kundeID + ";";
		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		return fillPetplaces(rs);

	}

	@Override
	public void save(Tierplatz entry) throws SQLException, ValidationException {
		entry.validate();
		if (entry.getID() <= 0) {
			entry.setID(insert(entry));
		} else {
			update(entry);
		}

	}

}
