package ch.hszt.tierverwaltung.database.kunde;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import ch.hszt.tierverwaltung.database.ICreateDB;

/**
 * Diese Klasse erstellt die Tabelle Kunde. 
 * Achtung!!!!!! Sollten die entsprechende Tabelle bereits
 * vorhanden sein, wird diese gelöscht und neu erstellt. Deshalb sollte diese Klasse mit Bedacht ausgeführt werden.
 */
public class CreateKundeDB implements ICreateDB {



	/**
	 * Diese Methode erstellt die Tabelle Kunde. 
	 * Achtung!!!!!! Sollten die entsprechende Tabelle bereits
	 * vorhanden sein, wird diese gelöscht und neu erstellt. Deshalb sollte diese Klasse mit Bedacht ausgeführt werden.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * @throws Exception wenn die Connection mit der tierverwaltungs-Datenbank nicht erfolgreich ist
	 */
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
			String sql = "DROP TABLE 'kunde'";
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
    		String sql = "CREATE TABLE kunde "
    				+ " (kundeID integer PRIMARY KEY autoincrement, "
                                     +" name varchar(50), "
                                     +" vorname varchar(50), " 
                                     +" adresse varchar(100), " 
                                     +" plz varchar(10), "
                                     +" ort varchar(50),"
                                     +" telefon varchar(15),"
                                     +" eMail varchar(100))"
    				+";";
    		stmt.execute(sql);
        } finally {
             if (conn != null)
            	 conn.close();
        }
		
	}
	
    /**
     * Führt createTablesInTierverwaltungDatabase aus. Siehe Javadoc von createTablesInTierverwaltungDatabase.
     * @param args
     */
    public static void main(String[] args) {
		CreateKundeDB db = new CreateKundeDB();
		try {
			db.createTable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("fehlerli");
		}
	}
	
	
}