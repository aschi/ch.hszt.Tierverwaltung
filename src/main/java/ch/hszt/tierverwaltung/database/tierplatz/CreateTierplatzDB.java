package ch.hszt.tierverwaltung.database.tierplatz;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import ch.hszt.tierverwaltung.database.ICreateDB;

/**
 * Diese Klasse erstellt die Tabelle Tierplatz. 
 * Achtung!!!!!! Sollten die entsprechende Tabelle bereits
 * vorhanden sein, wird diese gelöscht und neu erstellt. Deshalb sollte diese Klasse mit Bedacht ausgeführt werden.
 */
public class CreateTierplatzDB implements ICreateDB {



	/**
	 * Diese Methode erstellt die Tabelle Tierplatz. 
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
			String sql = "DROP TABLE 'tierplatz'";
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
    		String sql = "CREATE TABLE tierplatz "
    				+ " (tierplatzID integer PRIMARY KEY autoincrement, "
    								 +" geeignetFuerTierID integer,"
                                     +" groesse integer, "
                                     +" austattung varchar(150), " 
                                     +" anzahlTiere integer, " 
                                     +" auslauf char(1), " 
                                     +" auslaufGroesse integer)"
    				+";";
    		stmt.execute(sql);;
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
		CreateTierplatzDB db = new CreateTierplatzDB();
		try {
			db.createTable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("fehlerli");
		}
	}
	
	
}