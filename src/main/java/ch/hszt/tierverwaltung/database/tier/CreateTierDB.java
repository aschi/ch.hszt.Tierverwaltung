package ch.hszt.tierverwaltung.database.tier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import ch.hszt.tierverwaltung.database.ICreateDB;

/**
 * Diese Klasse erstellt die Tabelle Tier. 
 * Achtung!!!!!! Sollten die entsprechende Tabelle bereits
 * vorhanden sein, wird diese gelöscht und neu erstellt. Deshalb sollte diese Klasse mit Bedacht ausgeführt werden.
 */
public class CreateTierDB implements ICreateDB {



	/**
	 * Diese Methode erstellt die Tabelle Tier. 
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
			String sql = "DROP TABLE 'tier'";
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
    		String sql = "CREATE TABLE tier "
    				+ " (tierID integer PRIMARY KEY autoincrement, "
    								 +" fkKunde integer,"
                                     +" art varchar(100), "
                                     +" rasse varchar(100), " 
                                     +" name varchar(255), " 
                                     +" tieralter integer(3), " 
                                     +" groesseID integer(3),"
                                     +" krankheitsbild varchar(300),"
                                     +" essgewohnheit varchar(200),"
                                     +" auslauf char(1),"
                                     +" umgangTier varchar(500),"
                                     +" umgangMensch varchar(500),"
                                     +" anmerkungen varchar(400),"
                                     +" zusatzkosten real)"
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
		CreateTierDB db = new CreateTierDB();
		try {
			db.createTable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("fehlerli");
		}
	}
	
	
}