package ch.hszt.tierverwaltung.database.petspace;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ch.hszt.tierverwaltung.backend.Petspace;
import ch.hszt.tierverwaltung.backend.ValidationException;
import ch.hszt.tierverwaltung.database.DBConnection;
import ch.hszt.tierverwaltung.database.IDataMapper;

/**
 * This class implements the IDataMapper-Interface. It handles all Dataaccess to table
 * petspace
 * @author prisi
 *
 */
public final class PetspaceDataMapper implements IDataMapper<Petspace> {

	DBConnection dbConnection = DBConnection.getInstance();

	/**
	 * Default Constructor
	 */
	public PetspaceDataMapper() {

	}

	@Override
	public int insert(Petspace entry) throws SQLException {
		String sql;
		ResultSet rs = null;
		sql = "INSERT INTO 'petspace' VALUES (null, " + entry.getAdaptedForPetID()
				+ ", " + entry.getSize() + ", \'" + entry.getEquipment()
				+ "\', " + entry.getNoOfPets() + ", \'" + entry.getRun()
				+ "\', " + entry.getRunSize() + ");";
		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		stmt.executeUpdate(sql);

		PreparedStatement pstmt = dbConnection.getConn()
				.prepareStatement("select max(petspaceId) max from 'petspace';");
		rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("max");
		} else {
			return 0;
		}

	}

	@Override
	public void update(Petspace entry) throws SQLException {
		String sql;
		sql = "UPDATE 'petspace' SET " 
				+ "adaptedForPetID = " + entry.getAdaptedForPetID() + ", "
				+ "size = " + entry.getSize() + ", " 
				+ "equipment = \'"	+ entry.getEquipment() + "\', " 
				+ "noOfPets = " + entry.getNoOfPets() + ", " 
				+ "run = \'" + entry.getRun() + "\', "
				+ "runSize = " + entry.getRunSize() + " "
				+ "WHERE petspaceId = " + entry.getID() + ";";

		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		stmt.executeUpdate(sql);

	}
	

	//Returns an ArrayList with all petplaces from the Resultset
	private ArrayList<Petspace> fillPetplaces(ResultSet rs) throws SQLException {
		ArrayList<Petspace> petArray = new ArrayList<Petspace>();

		while (rs.next()) {
			char[] run = rs.getString("run").toCharArray();

			Petspace petspace = new Petspace(rs.getInt("adaptedForPetID"),
					rs.getInt("size"), rs.getString("equipment"),
					rs.getInt("noOfPets"), run[0], rs.getInt("runSize"), rs.getInt("petspaceId"));

			petArray.add(petspace);
		}
		
		return petArray;
	}

	@Override
	public void delete(Petspace entry) throws SQLException {
		String sql;
		sql = "DELETE FROM 'petspace' WHERE petspaceId = " + entry.getID() + ";";
		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		stmt.executeUpdate(sql);
	}

	@Override
	public List<Petspace> getList() throws SQLException {
		String sql;
		sql = "SELECT * FROM 'petspace';";
		System.out.println(sql);

		Statement stmt = dbConnection.getConn().createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		return fillPetplaces(rs);
	}

	@Override
	public Petspace getEntry(int id) throws SQLException {
		String sql;
		sql = "SELECT * FROM 'petspace' WHERE petspaceId = " + id + ";";
		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		;

		while (rs.next()) {
			char[] run = rs.getString("run").toCharArray();

			Petspace petspace = new Petspace(rs.getInt("adaptedForPetID"),
					rs.getInt("size"), rs.getString("equipment"),
					rs.getInt("noOfPets"), run[0], rs.getInt("runSize"), rs.getInt("petspaceId"));

			return petspace;
		}

		return null;
	}

	@Override
	public void save(Petspace entry) throws SQLException, ValidationException {
		entry.validate();
		if (entry.getID() <= 0) {
			entry.setID(insert(entry));
		} else {
			update(entry);
		}

	}

}
