package ch.hszt.tierverwaltung.tier.backend;

import ch.hszt.tierverwaltung.tier.backend.essstrategy.EssStrategy;
import ch.hszt.tierverwaltung.tier.backend.essstrategy.EssVerhaltenFactory;

public class Tier {
	private Tierart art;
	private EssStrategy essVerhalten;
	
	Tier(Tierart art){
		this.art = art;
		this.essVerhalten = EssVerhaltenFactory.getEssverhalten(art);
	}
	
	public void setArt(Tierart art){
		this.art = art;
		this.essVerhalten = EssVerhaltenFactory.getEssverhalten(art);
	}
	
	public Tierart getArt(){
		return art;
	}
	
	public void essen(){
		essVerhalten.essen();
	}
	
}
