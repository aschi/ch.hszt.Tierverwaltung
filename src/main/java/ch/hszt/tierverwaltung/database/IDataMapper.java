package ch.hszt.tierverwaltung.database;

import java.sql.SQLException;
import java.util.List;

import ch.hszt.tierverwaltung.backend.ValidationException;
import ch.hszt.tierverwaltung.kunden.backend.Kunde;

public interface IDataMapper <T> {
	
	
	/**
	 * Diese Methode speichert den DataRecord in die entsprechende Tabelle.
	 * Sie unterscheidet selbstständig, ob der DataRecord bereits in der Tabelle ist (UPDATE)
	 * oder ob der DataRecord eingefügt werden muss (INSERT). 
	 * @throws SQLException wenn der Datenbankzugriff fehlerhaft war
	 * @throws ValidationException wenn die Validierung, welche vor dem Speichern gemacht
	 * wird fehl schlug
	 */
	public void save(T entry) throws SQLException, ValidationException;
	
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
