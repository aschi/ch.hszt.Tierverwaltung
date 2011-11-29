package ch.hszt.tierverwaltung.gui.listings;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTable;

import ch.hszt.tierverwaltung.gui.forms.FormBuilder;
import ch.hszt.tierverwaltung.tier.backend.Tier;

public abstract class Overview <T> extends JTable {
	ReadOnlyTableModel model;
	List<T> input;
	
	public void createTable(String[] columnNames, final List<T> input){
		model = new ReadOnlyTableModel(columnNames, 0);
		this.setModel(model);
		this.input = input;
		
		//MouseListener
		this.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				if(e.getClickCount() == 2){
					int selRow = ((JTable) e.getComponent()).getSelectedRow();
					new FormBuilder().buildForm(input.get(selRow));
					
					//System.out.println(((Tier)input.get(selRow)).getName());
				}
			}
		});
		
		
		updateTableValues(input);
	}
	
	public abstract void updateTableValues(List<T> input);
}
