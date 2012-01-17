package ch.hszt.tierverwaltung.backend;



/**
 * This interface will be implemented from all data-records (pet, customer, stay, petplace)
 * It includes the methods getID and validate
 * 
 * @author prisi
 *
 * @param <T> a data-record of type pet, customer, stay or petplace
 */
public interface IDataRecord extends IPublicCloneable{
	
	/**
	 * This method returns the id of the data-record in the table. If the id is > 0, the
	 * object is saved in the table. if the id s <= 0, the object isn't saved in the database yet
	 * @return id
	 */
	public int getID();
	
	/**
	 * This method validates the input from the user-interface. if the validation isn't successful, a
	 * validation-exception will be thrown. The validation exception includes a message for the user.
	 * 
	 * @throws ValidationException if the validation was not successful
	 */
	public void validate() throws ValidationException;
}
