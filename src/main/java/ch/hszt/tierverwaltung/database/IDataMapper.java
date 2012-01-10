package ch.hszt.tierverwaltung.database;

import java.sql.SQLException;
import java.util.List;

import ch.hszt.tierverwaltung.backend.ValidationException;

/**
 * This Interface handles all tableaccess from each table with objects <T>
 * @author prisi
 *
 * @param <T> object which is a entry of the table
 */
public interface IDataMapper <T> {
	
	
	/**
	 * This method saves a data-record into the table.
	 * The method decides by his own, if the data-record is still in the table (UPDATE) or if the data-record 
	 * is new (INSERT)
	 * @throws SQLException if database-access was not successful
	 * @throws ValidationException if the validation was not successful
	 */
	public void save(T entry) throws SQLException, ValidationException;
	
	/**
	 * Inserts a new enty into the table
	 * @param entry entry to be inserted
	 * @return id the id from the new data-record in the table
	 * @throws SQLException
	 */
	public int insert(T entry) throws SQLException;
	
	/**
	 * Updates the given entry in the table
	 * @param entry to be updated
	 * @throws SQLException
	 */
	public void update(T entry) throws SQLException;
	
	/**
	 * Deletes the given entry from the table
	 * @param entry to be deleted
	 * @throws SQLException
	 */
	public void delete(T entry) throws SQLException;
	
	/**
	 * Selects all entries in the table, returns a list including all entries
	 * @return List including all entries from table
	 * @throws SQLException
	 */
	public List<T> getList() throws SQLException;
	
	/**
	 * Selects the data-record with the given id from the table
	 * @param id of the record which is looked for
	 * @return the entry which belongs to the given id
	 * @throws SQLException
	 */
	public T getEntry(int id) throws SQLException;
}
