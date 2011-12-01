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
	 * Diese Methode liefert die Datenbank-ID des DataRecords zurück. Ist sie > 0, ist
	 * das Objekt in der Datenbank abgelegt. Ist sie <= 0, so ist das Objekt noch nicht in der
	 * Datenbank enthalten.
	 * @return id
	 */
	public int getID();
	
	/**
	 * Die Methode validate validiert die Eingaben welche von der Benutzerschnittstelle gesetzt wurden.
	 * Bei Fehlerhaften Eingaben wird eine Nachricht für den Benutzer lesbar aufbereitet und kann auf 
	 * der Exception selber aufgerufen werden.
	 * @throws ValidationException Wenn die validierung fehl schlug
	 */
	public void validate() throws ValidationException;

}
