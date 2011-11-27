package ch.hszt.tierverwaltung.gui.listings;

import java.util.ArrayList;

import ch.hszt.tierverwaltung.tier.backend.Tier;
import ch.hszt.tierverwaltung.tier.backend.Tierart;

public class TierOverview extends Overview<Tier>{

	public TierOverview(){
		ArrayList<Tier> al = new ArrayList<Tier>();
		String[] columnNames = {"Name", "Art", "Rasse", "Alter", "Grösse"};
		createTable(columnNames, al);
	}
	
	@Override
	public void updateTableValues(ArrayList<Tier> input) {
		String[] row = new String[5];
		for(Tier e : input){
			row[0] = e.getName();
			row[1] = e.getAnmerkungen();
			row[2] = e.getRasse();
			row[3] = String.valueOf(e.getTieralter());
			switch(e.getGroesseID()){
			case 1:
				row[4] = "<= 30cm";
				break;
			case 2:
				row[4] = "30cm - 1m";
				break;
			case 3:
				row[4] = "> 1m";
				break;
			}
			model.addRow(row);
		}
	}
	
	
	
}
