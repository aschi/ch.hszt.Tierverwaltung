package ch.hszt.tierverwaltung.gui.listings;

import java.util.ArrayList;

import ch.hszt.tierverwaltung.tier.backend.Tier;

public class TierOverview extends Overview<Tier>{

	public TierOverview(){
		ArrayList<Tier> al = new ArrayList<Tier>();
		String[] columnNames = {"Name", "Art", "Rasse"};
		createTable(columnNames, al);
	}
	
	@Override
	public void updateTableValues(ArrayList<Tier> input) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
