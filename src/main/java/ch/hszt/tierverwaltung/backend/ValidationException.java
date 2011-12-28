package ch.hszt.tierverwaltung.backend;

import java.util.ArrayList;
import java.util.List;

/**
 * The ValidationException is thrown if a User-Input-Validation was not successful. 
 * It includes a message readable for the user
 * 
 * @author prisi
 *
 */
public class ValidationException extends Exception {

	private static final long serialVersionUID = 1L;
	private List<String> errorMsgs;

	/**
	 *  Constructor
 	*/
	public ValidationException() {
		super();
		errorMsgs = new ArrayList<String>();
	}

	/**
	 *  Adds a new ErrorMessage to the messageList
 	*/
	public void addErrorMessage(String errorMsg) {
		errorMsgs.add(errorMsg);
	}
	
	/**
	*  Returns the list with all errormessages
	* @return List includes all error messages
 	*/
	public List<String> getErrorMsgs() {
		return errorMsgs;
	}

	/**
	 * generates a message for a user
	 * @param e ValidationException
	 * @return message for user as String
	 */
	public static String createErrorMsg(ValidationException e) {
		String errMsg = "Error at Validation: "
				+ System.getProperty("line.separator");
		for (String err : e.getErrorMsgs()) {
			errMsg += "- " + err + System.getProperty("line.separator");
		}
		errMsg = errMsg.substring(0, errMsg.length() - 1);
		return errMsg;
	}
}