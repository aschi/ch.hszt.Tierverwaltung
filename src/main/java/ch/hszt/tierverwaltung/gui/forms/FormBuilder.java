package ch.hszt.tierverwaltung.gui.forms;

import ch.hszt.tierverwaltung.backend.IPublicCloneable;
import ch.hszt.tierverwaltung.backend.Kunde;
import ch.hszt.tierverwaltung.backend.Tier;
import ch.hszt.tierverwaltung.backend.Tierplatz;
import ch.hszt.tierverwaltung.gui.MainGui;

public class FormBuilder<T extends IPublicCloneable> {
	/**
	 * Öffnet das gegebene Objekt im dazugehörigen Formular.
	 * Falls für das entsprechende Objekt kein Formular konfiguriert ist, gibt die Funktion "NYI" (not yet implemented) auf der Konsole aus
	 * @param object Das zu öffnende Objekt
	 * @param gui Referenz zum Hauptfenster
	 */
	public void buildForm(T object, MainGui gui){
		
		if(object instanceof Tier){
			new GuiTierEintrag((Tier)object, gui);
		}else if(object instanceof Kunde){
			new GuiKundenEintrag((Kunde)object, gui);
		}else if(object instanceof Tierplatz){
			new GuiTierplatzEintrag((Tierplatz)object, gui);
		}else{
			System.out.println("NYI");
		}
		
	}
}
