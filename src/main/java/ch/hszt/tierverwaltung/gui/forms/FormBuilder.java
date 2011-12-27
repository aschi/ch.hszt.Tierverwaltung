package ch.hszt.tierverwaltung.gui.forms;

import ch.hszt.tierverwaltung.backend.IPublicCloneable;
import ch.hszt.tierverwaltung.backend.Customer;
import ch.hszt.tierverwaltung.backend.Pet;
import ch.hszt.tierverwaltung.backend.Petplace;
import ch.hszt.tierverwaltung.gui.MainGui;

public class FormBuilder<T extends IPublicCloneable> {
	/**
	 * Öffnet das gegebene Objekt im dazugehörigen Formular.
	 * Falls für das entsprechende Objekt kein Formular konfiguriert ist, gibt die Funktion "NYI" (not yet implemented) auf der Konsole aus
	 * @param object Das zu öffnende Objekt
	 * @param gui Referenz zum Hauptfenster
	 */
	public void buildForm(T object, MainGui gui){
		
		if(object instanceof Pet){
			new GuiPetEntry((Pet)object, gui);
		}else if(object instanceof Customer){
			new GuiCustomerEntry((Customer)object, gui);
		}else if(object instanceof Petplace){
			new GuiPetplaceEntry((Petplace)object, gui);
		}else{
			System.out.println("NYI");
		}
		
	}
}
