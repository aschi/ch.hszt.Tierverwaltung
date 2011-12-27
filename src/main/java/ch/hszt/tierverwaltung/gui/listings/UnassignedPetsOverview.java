package ch.hszt.tierverwaltung.gui.listings;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import ch.hszt.tierverwaltung.backend.Pet;
import ch.hszt.tierverwaltung.database.pet.PetDataMapper;
import ch.hszt.tierverwaltung.gui.MainGui;

public class UnassignedPetsOverview extends Overview<Pet> {
	
	public UnassignedPetsOverview(MainGui gui, JPanel buttonPane) throws SQLException {
		super(gui);
		setMapper(new PetDataMapper());
		setInput(((PetDataMapper)getMapper()).getUnassignedPets());
		System.out.println("Unassigned pets: " + ((PetDataMapper)getMapper()).getUnassignedPets().size());
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
		try {
			if(getMapper() == null){
				setMapper(new PetDataMapper());
			}
			updateTableValues(((PetDataMapper)getMapper()).getUnassignedPets());
			System.out.println("Unassigned pets: " + ((PetDataMapper)getMapper()).getUnassignedPets().size());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
	}

}
