package ch.hszt.tierverwaltung.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * An Instance of the database-connection from tierverwaltung.db
 * @author prisi
 *
 */
public class DBConnection {
	
	private static DBConnection instance;

	private Connection conn;
	
	private DBConnection() {
		openConnection();
	}
	
	/**
	 * returns an instance of this class
	 * @return instance of this class
	 */
	public static synchronized DBConnection getInstance() {
		if (instance == null) {
			instance = new DBConnection();
		}
		return instance;
	}
	
	private void openConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:tierverwaltung.db");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	private void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	/**
	 * returns the connection to the database tierverwaltung.db
	 * @return
	 */
	public Connection getConn() {
		return conn;
	}
	
	

}
