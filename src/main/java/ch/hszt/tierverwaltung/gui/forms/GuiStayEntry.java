package ch.hszt.tierverwaltung.gui.forms;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import org.jdesktop.swingx.JXDatePicker;

import ch.hszt.tierverwaltung.backend.Pet;
import ch.hszt.tierverwaltung.backend.Petspace;
import ch.hszt.tierverwaltung.backend.Stay;
import ch.hszt.tierverwaltung.backend.ValidationException;
import ch.hszt.tierverwaltung.database.customer.CustomerDataMapper;
import ch.hszt.tierverwaltung.database.pet.PetDataMapper;
import ch.hszt.tierverwaltung.database.petspace.PetspaceDataMapper;
import ch.hszt.tierverwaltung.database.stay.StayDataMapper;
import ch.hszt.tierverwaltung.gui.MainGui;

public class GuiStayEntry {
	private MainGui gui;
	private Pet selectedPet;
	private Stay stay;

	private JFrame frame;
	private JPanel centerPane;
	private JPanel dateSelectionPane;
	private JPanel buttonPane;

	private JComboBox spaceSelector;
	private JComboBox petSelector;

	private JXDatePicker dateFrom;
	private JXDatePicker dateTo;

	private JButton saveButton;
	private JButton deleteButton;

	private PetDataMapper pdm;
	private StayDataMapper sdm;
	private PetspaceDataMapper psdm;

	public GuiStayEntry(MainGui gui) {
		this.gui = gui;

		sdm = new StayDataMapper();
		psdm = new PetspaceDataMapper();
		pdm = new PetDataMapper();
		createFrame();
	}

	public GuiStayEntry(Stay stay, MainGui gui) {
		this(gui);
		this.stay = stay;
		
		loadStayValues();
	}

	private void loadStayValues() {
		petSelector.setSelectedItem(stay.getPet());
		dateFrom.setDate(stay.getDateFrom());
		dateTo.setDate(stay.getDateTo());
		checkAndUpdateAvailableSpaces();
		spaceSelector.setSelectedItem(stay.getPetspace());
	}

	//Reads values from gui
	private void readStayValues() {
		stay.setDateFrom(dateFrom.getDate());
		stay.setDateTo(dateTo.getDate());
		if(petSelector.getSelectedItem() instanceof Pet){
			stay.setPet((Pet)petSelector.getSelectedItem());
		}
		if(spaceSelector.getSelectedItem() instanceof Petspace){
			stay.setPetspace((Petspace)spaceSelector.getSelectedItem());
		}
	}

	private void createFrame() {
		frame = new JFrame("Aufenthalt");

		JPanel contentPane = new JPanel(new BorderLayout());

		DefaultComboBoxModel petSelectorModel = new DefaultComboBoxModel();
		petSelector = new JComboBox(petSelectorModel);

		try {
			for (Pet p : pdm.getList()) {
				petSelectorModel.addElement(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		petSelector.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkAndUpdateAvailableSpaces();
			}
		});

		// dateSelectionPane
		dateSelectionPane = new JPanel(new SpringLayout());

		dateFrom = new JXDatePicker();
		dateTo = new JXDatePicker();

		dateFrom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkAndUpdateAvailableSpaces();
			}
		});

		dateTo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkAndUpdateAvailableSpaces();
			}
		});

		JLabel fromLbl = new JLabel("Von");
		JLabel toLbl = new JLabel("Bis");

		dateSelectionPane.add(fromLbl);
		dateSelectionPane.add(dateFrom);
		dateSelectionPane.add(toLbl);
		dateSelectionPane.add(dateTo);

		SpringUtilities.makeCompactGrid(dateSelectionPane, 1, 4, 0, 5, 5, 5);

		// spaceSelectionPane
		String[] cbxEmptyVal = { "- Sie müssen zuerst Tier & Datum auswählen -" };
		spaceSelector = new JComboBox(cbxEmptyVal);
		spaceSelector.setEnabled(false);

		// buttonPane
		buttonPane = new JPanel();
		saveButton = new JButton("Speichern");
		deleteButton = new JButton("Loeschen");

		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(stay == null){
					stay = new Stay();
				}
				
				readStayValues();
				try {
					sdm.save(stay);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ValidationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				synchronized (gui.getOverviewUpdater()) {
					gui.getOverviewUpdater().notify();
				}
				
				String message = "Erfolgreich gespeichert";
				JOptionPane.showMessageDialog(null, message, "Information",
						JOptionPane.INFORMATION_MESSAGE);	
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					sdm.delete(stay);

					synchronized (gui.getOverviewUpdater()) {
						gui.getOverviewUpdater().notify();
					}

					closeWindow();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.add(Box.createHorizontalGlue());
		buttonPane.add(saveButton);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(deleteButton);

		// centerPane
		centerPane = new JPanel(new SpringLayout());
		centerPane.add(new JLabel("Tier:"));
		centerPane.add(petSelector);
		centerPane.add(new JLabel("Datum:"));
		centerPane.add(dateSelectionPane);
		centerPane.add(new JLabel("Tierplatz:"));
		centerPane.add(spaceSelector);

		SpringUtilities.makeCompactGrid(centerPane, 3, 2, 5, 5, 5, 5);

		contentPane.add(centerPane, BorderLayout.CENTER);
		contentPane.add(buttonPane, BorderLayout.SOUTH);

		frame.setContentPane(contentPane);

		frame.setVisible(true);
		frame.pack();
	}

	public static void main(String[] args) {
		new GuiStayEntry(null);
	}

	private void closeWindow(){
		frame.dispose();
	}
	
	private void checkAndUpdateAvailableSpaces() {
		if (dateFrom.getDate() != null && dateTo.getDate() != null
				&& (dateFrom.getDate().getTime() < dateTo.getDate().getTime())
				&& (petSelector.getSelectedItem() instanceof Pet)) {

			selectedPet = (Pet) petSelector.getSelectedItem();

			try {
				DefaultComboBoxModel spaceSelectorModel = new DefaultComboBoxModel();
				spaceSelector.setModel(spaceSelectorModel);

				List<Petspace> psList = psdm.getFreePetspaces(selectedPet,
						dateFrom.getDate(), dateTo.getDate());
				if (psList != null) {
					for (Petspace ps : psList) {
						spaceSelectorModel.addElement(ps);
					}
					spaceSelector.setEnabled(true);
				} else {
					spaceSelectorModel.addElement("- keine Freien Tierplätze gefunden! -");
					spaceSelector.setEnabled(false);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else{
			DefaultComboBoxModel spaceSelectorModel = new DefaultComboBoxModel();
			spaceSelector.setModel(spaceSelectorModel);
			spaceSelectorModel.addElement("- Sie müssen zuerst Tier & Datum auswählen -");
			spaceSelector.setEnabled(false);
		}
	}
}
