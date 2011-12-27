package ch.hszt.tierverwaltung.database.customer;

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
import ch.hszt.tierverwaltung.database.pet.PetDataMapper;

/**
 * 
 * @author prisi
 *
 */
public final class CustomerDataMapper implements IDataMapper<Customer> {

	DBConnection dbConnection = DBConnection.getInstance();

	/**
	 * Constructor
	 */
	public CustomerDataMapper() {

	}

	@Override
	public int insert(Customer entry) throws SQLException {

		String sql;
		ResultSet rs = null;
		sql = "INSERT INTO 'customer' VALUES (null, '" + entry.getName() + "', "
				+ "\'" + entry.getFirstName() + "\', \'" + entry.getAddress()
				+ "\', '" + entry.getZip() + "\', \'" + entry.getCity()
				+ "\', \'" + entry.getPhoneNo() + "\', \'" + entry.getEMail()
				+ "\');";
		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		stmt.executeUpdate(sql);

		PreparedStatement pstmt = dbConnection.getConn().prepareStatement(
				"select max(customerId) max from 'customer';");
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
		sql = "UPDATE 'customer' SET " + "name = \'" + entry.getName() + "\', "
				+ "firstName = '" + entry.getFirstName() + "', " + "address = \'"
				+ entry.getAddress() + "\', " + "zip = \'" + entry.getZip()
				+ "\', " + "city = \'" + entry.getCity() + "\', "
				+ "phoneNo = \'" + entry.getPhoneNo() + "\', " + "eMail = \'"
				+ entry.getEMail() + "\'" + 
				"WHERE customerId = " + entry.getID() + ";";

		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		stmt.executeUpdate(sql);

	}

	@Override
	public void delete(Customer entry) throws SQLException {
		//Beziehungen zum Tier entfernen
		for(Pet t : entry.getPets()){
			t.setFkCustomer(-1);
			try {
				new PetDataMapper().save(t);
			} catch (ValidationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		String sql;
		sql = "DELETE FROM 'customer' WHERE customerId = " + entry.getCustomerId() + ";";
		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		stmt.executeUpdate(sql);
	}


	@Override
	public List<Customer> getList() throws SQLException {
		String sql;
		sql = "SELECT * FROM 'customer';";
		System.out.println(sql);

		Statement stmt = dbConnection.getConn().createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<Customer> kundenarray = new ArrayList<Customer>();

		while (rs.next()) {

			Customer kunde = new Customer(rs.getInt("customerId"), rs.getString("name"),
					rs.getString("firstName"), rs.getString("address"),
					rs.getString("zip"), rs.getString("city"),
					rs.getString("phoneNo"), rs.getString("eMail"));

			kunde.setPets(new PetDataMapper().getPetsFromCustomer(kunde
					.getCustomerId()));

			kundenarray.add(kunde);
		}
		return kundenarray;
	}

	@Override
	public Customer getEntry(int id) throws SQLException {
		String sql;
		sql = "SELECT * FROM 'customer' WHERE customerId = " + id + ";";
		System.out.println(sql);
		Statement stmt = dbConnection.getConn().createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		;

		while (rs.next()) {

			Customer kunde = new Customer(rs.getInt("customerId"), rs.getString("name"),
					rs.getString("firstName"), rs.getString("address"),
					rs.getString("zip"), rs.getString("city"),
					rs.getString("phoneNo"), rs.getString("eMail"));

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
