package ch.hszt.tierverwaltung.gui.listings;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import ch.hszt.tierverwaltung.backend.IDataRecord;
import ch.hszt.tierverwaltung.gui.forms.FormBuilder;

public abstract class Overview <T extends IDataRecord> extends JPanel {
	JTable overview;
	ReadOnlyTableModel model;
	List<T> input;
	T emptyObject;
	JPanel buttonPane;
	
	public void createTable(String[] columnNames, final List<T> input, final T emptyObject){
		this.input = input;
		this.emptyObject = emptyObject;
		
		//Create components
		initTable(columnNames);
		createButtonPane();
		
		//build layout
		this.setLayout(new BorderLayout());
		this.add(buttonPane, BorderLayout.NORTH);
		this.add(overview, BorderLayout.CENTER);
		
		updateTableValues(input);
	}
	
	/**
	 * Creates the overview Buttons & adds actionlisteners for funktionality
	 */
	private void createButtonPane(){
		// Buttons
		buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JButton newButton = new JButton("Neu...");
		JButton deleteButton = new JButton("Löschen");
		
		buttonPane.add(newButton);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(deleteButton);
		
		//New
		newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new FormBuilder<T>().buildForm((T)emptyObject.clone());
			}
		});
		
		//Delete
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					input.get(overview.getSelectedRow()).delete();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				input.remove(overview.getSelectedRow());
				updateTableValues(input);
			}
		});
	}
	
	/**
	 * Initialise jTable / create model
	 * @param columnNames
	 */
	private void initTable(String[] columnNames){
		model = new ReadOnlyTableModel();
		model.setColumnIdentifiers(columnNames);
		
		overview = new JTable(model);
		
		
		//MouseListener to open an entry
		overview.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				if(e.getClickCount() == 2){
					int selRow = ((JTable) e.getComponent()).getSelectedRow();
					new FormBuilder<T>().buildForm(input.get(selRow));
				}
			}
		});
	}
	
	/**
	 * Aktualisiere die Listenansicht
	 * @param input List welche die Daten enthält
	 */
	public void updateTableValues(List<T> input){
		this.input = input;
		//Remove existing entries before refilling
		while(model.getRowCount() > 0){
			model.removeRow(0);
		}
	}
}
