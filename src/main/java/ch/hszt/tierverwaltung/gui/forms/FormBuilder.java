package ch.hszt.tierverwaltung.gui.forms;

public class FormBuilder<T> {
	
	public void buildForm(T object){
		
		if(object.getClass().getSimpleName().equals("Tier")){
			new GuiTierEintrag();
		}else if(object.getClass().getSimpleName().equals("Kunde")){
			
		}
		
	}
}
