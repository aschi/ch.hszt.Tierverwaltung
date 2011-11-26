package ch.hszt.tierverwaltung.gui.listings;

import java.util.ArrayList;

import ch.hszt.tierverwaltung.kunden.backend.Kunde;

public class KundenOverview extends Overview<Kunde>{

	public KundenOverview(){
		ArrayList<Kunde> al = new ArrayList<Kunde>();
		String[] columnNames = {"Name", "Adresse", "Blibla Kundendaten"};
		createTable(columnNames, al);
	}
	
	@Override
	public void updateTableValues(ArrayList<Kunde> input) {
		// TODO Auto-generated method stub
		
	}
	
}
