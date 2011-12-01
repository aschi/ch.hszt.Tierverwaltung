package ch.hszt.tierverwaltung.backend;

import java.sql.SQLException;


/**
 * Dieses Interface wird von allen DataRecords (Tier, Kunde, Aufenthalt, Tierplatz) implementiert und beinhaltet
 * die Methoden save und delete 
 * @author prisi
 *
 * @param <T> Ein dataRecord vom Typ Tier, Kunde, Tierplatz oder Aufenthalt
 */
public interface IDataRecord extends IPublicCloneable{
	
	/**
	 * Diese Methode speichert den DataRecord in die entsprechende Tabelle.
	 * Sie unterscheidet selbstständig, ob der DataRecord bereits in der Tabelle ist (UPDATE)
	 * oder ob der DataRecord eingefügt werden muss (INSERT). 
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
	public void delete() throws SQLException;
	
	/**
	 * Die Methode validate validiert die Eingaben welche von der Benutzerschnittstelle gesetzt wurden.
	 * Bei Fehlerhaften Eingaben wird eine Nachricht für den Benutzer lesbar aufbereitet und kann auf 
	 * der Exception selber aufgerufen werden.
	 * @throws ValidationException Wenn die validierung fehl schlug
	 */
	public void validate() throws ValidationException;

}
