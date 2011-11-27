package ch.hszt.tierverwaltung.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface ICreateDB {
	
	/**
	 * Führt die Methoden delete() und creatae() aus.
	 * Somit wird die bestehende Tabelle gedroppt, und die Tabelle neu erstellt.
	 */
	public void createTable() throws ClassNotFoundException, SQLException;
	
	/**
	 * Dropt die Tabelle
	 * @throws SQLException
	 */
	abstract  void delete(Connection conn) throws SQLException;
	/**
	 * Erstellt die Tabelle neu
	 * @throws SQLException
	 */
	abstract void create(Connection conn) throws SQLException;
}
