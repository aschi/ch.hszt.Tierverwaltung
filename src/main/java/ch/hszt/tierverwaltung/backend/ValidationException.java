package ch.hszt.tierverwaltung.backend;

import java.util.ArrayList;
import java.util.List;

/**
 * Die ValidationException wird dann geworden, wenn eine Benutzeeingabe  bei der Validierung
 * durchgefallen ist. Sie gibt die entsprechende Meldungen der fehlerhaften Validierungen zurück.
 * @author prisi
 *
 */
public class ValidationException extends Exception {

	private static final long serialVersionUID = 1L;
	private List<String> errorMsgs;

	public ValidationException() {
		super();
		errorMsgs = new ArrayList<String>();
	}

	public void addErrorMessage(String errorMsg) {
		errorMsgs.add(errorMsg);
	}
	
	public List<String> getErrorMsgs() {
		return errorMsgs;
	}

	/**
	 * Generiert eine Benutzerlesbare Message
	 * @param e ValidationException
	 * @return Für den Benutzer lesbare Exceptionnachricht
	 */
	public static String createErrorMsg(ValidationException e) {
		String errMsg = "Fehler beim Validieren der Eingaben: "
				+ System.getProperty("line.separator");
		for (String err : e.getErrorMsgs()) {
			errMsg += "- " + err + System.getProperty("line.separator");
		}
		errMsg = errMsg.substring(0, errMsg.length() - 1);
		return errMsg;
	}
}