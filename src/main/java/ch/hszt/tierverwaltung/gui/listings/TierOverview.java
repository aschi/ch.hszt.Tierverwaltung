package ch.hszt.tierverwaltung.gui.listings;

import java.sql.SQLException;
import java.util.List;

import ch.hszt.tierverwaltung.backend.Tier;
import ch.hszt.tierverwaltung.database.kunde.KundeDataMapper;
import ch.hszt.tierverwaltung.database.tier.TierDataMapper;
import ch.hszt.tierverwaltung.gui.MainGui;

public class TierOverview extends Overview<Tier>{
	public TierOverview(MainGui gui) throws SQLException{
		super(gui);
		setMapper(new TierDataMapper());
		setInput(getMapper().getList());
		setUpTable();
	}
	
	public TierOverview(MainGui gui, List<Tier> input){
		super(gui, input);
		setUpTable();
	}
	
	private void setUpTable(){
		String[] columnNames = {"Name", "Art", "Rasse", "Alter", "Grösse"};
		createTable(columnNames, getInput(), new Tier());
	}
	
	/**
	 * Aktualisiere die Tabelle mit den Werten aus der gegebenen ArrayList
	 */
	@Override
	public void updateTableValues(List<Tier> input) {
		super.updateTableValues(input);
		
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
			getModel().addRow(row);
		}
	}
	
	/**
	 * Aktualisiere Tabelle mit Werten aus der Datenbank
	 */
	@Override
	public void updateTableValues(){
		try {
			if(getMapper() == null){
				setMapper(new TierDataMapper());
			}
			updateTableValues(getMapper().getList());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
