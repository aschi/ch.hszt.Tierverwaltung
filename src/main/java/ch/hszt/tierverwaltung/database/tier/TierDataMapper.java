package ch.hszt.tierverwaltung.database.tier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ch.hszt.tierverwaltung.backend.Tier;
import ch.hszt.tierverwaltung.backend.ValidationException;
import ch.hszt.tierverwaltung.database.DBConnection;
import ch.hszt.tierverwaltung.database.IDataMapper;

public final class TierDataMapper implements IDataMapper<Tier> {

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
	public int insert(Tier entry) throws SQLException {
		String sql;
		ResultSet rs = null;
		sql = "INSERT INTO 'tier' VALUES (null, null, '" + entry.getArt()
				+ "', " + "\'" + entry.getRasse() + "\', \'" + entry.getName()
				+ "\', " + entry.getTieralter() + ", " + entry.getGroesseID()
				+ ", \'" + entry.getKrankheitsbild() + "\', \'"
				+ entry.getEssgewohnheit() + "\', \'" + entry.getAuslauf()
				+ "\', \'" + entry.getUmgangTier() + "\', \'"
				+ entry.getUmgangMensch() + "\', \'" + entry.getAnmerkungen()
				+ "\', " + entry.getZusatzkosten() + ");";
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
	public void update(Tier entry) throws SQLException {
		String sql;
		sql = "UPDATE 'tier' SET " + "fkKunde = " + entry.getFkKunde() + ", "
				+ "art = '" + entry.getArt() + "', " + "rasse = \'"
				+ entry.getRasse() + "\', " + "name = \'" + entry.getName()
				+ "\', " + "tieralter = " + entry.getTieralter() + ", "
				+ "groesseID = " + entry.getGroesseID() + ", "
				+ "krankheitsbild = \'" + entry.getKrankheitsbild() + "\', "
				+ "essgewohnheit = \'" + entry.getEssgewohnheit() + "\', "
				+ "auslauf = \'" + entry.getAuslauf() + "\', "
				+ "umgangTier = \'" + entry.getUmgangTier() + "\', "
				+ "umgangMensch = \'" + entry.getUmgangMensch() + "\', "
				+ "anmerkungen = \'" + entry.getAnmerkungen() + "\', "
				+ "zusatzkosten = " + entry.getZusatzkosten()
				+ " WHERE tierID = " + entry.getTierID() + ";";

		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		stmt.executeUpdate(sql);

	}

	/**
	 * Diese Methode löscht einen Tiereintrag aus der Tabelle tier
	 * 
	 * @param tier
	 *            Tiereintrag, welcher gelöscht werden soll
	 * @throws SQLException
	 */
	@Override
	public void delete(Tier entry) throws SQLException {
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
	public List<Tier> getList() throws SQLException {
		String sql;
		sql = "SELECT * FROM 'tier';";
		System.out.println(sql);

		Statement stmt = dbConnection.getConn().createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<Tier> tierarray = new ArrayList<Tier>();

		while (rs.next()) {
			char[] auslauf = rs.getString("auslauf").toCharArray();

			Tier tier = new Tier(rs.getInt("tierID"), rs.getInt("fkKunde"),
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

	@Override
	public Tier getEntry(int id) throws SQLException {
		String sql;
		sql = "SELECT * FROM 'tier' WHERE tierID = " + id + ";";
		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		;

		while (rs.next()) {
			char[] auslauf = rs.getString("auslauf").toCharArray();

			Tier tier = new Tier(rs.getInt("tierID"), rs.getInt("fkKunde"),
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

	public ArrayList<Tier> getTiereZuKunde(int kundeID) throws SQLException {
		String sql;
		sql = "SELECT * FROM 'tier' WHERE fkKunde = " + kundeID + ";";
		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<Tier> tierArray = new ArrayList<Tier>();

		while (rs.next()) {
			char[] auslauf = rs.getString("auslauf").toCharArray();

			Tier tier = new Tier(rs.getInt("tierID"), rs.getInt("fkKunde"),
					rs.getString("art"), rs.getString("rasse"),
					rs.getString("name"), rs.getInt("tieralter"),
					rs.getInt("groesseID"), rs.getString("krankheitsbild"),
					rs.getString("essgewohnheit"), auslauf[0],
					rs.getString("umgangTier"), rs.getString("umgangMensch"),
					rs.getString("anmerkungen"), rs.getDouble("zusatzkosten"));

			tierArray.add(tier);
		}

		return tierArray;

	}

	@Override
	public void save(Tier entry) throws SQLException, ValidationException {
		entry.validate();
		if (entry.getTierID() <= 0) {
			entry.setTierID(insert(entry));
		} else {
			update(entry);
		}

	}

}
