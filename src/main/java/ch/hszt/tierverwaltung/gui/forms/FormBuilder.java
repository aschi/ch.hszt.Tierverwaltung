package ch.hszt.tierverwaltung.gui.forms;

import ch.hszt.tierverwaltung.backend.IPublicCloneable;
import ch.hszt.tierverwaltung.kunden.backend.Kunde;
import ch.hszt.tierverwaltung.tier.backend.Tier;

public class FormBuilder<T extends IPublicCloneable> {
	/**
	 * buildForm
	 * 
	 * @param object
	 */
	public void buildForm(T object){
		
		if(object instanceof Tier){
			new GuiTierEintrag((Tier)object);
		}else if(object instanceof Kunde){
			//new GuiKundenEintrag((Kunde)object);
		}else{
			System.out.println("NYI");
		}
		
	}
}
