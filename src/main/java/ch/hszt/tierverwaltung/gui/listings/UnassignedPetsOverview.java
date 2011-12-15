package ch.hszt.tierverwaltung.gui.listings;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import ch.hszt.tierverwaltung.backend.Tier;
import ch.hszt.tierverwaltung.database.tier.TierDataMapper;
import ch.hszt.tierverwaltung.gui.MainGui;

public class UnassignedPetsOverview extends Overview<Tier> {
	
	public UnassignedPetsOverview(MainGui gui, JPanel buttonPane) throws SQLException {
		super(gui);
		setMapper(new TierDataMapper());
		setInput(((TierDataMapper)getMapper()).getUnassignedPets());
		setUpTable(buttonPane);		
	}

	private void setUpTable(JPanel buttonPane){
		String[] columnNames = {"Name", "Art"};
		createTable(columnNames, getInput(), new Tier(), buttonPane);
	}

	@Override
	public void updateTableValues(List<Tier> input) {
		super.updateTableValues(input);
		
		String[] row = new String[2];
		for(Tier e : input){
			row[0] = e.getName();
			row[1] = e.getArt();			
			getModel().addRow(row);
		}
		
	}
	
	public void updateTableValues() {
		try {
			if(getMapper() == null){
				setMapper(new TierDataMapper());
			}
			updateTableValues(((TierDataMapper)getMapper()).getUnassignedPets());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
	}

}
