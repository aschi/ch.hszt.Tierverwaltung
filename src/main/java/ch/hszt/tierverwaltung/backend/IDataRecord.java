package ch.hszt.tierverwaltung.backend;

import java.sql.SQLException;

import com.sun.xml.internal.ws.developer.ValidationErrorHandler;


/**
 * Dieses Interface wird von allen DataRecords (Tier, Kunde, Aufenthalt, Tierplatz) implementiert und beinhaltet
 * die Methoden save und delete 
 * @author prisi
 *
 * @param <T> Ein dataRecord vom Typ Tier, Kunde, Tierplatz oder Aufenthalt
 */
public interface IDataRecord {
	
	/**
	 * Diese Methode speichert den DataRecord in die entsprechende Tabelle..
	 * @throws SQLException wenn der Datenbankzugriff fehlerhaft war
	 * @throws ValidationException wenn die Validierung, welche vor dem Speichern gemacht
	 * wird fehl schlug
	 */
	public void save() throws SQLException, ValidationException;
	
	/**
	 * Diese Methode löscht den DataRecord aus der entsprechenden Tabelle
	 * @throws SQLException wenn der Datenbankzugriff fehlerhaft war
	 * @throws ValidationException wenn die Validierung, welche vor dem Speichern gemacht
	 * wird fehl schlug
	 */
	public void delete() throws SQLException, ValidationException;
	
	/**
	 * Die Methode validate validiert die Eingaben welche von der Benutzerschnittstelle gesetzt wurden.
	 * Bei Fehlerhaften Eingaben wird eine Nachricht für den Benutzer lesbar aufbereitet und zurückgegeben.
	 * @throws ValidationException Wenn die validierung fehl schlug
	 */
	public void validate() throws ValidationException;

}
