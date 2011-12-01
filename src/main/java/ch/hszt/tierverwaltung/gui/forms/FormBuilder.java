package ch.hszt.tierverwaltung.gui.forms;

import ch.hszt.tierverwaltung.backend.IPublicCloneable;
import ch.hszt.tierverwaltung.backend.Kunde;
import ch.hszt.tierverwaltung.backend.Tier;
import ch.hszt.tierverwaltung.gui.MainGui;

public class FormBuilder<T extends IPublicCloneable> {
	/**
	 * buildForm
	 * 
	 * @param object
	 */
	public void buildForm(T object, MainGui gui){
		
		if(object instanceof Tier){
			new GuiTierEintrag((Tier)object, gui);
		}else if(object instanceof Kunde){
			new GuiKundenEintrag((Kunde)object, gui);
		}else{
			System.out.println("NYI");
		}
		
	}
}
