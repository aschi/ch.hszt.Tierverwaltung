package ch.hszt.tierverwaltung.gui.listings;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import ch.hszt.tierverwaltung.backend.Pet;
import ch.hszt.tierverwaltung.gui.MainGui;

public class AssignedPetsOverview extends Overview<Pet> {

	public AssignedPetsOverview(MainGui gui, List<Pet> input) {
		super(gui, input);
		setUpTable(null);
	}
	
	public AssignedPetsOverview(MainGui gui, ArrayList<Pet> input,
			JPanel buttonPane) {
		super(gui, input);
		setUpTable(buttonPane);		
	}

	private void setUpTable(JPanel buttonPane){
		String[] columnNames = {"Name", "Art"};
		createTable(columnNames, getInput(), new Pet(), buttonPane);
	}

	@Override
	public void updateTableValues(List<Pet> input) {
		super.updateTableValues(input);
		
		String[] row = new String[2];
		for(Pet e : input){
			row[0] = e.getName();
			row[1] = e.getSpecies();			
			getModel().addRow(row);
		}
		
	}
	
	@Override
	public void updateTableValues() {
		super.updateTableValues(getInput());
		
		String[] row = new String[2];
		for(Pet e : getInput()){
			row[0] = e.getName();
			row[1] = e.getSpecies();			
			getModel().addRow(row);
		}
		
	}

}
