package ch.hszt.tierverwaltung.database.pet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import ch.hszt.tierverwaltung.database.ICreateDB;

/**
 * This class creates the table pet
 * Attention!!!!!! If the table already exist, it will be dropped and a new one will be created.
 */
public class CreatePetDB implements ICreateDB {


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
			String sql = "DROP TABLE 'pet'";
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
    		String sql = "CREATE TABLE pet "
    				+ " (petId integer PRIMARY KEY autoincrement, "
    								 +" fkCustomer integer,"
                                     +" species varchar(100), "
                                     +" race varchar(100), " 
                                     +" name varchar(255), " 
                                     +" age integer(3), " 
                                     +" sizeId integer(3),"
                                     +" diseasePattern varchar(300),"
                                     +" eatingHabits varchar(200),"
                                     +" run char(1),"
                                     +" contactOtherPets varchar(500),"
                                     +" contactPeople varchar(500),"
                                     +" remarks varchar(400),"
                                     +" additionalCosts real)"
    				+";";
    		stmt.execute(sql);;
        } finally {
             if (conn != null)
            	 conn.close();
        }
		
	}
	
    /**
     * calls CreatePetDB
     * @param args
     */
    public static void main(String[] args) {
		CreatePetDB db = new CreatePetDB();
		try {
			db.createTable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("fehlerli");
		}
	}
	
	
}