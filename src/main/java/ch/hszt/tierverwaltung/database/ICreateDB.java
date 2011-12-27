package ch.hszt.tierverwaltung.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface ICreateDB {
	
	/**
	 * Runs the methods delete() and create() to create the new tables.
	 * the current tables will be deleted and built new.
	 */
	public void createTable() throws ClassNotFoundException, SQLException;
	
	/**
	 * Drops the table
	 * @throws SQLException
	 */
	abstract  void delete(Connection conn) throws SQLException;
	
	/**
	 * creates the new table
	 * @throws SQLException
	 */
	abstract void create(Connection conn) throws SQLException;
}
