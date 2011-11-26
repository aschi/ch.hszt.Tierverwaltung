package ch.hszt.tierverwaltung.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {

	/**
	 * Diese Klasse erstellt die Tabellen Tier, Tierplatz, und Kunde. 
	 * Achtung!!!!!! Sollten die entsprechenden Tabellen bereits
	 * vorhanden sein, werden diese überschrieben. Deshalb sollte diese Klasse mit Bedacht ausgeführt werden.
	 * @throws Exception wenn die Connection mit der tierverwaltungs-Datenbank nicht erfolgreich ist
	 */
	public void createTablesInTierverwaltungDatabase() throws Exception {

		Class.forName("org.sqlite.JDBC");

		
		Connection conn = DriverManager.getConnection("jdbc:sqlite:tierverwaltung.db");
		deleteTableTier(conn);
		createTierTable(conn);
		deleteTableKunde(conn);
		createKundeTable(conn);
		deleteTableTierplatz(conn);
		createTierplatzTable(conn);
		
	}
	
	private void deleteTableTier(Connection conn) {
		
		try {
			Statement stmt = conn.createStatement();
			String sql = "DROP TABLE 'tier'";
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void deleteTableKunde(Connection conn) {
		
	}
	
	private void deleteTableTierplatz(Connection conn) {
		
	}
	
	private void createKundeTable(Connection conn) {
		
	}
	
	private void createTierplatzTable(Connection conn) {
		
	}
	
    private void createTierTable(Connection conn) throws SQLException
    {
    	try {
    		Statement stmt = conn.createStatement();
    		String sql = "CREATE TABLE tier "
    				+ " (tierID integer PRIMARY KEY autoincrement, "
    								 +" fkKunde integer,"
    								 +" fkAufenthalt integer(3),"
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
		CreateDB db = new CreateDB();
		try {
			db.createTablesInTierverwaltungDatabase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("fehlerli");
		}
	}
	
	
}