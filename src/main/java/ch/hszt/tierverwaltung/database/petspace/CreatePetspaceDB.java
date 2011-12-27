package ch.hszt.tierverwaltung.database.petspace;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import ch.hszt.tierverwaltung.database.ICreateDB;

/**
 * This class creates the table petspace
 * Attention!!!!!! If the table already exist, it will be dropped and a new one will be created.
 */
public class CreatePetspaceDB implements ICreateDB {

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
			String sql = "DROP TABLE 'petspace'";
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
    		String sql = "CREATE TABLE petspace "
    				+ " (petspaceId integer PRIMARY KEY autoincrement, "
    								 +" adaptedForPetID integer,"
                                     +" size integer, "
                                     +" equipment varchar(150), " 
                                     +" noOfPets integer, " 
                                     +" run char(1), " 
                                     +" runSize integer)"
    				+";";
    		stmt.execute(sql);;
        } finally {
             if (conn != null)
            	 conn.close();
        }
		
	}
	
    /**
     * Calls CreatePetspaceDB
     * @param args
     */
    public static void main(String[] args) {
		CreatePetspaceDB db = new CreatePetspaceDB();
		try {
			db.createTable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("fehlerli");
		}
	}
	
	
}