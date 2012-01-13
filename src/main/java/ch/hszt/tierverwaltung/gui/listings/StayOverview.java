package ch.hszt.tierverwaltung.gui.listings;

import java.sql.SQLException;
import java.util.List;

import ch.hszt.tierverwaltung.backend.Stay;
import ch.hszt.tierverwaltung.database.stay.StayDataMapper;
import ch.hszt.tierverwaltung.gui.MainGui;

/**
 * 
 * @author Aschi
 *
 */
public class StayOverview extends Overview<Stay>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * @param gui
	 * @throws SQLException
	 */
	public StayOverview(MainGui gui) throws SQLException {
		super(gui);
		setMapper(new StayDataMapper());
		setInput(getMapper().getList());
		setUpTable();
	}

	/**
	 * Constructor
	 * @param gui
	 * @param input
	 */
	public StayOverview(MainGui gui, List<Stay> input) {
		super(gui, input);
		setUpTable();
	}

	/**
	 * Set up table for pet spaces
	 */
	private void setUpTable() {
		String[] columnNames = {"Tier", "Datum von", "Datum bis", "Tierplatz"};
		createTable(columnNames, getInput(), new Stay(), null);
	}
	
	/**
	 * Update this overview using a given list
	 */
	@Override
	public void updateTableValues(List<Stay> input) {
		super.updateTableValues(input);

		String[] row = new String[4];
		if (input != null && input.size() > 0) {
			for (Stay e : input) {
				row[0] = e.getPet().toString();
				row[1] = e.getDateFrom().toString();
				row[2] = e.getDateTo().toString();
				row[3] = e.getPetspace().toString();
				getModel().addRow(row);
			}
		}
	}
	
	/**
	 * Update overview using the database
	 */
	@Override
	public void updateTableValues() {
		try {
			if (getMapper() == null) {
				setMapper(new StayDataMapper());
			}
			updateTableValues(getMapper().getList());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
