package ch.hszt.tierverwaltung.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ch.hszt.tierverwaltung.tier.backend.Tier;

final class TierDatabaseAccess implements IDatabaseAccess<Tier> {

	private Connection conn;

	public TierDatabaseAccess() {
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
	 * 
	 * @param tier Tiereintrag welcher in die Datenbank eingefügt werden soll
	 * @throws SQLException
	 */
	@Override
	public void insert(Tier entry) throws SQLException{
		String sql;
		sql = "INSERT INTO 'tier' VALUES (null, '" + entry.getArt() + "', "
				+ "\'" + entry.getRasse() + "\', \'" + entry.getName() + "\', "
				+ entry.getTieralter() + ", " + entry.getGroesseID() + ", \'"
				+ entry.getKrankheitsbild() + "\', \'" + entry.getEssgewohnheit()
				+ "\', \'" + entry.getAuslauf() + "\', \'"
				+ entry.getUmgangTier() + "\', \'" + entry.getUmgangMensch()
				+ "\', \'" + entry.getAnmerkungen() + "\', "
				+ entry.getZusatzkosten() + ");";
		System.out.println(sql);
		Statement stmt = conn.createStatement();
		stmt.execute(sql);

	}

	@Override
	public void update(Tier entry) throws SQLException{
		// TODO Auto-generated method stub

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
		sql = "DELETE FROM 'tier' t " + "WHERE t.art = \'" + entry.getArt()
				+ "\' and" + " t.rassse = \'" + entry.getRasse() + "\' and"
				+ " t.name = \'" + entry.getName() + "\' and"
				+ " t.tieralter = " + entry.getTieralter() + " and "
				+ " t.groesseID = " + entry.getGroesseID() + " and"
				+ " t.krankheitsbild = \'" + entry.getKrankheitsbild()
				+ "\' and" + " t.essgewohnheit = \'" + entry.getEssgewohnheit()
				+ "\' and" + " t.auslauf = \'" + entry.getAuslauf() + "\' and"
				+ " t.umgangTier = \'" + entry.getUmgangTier() + "\' and"
				+ " t.umgangMensch = \'" + entry.getUmgangMensch() + "\' and"
				+ " t.anmerkungen = \'" + entry.getAnmerkungen() + "\' and"
				+ " t.zusatzkosten= \'" + entry.getZusatzkosten() + "\' ;";
		System.out.println(sql);
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
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

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<Tier> tierarray = new ArrayList<Tier>();

		while (rs.next()) {
			char[] auslauf = rs.getString("auslauf").toCharArray();

			Tier tier = new Tier(rs.getString("art"), rs.getString("rasse"),
					rs.getString("name"), rs.getInt("tieralter"),
					rs.getInt("groesseID"), rs.getString("krankheitsbild"),
					rs.getString("essgewohnheit"), auslauf[0],
					rs.getString("umgangTier"), rs.getString("umgangMensch"),
					rs.getString("anmerkungen"), rs.getDouble("zusatzkosten"));

			tierarray.add(tier);
		}

		rs.close();
		conn.close();

		return tierarray;
	}

	@Override
	public Tier getEntry(int id) throws SQLException{
		// TODO Auto-generated method stub
		return null;
	}

}
