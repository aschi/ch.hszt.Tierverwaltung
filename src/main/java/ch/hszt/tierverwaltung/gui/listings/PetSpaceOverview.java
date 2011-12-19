package ch.hszt.tierverwaltung.gui.listings;

import java.sql.SQLException;
import java.util.List;

import ch.hszt.tierverwaltung.backend.Config;
import ch.hszt.tierverwaltung.backend.Tierplatz;
import ch.hszt.tierverwaltung.database.tierplatz.TierplatzDataMapper;
import ch.hszt.tierverwaltung.gui.MainGui;

public class PetSpaceOverview extends Overview<Tierplatz>{

	public PetSpaceOverview(MainGui gui) throws SQLException {
		super(gui);
		setMapper(new TierplatzDataMapper());
		setInput(getMapper().getList());
		setUpTable();
	}

	public PetSpaceOverview(MainGui gui, List<Tierplatz> input) {
		super(gui, input);
		setUpTable();
	}

	private void setUpTable() {
		String[] columnNames = {"Geeignet für", "Grösse", "Anzahl Tiere", "Ausstattung", "Auslauf", "Auslauf Grösse"};
		createTable(columnNames, getInput(), new Tierplatz(), null);
	}
	
	/**
	 * Aktualisiere die Tabelle mit den Werten aus der gegebenen ArrayList
	 */
	@Override
	public void updateTableValues(List<Tierplatz> input) {
		super.updateTableValues(input);

		String[] row = new String[6];
		if (input != null && input.size() > 0) {
			for (Tierplatz e : input) {
				row[0] = Config.petSpaceSize[e.getGeeignetFuerTierID()];
				row[1] = Integer.toString(e.getGroesse()) + "m²";
				row[2] = Integer.toString(e.getAnzahlTiere());
				row[3] = e.getAusstattung();
				row[4] = (e.getAuslauf() == '1' ? "Ja" : "Nein");
				row[5] = e.getAuslaufGroesse() >= 0 ? Integer.toString(e.getAuslaufGroesse()) + "m²" : "";
				getModel().addRow(row);
			}
		}
	}
	
	/**
	 * Aktualisiere Tabelle mit Werten aus der Datenbank
	 */
	@Override
	public void updateTableValues() {
		try {
			if (getMapper() == null) {
				setMapper(new TierplatzDataMapper());
			}
			updateTableValues(getMapper().getList());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
