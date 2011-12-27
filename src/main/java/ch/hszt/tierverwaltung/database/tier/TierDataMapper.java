package ch.hszt.tierverwaltung.database.tier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ch.hszt.tierverwaltung.backend.Pet;
import ch.hszt.tierverwaltung.backend.ValidationException;
import ch.hszt.tierverwaltung.database.DBConnection;
import ch.hszt.tierverwaltung.database.IDataMapper;

public final class TierDataMapper implements IDataMapper<Pet> {

	DBConnection dbConnection = DBConnection.getInstance();

	public TierDataMapper() {

	}

	/**
	 * Fügt einen neuen Tiereintrag in die Datenbank tier ein.
	 * 
	 * @param tier
	 *            Tiereintrag welcher in die Datenbank eingefügt werden soll
	 * @throws SQLException
	 */
	@Override
	public int insert(Pet entry) throws SQLException {
		String sql;
		ResultSet rs = null;
		sql = "INSERT INTO 'tier' VALUES (null, null, '" + entry.getSpecies()
				+ "', " + "\'" + entry.getRace() + "\', \'" + entry.getName()
				+ "\', " + entry.getAge() + ", " + entry.getSizeId()
				+ ", \'" + entry.getDiseasePattern() + "\', \'"
				+ entry.getEatingHabits() + "\', \'" + entry.getRun()
				+ "\', \'" + entry.getContactOtherPets() + "\', \'"
				+ entry.getContactPeople() + "\', \'" + entry.getRemarks()
				+ "\', " + entry.getAdditionalCosts() + ");";
		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		stmt.executeUpdate(sql);

		PreparedStatement pstmt = dbConnection.getConn()
				.prepareStatement("select max(tierID) max from 'tier';");
		rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("max");
		} else {
			return 0;
		}

	}

	@Override
	public void update(Pet entry) throws SQLException {
		String sql;
		sql = "UPDATE 'tier' SET " + "fkKunde = " + entry.getFkKunde() + ", "
				+ "art = '" + entry.getSpecies() + "', " + "rasse = \'"
				+ entry.getRace() + "\', " + "name = \'" + entry.getName()
				+ "\', " + "tieralter = " + entry.getAge() + ", "
				+ "groesseID = " + entry.getSizeId() + ", "
				+ "krankheitsbild = \'" + entry.getDiseasePattern() + "\', "
				+ "essgewohnheit = \'" + entry.getEatingHabits() + "\', "
				+ "auslauf = \'" + entry.getRun() + "\', "
				+ "umgangTier = \'" + entry.getContactOtherPets() + "\', "
				+ "umgangMensch = \'" + entry.getContactPeople() + "\', "
				+ "anmerkungen = \'" + entry.getRemarks() + "\', "
				+ "zusatzkosten = " + entry.getAdditionalCosts()
				+ " WHERE tierID = " + entry.getTierID() + ";";

		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		stmt.executeUpdate(sql);

	}
	
	/**
	 * This method returns an ArrayList with all Pets which aren't assigned yet to 
	 * a customer
	 * @return ArrayList with all pets which aren't assigned yet
	 */
	public ArrayList<Pet> getUnassignedPets() throws SQLException {
		String sql;
		sql = "SELECT * FROM 'tier' WHERE fkKunde < 1 OR fkKunde is null;";
		System.out.println(sql);

		Statement stmt = dbConnection.getConn().createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		return fillPets(rs);

	}
	

	//Returns an ArrayList with all Pets from the Resultset
	private ArrayList<Pet> fillPets(ResultSet rs) throws SQLException {
		ArrayList<Pet> tierarray = new ArrayList<Pet>();
		
		while (rs.next()) {
			char[] auslauf = rs.getString("auslauf").toCharArray();

			Pet tier = new Pet(rs.getInt("tierID"), rs.getInt("fkKunde"),
					rs.getString("art"), rs.getString("rasse"),
					rs.getString("name"), rs.getInt("tieralter"),
					rs.getInt("groesseID"), rs.getString("krankheitsbild"),
					rs.getString("essgewohnheit"), auslauf[0],
					rs.getString("umgangTier"), rs.getString("umgangMensch"),
					rs.getString("anmerkungen"), rs.getDouble("zusatzkosten"));

			tierarray.add(tier);
		}
		
		return tierarray;
	}

	/**
	 * Diese Methode löscht einen Tiereintrag aus der Tabelle tier
	 * 
	 * @param tier
	 *            Tiereintrag, welcher gelöscht werden soll
	 * @throws SQLException
	 */
	@Override
	public void delete(Pet entry) throws SQLException {
		String sql;
		sql = "DELETE FROM 'tier' WHERE tierID = " + entry.getTierID() + ";";
		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
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
	public List<Pet> getList() throws SQLException {
		String sql;
		sql = "SELECT * FROM 'tier';";
		System.out.println(sql);

		Statement stmt = dbConnection.getConn().createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		return fillPets(rs);
	}

	@Override
	public Pet getEntry(int id) throws SQLException {
		String sql;
		sql = "SELECT * FROM 'tier' WHERE tierID = " + id + ";";
		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		;

		while (rs.next()) {
			char[] auslauf = rs.getString("auslauf").toCharArray();

			Pet tier = new Pet(rs.getInt("tierID"), rs.getInt("fkKunde"),
					rs.getString("art"), rs.getString("rasse"),
					rs.getString("name"), rs.getInt("tieralter"),
					rs.getInt("groesseID"), rs.getString("krankheitsbild"),
					rs.getString("essgewohnheit"), auslauf[0],
					rs.getString("umgangTier"), rs.getString("umgangMensch"),
					rs.getString("anmerkungen"), rs.getDouble("zusatzkosten"));

			return tier;
		}

		return null;
	}

	public ArrayList<Pet> getTiereZuKunde(int kundeID) throws SQLException {
		String sql;
		sql = "SELECT * FROM 'tier' WHERE fkKunde = " + kundeID + ";";
		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		return fillPets(rs);

	}

	@Override
	public void save(Pet entry) throws SQLException, ValidationException {
		entry.validate();
		if (entry.getTierID() <= 0) {
			entry.setTierID(insert(entry));
		} else {
			update(entry);
		}

	}

}
