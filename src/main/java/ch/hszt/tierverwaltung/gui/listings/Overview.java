package ch.hszt.tierverwaltung.gui.listings;

import java.util.ArrayList;
import javax.swing.JTable;

public abstract class Overview <T> extends JTable {
	ReadOnlyTableModel model;
	
	public void createTable(String[] columnNames, ArrayList<T> input){
		model = new ReadOnlyTableModel(columnNames, 0);
		this.setModel(model);
		updateTableValues(input);
	}
	
	public abstract void updateTableValues(ArrayList<T> input);
}
