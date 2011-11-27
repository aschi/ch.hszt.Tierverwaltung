package ch.hszt.tierverwaltung.database;

import java.sql.SQLException;
import java.util.List;

public interface IDatabaseAccess <T> {
	
	/**
	 * Fügt einen neuen Satz in die Tabelle ein
	 * @param entry
	 * @return id ID des neuen Satzes in der Tabelle
	 * @throws SQLException
	 */
	public int insert(T entry) throws SQLException;
	/**
	 * Macht einen UPDATE auf dem entsprechenden Satz inder Tabelle
	 * @param entry
	 * @throws SQLException
	 */
	public void update(T entry) throws SQLException;
	/**
	 * Löscht den entry aus der Tabelle
	 * @param entry
	 * @throws SQLException
	 */
	public void delete(T entry) throws SQLException;
	/**
	 * Selektiert alle Einträge der Tabelle, und liefert diese als Liste zurück
	 * @return
	 * @throws SQLException
	 */
	public List<T> getList() throws SQLException;
	/**
	 * Ermittelt anhand der ID den Datensatz aus der Tabelle
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public T getEntry(int id) throws SQLException;
}
