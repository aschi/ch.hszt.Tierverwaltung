package ch.hszt.tierverwaltung.tier.backend.essstrategy;

import ch.hszt.tierverwaltung.tier.backend.Tierart;

public class EssVerhaltenFactory {

	public static EssStrategy getEssverhalten(Tierart art) {
		if(art == Tierart.Fisch){
			return new EssenFisch();
		}else if(art == Tierart.Hund){
			return new EssenHund();
		}else if(art == Tierart.Katze){
			return new EssenKatze();	
		}else{
			return null;
		}
	}

}
