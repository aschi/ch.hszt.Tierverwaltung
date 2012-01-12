package ch.hszt.tierverwaltung.database.stay;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ch.hszt.tierverwaltung.backend.Stay;
import ch.hszt.tierverwaltung.backend.ValidationException;
import ch.hszt.tierverwaltung.database.DBConnection;
import ch.hszt.tierverwaltung.database.IDataMapper;

/**
 * 
 *  * This class implements the IDataMapper-Interface. It handles all Dataaccess to table
 * stay
 * @author prisi
 *
 */
public final class StayDataMapper implements IDataMapper<Stay> {

	DBConnection dbConnection = DBConnection.getInstance();

	/**
	 * Default Constructor
	 */
	public StayDataMapper() {

	}


	@Override
	public int insert(Stay entry) throws SQLException {
		String sql;
		ResultSet rs = null;
		sql = "INSERT INTO 'stay' VALUES (null, " + entry.getPetId() + ", " + 
				entry.getPetspaceId() + "," + entry.getDateFrom().getTime() + ", " + entry.getDateTo().getTime() + ");";
		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		stmt.executeUpdate(sql);

		PreparedStatement pstmt = dbConnection.getConn()
				.prepareStatement("select max(stayId) max from 'stay';");
		rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("max");
		} else {
			return 0;
		}

	}

	@Override
	public void update(Stay entry) throws SQLException {
		String sql;
		sql = "UPDATE 'stay' SET " 
				+ "fkPet = " + entry.getPetId() + ", "
				+ "fkPetspace = " + entry.getPetspaceId() + ", " 
				+ "dateFrom = "	+ entry.getDateFrom().getTime() + ", " 
				+ "dateTo = " + entry.getDateTo().getTime()
				+ " WHERE stayId = " + entry.getID() + ";";

		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		stmt.executeUpdate(sql);

	}
	

	//Returns an ArrayList with all Stays from the Resultset
	private ArrayList<Stay> fillStays(ResultSet rs) throws SQLException {
		ArrayList<Stay> stayArray = new ArrayList<Stay>();
		
		while (rs.next()) {

			Stay stay = new Stay(rs.getInt("stayId"), rs.getInt("fkPet"),
					rs.getInt("fkPetspace"), new Date(rs.getTimestamp("dateFrom").getTime()),
					new Date(rs.getTimestamp("DateTo").getTime()));

			stayArray.add(stay);
		}
		
		return stayArray;
	}

	@Override
	public void delete(Stay entry) throws SQLException {
		String sql;
		sql = "DELETE FROM 'stay' WHERE stayId = " + entry.getID() + ";";
		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		stmt.executeUpdate(sql);
	}

	@Override
	public List<Stay> getList() throws SQLException {
		String sql;
		sql = "SELECT * FROM 'stay';";
		System.out.println(sql);

		Statement stmt = dbConnection.getConn().createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		return fillStays(rs);
	}

	@Override
	public Stay getEntry(int id) throws SQLException {
		String sql;
		sql = "SELECT * FROM 'stay' WHERE stayId = " + id + ";";
		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		;

		while (rs.next()) {

			Stay stay = new Stay(rs.getInt("stayId"), rs.getInt("fkPet"),
					rs.getInt("fkPetspace"), new Date(rs.getTimestamp("dateFrom").getTime()),
					new Date(rs.getTimestamp("DateTo").getTime()));

			return stay;
		}

		return null;
	}

	@Override
	public void save(Stay entry) throws SQLException, ValidationException {
		entry.validate();
		if (entry.getID() <= 0) {
			entry.setID(insert(entry));
		} else {
			update(entry);
		}

	}
	
	/**
	 * Returns a List with all Stays to a given petspace
	 * @param petspaceId
	 * @return List with the stays
	 * @throws SQLException
	 */
	public List<Stay> getStaysToSpace(int petspaceId) throws SQLException {
		List<Stay> stays = null;
		
		String sql;
		sql = "SELECT * FROM 'stay' WHERE fkPetspace = " + petspaceId + ";";
		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		stays = fillStays(rs);
		
		return stays;
	}

}
