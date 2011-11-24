package DBHandling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

final class DBConnection {
	
	final Connection conn;

	public DBConnection() throws ClassNotFoundException, SQLException {

		Class.forName("org.sqlite.JDBC");

		 conn = DriverManager
					.getConnection("jdbc:sqlite:tierverwaltung.db");
		
	}

	public Connection getConn() {
		return conn;
	}
	
	

}
