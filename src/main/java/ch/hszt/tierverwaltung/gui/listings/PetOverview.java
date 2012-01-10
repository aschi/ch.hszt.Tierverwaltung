package ch.hszt.tierverwaltung.gui.listings;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JPanel;

import ch.hszt.tierverwaltung.backend.Config;
import ch.hszt.tierverwaltung.backend.Pet;
import ch.hszt.tierverwaltung.database.pet.PetDataMapper;
import ch.hszt.tierverwaltung.gui.MainGui;

public class PetOverview extends Overview<Pet> {
	public PetOverview(MainGui gui) throws SQLException {
		super(gui);
		setMapper(new PetDataMapper());
		setInput(getMapper().getList());
		setUpTable();
	}

	public PetOverview(MainGui gui, List<Pet> input) {
		super(gui, input);
		setUpTable();
	}

	/**
	 * Set up the table
	 */
	private void setUpTable() {
		String[] columnNames = { "Name", "Art", "Rasse", "Alter", "Grösse" };
		createTable(columnNames, getInput(), new Pet(), null);
	}

	/**
	 * Update Overview using a given arraylist
	 */
	@Override
	public void updateTableValues(List<Pet> input) {
		super.updateTableValues(input);

		String[] row = new String[5];
		if (input != null && input.size() > 0) {
			for (Pet e : input) {
				row[0] = e.getName();
				row[1] = e.getSpecies();
				row[2] = e.getRace();
				row[3] = String.valueOf(e.getAge());
				row[4] = Config.petSize[e.getSizeId()];
				getModel().addRow(row);
			}
		}
	}

	/**
	 * Update Overview with database values
	 */
	@Override
	public void updateTableValues() {
		try {
			if (getMapper() == null) {
				setMapper(new PetDataMapper());
			}
			updateTableValues(getMapper().getList());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
