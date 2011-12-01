package ch.hszt.tierverwaltung.gui.listings;

import java.util.ArrayList;
import java.util.List;

import ch.hszt.tierverwaltung.kunden.backend.Kunde;
import ch.hszt.tierverwaltung.tier.backend.Tier;

public class KundenOverview extends Overview<Kunde>{

	public KundenOverview(){
		ArrayList<Kunde> al = new ArrayList<Kunde>();
		String[] columnNames = {"Name", "Vorname", "Ort", "Telefon Nr", "Tiere"};
		createTable(columnNames, al, new Kunde());
	}
	
	@Override
	public void updateTableValues(List<Kunde> input) {
		String[] row = new String[5];
		String tNames;
		
		for(Kunde k : input){
			row[0] = k.getName();
			row[1] = k.getVorname();
			row[2] = k.getOrt();
			row[3] = k.getTelNo();
			
			tNames = "";
			for(Tier t : k.getTiere()){
				tNames += t.getName() +", ";
			}
			row[4] = tNames.equals("") ? tNames : tNames.substring(0, tNames.length()-2);
			
			model.addRow(row);
		}
		
	}
	
}
