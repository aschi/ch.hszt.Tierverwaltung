package ch.hszt.tierverwaltung.gui.listings;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ch.hszt.tierverwaltung.database.kunde.KundeDataMapper;
import ch.hszt.tierverwaltung.gui.MainGui;
import ch.hszt.tierverwaltung.kunden.backend.Kunde;
import ch.hszt.tierverwaltung.tier.backend.Tier;

public class KundenOverview extends Overview<Kunde>{

	public KundenOverview(MainGui gui){
		super(gui);
		setMapper(new KundeDataMapper());
		ArrayList<Kunde> al = new ArrayList<Kunde>();
		String[] columnNames = {"Name", "Vorname", "Ort", "Telefon Nr", "Tiere"};
		createTable(columnNames, al, new Kunde());
	}
	
	@Override
	public void updateTableValues(List<Kunde> input) {
		try {
			KundeDataMapper kdm = new KundeDataMapper();
			input = (input==null) ? kdm.getList() : input;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String[] row = new String[5];
		String tNames;
		
		for(Kunde k : input){
			row[0] = k.getName();
			row[1] = k.getVorname();
			row[2] = k.getOrt();
			row[3] = k.getTelefon();
			
			tNames = "";
			for(Tier t : k.getTiere()){
				tNames += t.getName() +", ";
			}
			row[4] = tNames.equals("") ? tNames : tNames.substring(0, tNames.length()-2);
			
			getModel().addRow(row);
		}
		
	}
	
}
