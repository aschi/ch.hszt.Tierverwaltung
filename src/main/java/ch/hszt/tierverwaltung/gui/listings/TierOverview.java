package ch.hszt.tierverwaltung.gui.listings;

import java.sql.SQLException;
import java.util.List;

import ch.hszt.tierverwaltung.database.tier.TierDatabaseAccess;
import ch.hszt.tierverwaltung.tier.backend.Tier;

public class TierOverview extends Overview<Tier>{
	public TierOverview() throws SQLException{
		List<Tier> al = TierDatabaseAccess.getInstance().getList();
		String[] columnNames = {"Name", "Art", "Rasse", "Alter", "Grösse"};
		createTable(columnNames, al, new Tier());
	}
	
	@Override
	public void updateTableValues(List<Tier> input) {
		try {
			input = (input==null) ? TierDatabaseAccess.getInstance().getList() : input;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
			model.addRow(row);
		}
	}
	
	
	
}
