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
import ch.hszt.tierverwaltung.database.IDataMapper;
import ch.hszt.tierverwaltung.gui.MainGui;
import ch.hszt.tierverwaltung.gui.forms.FormBuilder;

public abstract class Overview <T extends IDataRecord> extends JPanel {
	private MainGui gui; //Verwendet zum aktualisieren der Listenansichten
	private JTable overview;
	private ReadOnlyTableModel model;
	private List<T> input;
	private T emptyObject;
	private JPanel buttonPane;
	private IDataMapper<T> mapper;
	
	public Overview(MainGui gui, List<T> input){
		this(gui);
		this.input = input;
	}
	
	public Overview(MainGui gui){
		this.gui = gui;
	}
	
	public ReadOnlyTableModel getModel(){
		return model;
	}
	
	public List<T> getInput(){
		return input;
	}
	
	public void setInput(List<T> input){
		this.input = input;
	}
	
	public IDataMapper<T> getMapper(){
		return mapper;
	}
	
	public void setMapper(IDataMapper<T> mapper){
		this.mapper = mapper;
	}
	
	/**
	 * Standartbuttons verwenden
	 * @param columnNames
	 * @param input
	 * @param emptyObject
	 */
	public void createTable(String[] columnNames, final List<T> input, final T emptyObject){
		createTable(columnNames, input, emptyObject, null);
	}
	
	/**
	 * Eigene Buttons mitgeben
	 * @param columnNames
	 * @param input
	 * @param emptyObject
	 * @param buttonPane
	 */
	public void createTable(String[] columnNames, final List<T> input, final T emptyObject, JPanel buttonPane){
		this.input = input;
		this.emptyObject = emptyObject;
		
		//Create components
		initTable(columnNames);
		//Button Pane
		if(buttonPane == null){
			createButtonPane();
		}else{
			this.buttonPane = buttonPane;
		}
		
		//build layout
		this.setLayout(new BorderLayout());
		this.add(this.buttonPane, BorderLayout.NORTH);
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
				new FormBuilder<T>().buildForm((T)emptyObject.clone(), gui);
			}
		});
		
		//Delete
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					mapper.delete(input.get(overview.getSelectedRow()));
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
					new FormBuilder<T>().buildForm(input.get(selRow), gui);
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
	
	/**
	 * Aktualisiere die Listenansicht. Verwende Daten aus der Datenbank
	 * 
	 */
	public abstract void updateTableValues();
	
	/**
	 * Gibt den Index der ausgewählten Zeile der Tabelle zurück
	 * @return Index der ausgewählten Zeile
	 */
	public int getSelectedRow(){
		return overview.getSelectedRow();
	}
	
}
