package ch.hszt.tierverwaltung.gui.forms;

import ch.hszt.tierverwaltung.tier.backend.Tier;

public class FormBuilder<T> {
	
	public void buildForm(T object){
		
		if(object instanceof Tier){
			new GuiTierEintrag((Tier)object);
		}else if(object.getClass().getSimpleName().equals("Kunde")){
			
		}
		
	}
}
