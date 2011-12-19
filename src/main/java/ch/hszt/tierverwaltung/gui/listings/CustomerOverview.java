package ch.hszt.tierverwaltung.gui.listings;

import java.sql.SQLException;
import java.util.List;

import ch.hszt.tierverwaltung.backend.Kunde;
import ch.hszt.tierverwaltung.backend.Tier;
import ch.hszt.tierverwaltung.database.kunde.KundeDataMapper;
import ch.hszt.tierverwaltung.gui.MainGui;

public class CustomerOverview extends Overview<Kunde>{

	public CustomerOverview(MainGui gui) throws SQLException{
		super(gui);
		setMapper(new KundeDataMapper());
		setInput(getMapper().getList());
		setUpTable();
	}
	
	public CustomerOverview(MainGui gui, List<Kunde> input){
		super(gui, input);
		setUpTable();
	}
	
	private void setUpTable(){
		String[] columnNames = {"Name", "Vorname", "Ort", "Telefon Nr", "Tiere"};
		createTable(columnNames, getInput(), new Kunde());
	}
	
	
	/**
	 * Tabelle aktualisieren mit der
	 */
	@Override
	public void updateTableValues(List<Kunde> input) {
		super.updateTableValues(input);
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
	
	@Override
	public void updateTableValues(){
		try {
			if(getMapper() == null){
				setMapper(new KundeDataMapper());
			}
			updateTableValues(getMapper().getList());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	
}
