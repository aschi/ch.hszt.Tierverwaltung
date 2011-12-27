package ch.hszt.tierverwaltung.database.customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import ch.hszt.tierverwaltung.database.ICreateDB;

/**
 * This class creates the table customer
 * Attention!!!!!! If the table already exist, it will be dropped and a new one will be created.
 */
public class CreateCustomerDB implements ICreateDB {



	@Override
	public void createTable() throws ClassNotFoundException, SQLException {

		Class.forName("org.sqlite.JDBC");

		
		Connection conn = DriverManager.getConnection("jdbc:sqlite:tierverwaltung.db");
		delete(conn);
		create(conn);
		
	}

	@Override
	public void delete(Connection conn) throws SQLException {
		try {
			Statement stmt = conn.createStatement();
			String sql = "DROP TABLE 'customer'";
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void create(Connection conn) throws SQLException {
    	try {
    		Statement stmt = conn.createStatement();
    		String sql = "CREATE TABLE customer "
    				+ " (customerId integer PRIMARY KEY autoincrement, "
                                     +" name varchar(50), "
                                     +" firstName varchar(50), " 
                                     +" address varchar(100), " 
                                     +" zip varchar(10), "
                                     +" city varchar(50),"
                                     +" phoneNo varchar(15),"
                                     +" eMail varchar(100))"
    				+";";
    		stmt.execute(sql);
        } finally {
             if (conn != null)
            	 conn.close();
        }
		
	}
	
    /**
     * calls CreateCustomerDB. 
     * @param args
     */
    public static void main(String[] args) {
		CreateCustomerDB db = new CreateCustomerDB();
		try {
			db.createTable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error");
		}
	}
	
	
}