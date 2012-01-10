package ch.hszt.tierverwaltung.database.pet;

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

/**
 * 
 *  * This class implements the IDataMapper-Interface. It handles all Dataaccess to table
 * pet
 * @author prisi
 *
 * @author prisi
 *
 */
public final class PetDataMapper implements IDataMapper<Pet> {

	DBConnection dbConnection = DBConnection.getInstance();

	/**
	 * Default Constructor
	 */
	public PetDataMapper() {

	}


	@Override
	public int insert(Pet entry) throws SQLException {
		String sql;
		ResultSet rs = null;
		sql = "INSERT INTO 'pet' VALUES (null, null, '" + entry.getSpecies()
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
				.prepareStatement("select max(petId) max from 'pet';");
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
		sql = "UPDATE 'pet' SET " + "fkCustomer = " + entry.getFkCustomer() + ", "
				+ "species = '" + entry.getSpecies() + "', " + "race = \'"
				+ entry.getRace() + "\', " + "name = \'" + entry.getName()
				+ "\', " + "age = " + entry.getAge() + ", "
				+ "sizeId = " + entry.getSizeId() + ", "
				+ "diseasePattern = \'" + entry.getDiseasePattern() + "\', "
				+ "eatingHabits = \'" + entry.getEatingHabits() + "\', "
				+ "run = \'" + entry.getRun() + "\', "
				+ "contactOtherPets = \'" + entry.getContactOtherPets() + "\', "
				+ "contactPeople = \'" + entry.getContactPeople() + "\', "
				+ "remarks = \'" + entry.getRemarks() + "\', "
				+ "additionalCosts = " + entry.getAdditionalCosts()
				+ " WHERE petId = " + entry.getPetId() + ";";

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
		sql = "SELECT * FROM 'pet' WHERE fkCustomer < 1 OR fkCustomer is null;";
		System.out.println(sql);

		Statement stmt = dbConnection.getConn().createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		return fillPets(rs);

	}
	

	//Returns an ArrayList with all Pets from the Resultset
	private ArrayList<Pet> fillPets(ResultSet rs) throws SQLException {
		ArrayList<Pet> tierarray = new ArrayList<Pet>();
		
		while (rs.next()) {
			char[] run = rs.getString("run").toCharArray();

			Pet tier = new Pet(rs.getInt("petId"), rs.getInt("fkCustomer"),
					rs.getString("species"), rs.getString("race"),
					rs.getString("name"), rs.getInt("age"),
					rs.getInt("sizeId"), rs.getString("diseasePattern"),
					rs.getString("eatingHabits"), run[0],
					rs.getString("contactOtherPets"), rs.getString("contactPeople"),
					rs.getString("remarks"), rs.getDouble("additionalCosts"));

			tierarray.add(tier);
		}
		
		return tierarray;
	}

	@Override
	public void delete(Pet entry) throws SQLException {
		String sql;
		sql = "DELETE FROM 'pet' WHERE petId = " + entry.getPetId() + ";";
		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		stmt.executeUpdate(sql);
	}

	@Override
	public List<Pet> getList() throws SQLException {
		String sql;
		sql = "SELECT * FROM 'pet';";
		System.out.println(sql);

		Statement stmt = dbConnection.getConn().createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		return fillPets(rs);
	}

	@Override
	public Pet getEntry(int id) throws SQLException {
		String sql;
		sql = "SELECT * FROM 'pet' WHERE petId = " + id + ";";
		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		;

		while (rs.next()) {
			char[] run = rs.getString("run").toCharArray();

			Pet tier = new Pet(rs.getInt("petId"), rs.getInt("fkCustomer"),
					rs.getString("species"), rs.getString("race"),
					rs.getString("name"), rs.getInt("age"),
					rs.getInt("sizeId"), rs.getString("diseasePattern"),
					rs.getString("eatingHabits"), run[0],
					rs.getString("contactOtherPets"), rs.getString("contactPeople"),
					rs.getString("remarks"), rs.getDouble("additionalCosts"));

			return tier;
		}

		return null;
	}

	/**
	 * Returns a list with all pets which belongs the the given customer
	 * @param customerId the given customer
	 * @return a List with all Pets which belongs to the given customer
	 * @throws SQLException
	 */
	public ArrayList<Pet> getPetsFromCustomer(int customerId) throws SQLException {
		String sql;
		sql = "SELECT * FROM 'pet' WHERE fkCustomer = " + customerId + ";";
		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		return fillPets(rs);

	}

	@Override
	public void save(Pet entry) throws SQLException, ValidationException {
		entry.validate();
		if (entry.getPetId() <= 0) {
			entry.setPetID(insert(entry));
		} else {
			update(entry);
		}

	}

}
