package ch.hszt.tierverwaltung.backend;

import java.sql.SQLException;


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
	 */
	public void save() throws SQLException;
	
	/**
	 * Diese Methode löscht den DataRecord aus der entsprechenden Tabelle
	 * @throws SQLException wenn der Datenbankzugriff fehlerhaft war
	 */
	public void delete() throws SQLException;

}
