package ch.hszt.tierverwaltung.gui.listings;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import ch.hszt.tierverwaltung.backend.Tier;
import ch.hszt.tierverwaltung.gui.MainGui;

public class AssignedPetsOverview extends Overview<Tier> {

	public AssignedPetsOverview(MainGui gui, List<Tier> input) {
		super(gui, input);
		setUpTable(null);
	}
	
	public AssignedPetsOverview(MainGui gui, ArrayList<Tier> input,
			JPanel buttonPane) {
		super(gui, input);
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
	
	@Override
	public void updateTableValues() {
		super.updateTableValues(getInput());
		
		String[] row = new String[2];
		for(Tier e : getInput()){
			row[0] = e.getName();
			row[1] = e.getArt();			
			getModel().addRow(row);
		}
		
	}

}
