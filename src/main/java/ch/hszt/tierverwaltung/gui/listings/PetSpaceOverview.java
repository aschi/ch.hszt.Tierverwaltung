package ch.hszt.tierverwaltung.gui.listings;

import java.sql.SQLException;
import java.util.List;

import ch.hszt.tierverwaltung.backend.Config;
import ch.hszt.tierverwaltung.backend.Petspace;
import ch.hszt.tierverwaltung.database.petspace.PetspaceDataMapper;
import ch.hszt.tierverwaltung.gui.MainGui;

/**
 * 
 * @author Aschi
 *
 */
public class PetSpaceOverview extends Overview<Petspace>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * @param gui
	 * @throws SQLException
	 */
	public PetSpaceOverview(MainGui gui) throws SQLException {
		super(gui);
		setMapper(new PetspaceDataMapper());
		setInput(getMapper().getList());
		setUpTable();
	}

	/**
	 * Constructors
	 * 
	 * @param gui
	 * @param input
	 */
	public PetSpaceOverview(MainGui gui, List<Petspace> input) {
		super(gui, input);
		setUpTable();
	}

	/**
	 * sets a table with all petplaces up
	 */
	private void setUpTable() {
		String[] columnNames = {"Geeignet für", "Grösse", "Anzahl Tiere", "Ausstattung", "Auslauf", "Auslauf Grösse"};
		createTable(columnNames, getInput(), new Petspace(), null);
	}
	
	@Override
	public void updateTableValues(List<Petspace> input) {
		super.updateTableValues(input);

		String[] row = new String[6];
		if (input != null && input.size() > 0) {
			for (Petspace e : input) {
				row[0] = Config.petSpaceSize[e.getAdaptedForPetID()];
				row[1] = Integer.toString(e.getSize()) + "m²";
				row[2] = Integer.toString(e.getNoOfPets());
				row[3] = e.getEquipment();
				row[4] = (e.getRun() == '1' ? "Ja" : "Nein");
				row[5] = e.getRunSize() >= 0 ? Integer.toString(e.getRunSize()) + "m²" : "";
				getModel().addRow(row);
			}
		}
	}
	
	@Override
	public void updateTableValues() {
		try {
			if (getMapper() == null) {
				setMapper(new PetspaceDataMapper());
			}
			updateTableValues(getMapper().getList());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
