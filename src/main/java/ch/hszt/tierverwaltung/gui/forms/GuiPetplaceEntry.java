package ch.hszt.tierverwaltung.gui.forms;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import ch.hszt.tierverwaltung.backend.Config;
import ch.hszt.tierverwaltung.backend.Petplace;
import ch.hszt.tierverwaltung.backend.ValidationException;
import ch.hszt.tierverwaltung.database.tierplatz.PetplaceDataMapper;
import ch.hszt.tierverwaltung.gui.MainGui;

/**
 * Graphical User Interface for a object of the typ petplace
 * @author Corinne
 *
 */
public class GuiPetplaceEntry {
	private JFrame frame;
	private JPanel panel;

	private JComboBox suitableForCbx;
	private JTextField cageSizeTxt;
	private JTextArea equipmentTxt;
	private JComboBox noPetsCbx;
	private JComboBox runCbx;
	private JTextField runSizeTxt;

	private JButton saveButton;
	private JButton deleteButton;

	private PetplaceDataMapper tdm;
	private MainGui gui;
	private Petplace petSpace;

	public GuiPetplaceEntry(MainGui gui) {
		this.gui = gui;
		fensterErzeugen();
		tdm = new PetplaceDataMapper();
	}

	public GuiPetplaceEntry(Petplace petSpace, MainGui gui) {
		this(gui);
		this.petSpace = petSpace;
		loadPetSpace();
	}

	private void loadPetSpace() {
		suitableForCbx.setSelectedIndex(petSpace.getAdaptedForPetID());
		
		cageSizeTxt.setText(Integer.toString(petSpace.getSize()));
		noPetsCbx.setSelectedIndex(petSpace.getNoOfPets());
	
		runCbx.setSelectedIndex(petSpace.getRun() == '1' ? 0 : 1);
		runSizeTxt.setText((petSpace.getRunSize() >= 0) ? Integer.toString(petSpace.getRunSize()): "");	
		
		equipmentTxt.setText(petSpace.getEquipment());
	}

	private void writePetSpace() {
		petSpace.setAdaptedForPetID(suitableForCbx.getSelectedIndex());
		petSpace.setSize(Integer.valueOf(cageSizeTxt.getText()));
		
		petSpace.setRun(runCbx.getSelectedIndex() == 0 ? '1' : '0');
		petSpace.setRunSize(runSizeTxt.getText().equals("") ? -1 : Integer.valueOf(runSizeTxt.getText()));
		
		petSpace.setNoOfPets(noPetsCbx.getSelectedIndex());
		
		petSpace.setEquipment(equipmentTxt.getText());	
	}

	private void closeWindow() {
		frame.dispose();
	}

	private void fensterErzeugen() {
		frame = new JFrame("Tierplatzeintrag");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		JPanel contentPane = new JPanel(new BorderLayout());
		JPanel buttonPane = new JPanel();

		panel = new JPanel(new SpringLayout());
		JPanel areaPanel = new JPanel(new SpringLayout());

		JLabel suitableForLbl = new JLabel("Geeignet für:");
		JLabel cageSizeLbl = new JLabel("Grösse:");
		JLabel noPetsLbl = new JLabel("Anzahl Tiere:");
		JLabel runLbl = new JLabel("Auslauf:");
		JLabel runSizeLbl = new JLabel("Auslaufgrösse:");
		JLabel equipmentLbl = new JLabel("Ausstattung:   ");
		
		createButtons();

		suitableForCbx = new JComboBox(Config.petSpaceSize);
		cageSizeTxt = new JTextField();
		noPetsCbx = new JComboBox(new String[] {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
		runCbx = new JComboBox(new String[] {"Ja", "Nein"});
		runSizeTxt = new JTextField();
		equipmentTxt = new JTextArea();
		equipmentTxt.setRows(10);
		
		suitableForLbl.setLabelFor(suitableForCbx);
		cageSizeLbl.setLabelFor(cageSizeTxt);
		equipmentLbl.setLabelFor(equipmentTxt);
		noPetsLbl.setLabelFor(noPetsCbx);
		runLbl.setLabelFor(runCbx);
		runSizeLbl.setLabelFor(runSizeTxt);
		
		panel.add(suitableForLbl);
		panel.add(suitableForCbx);
		panel.add(cageSizeLbl);
		panel.add(createSizePanel(cageSizeTxt));
		panel.add(noPetsLbl);
		panel.add(noPetsCbx);
		panel.add(runLbl);
		panel.add(runCbx);
		panel.add(runSizeLbl);
		panel.add(createSizePanel(runSizeTxt));
		
		areaPanel.add(equipmentLbl);
		areaPanel.add(new JScrollPane(equipmentTxt));
		
		frame.getContentPane().add(contentPane);
		SpringUtilities.makeCompactGrid(panel, 5, 2, 5, 5, 5, 5);
		SpringUtilities.makeCompactGrid(areaPanel, 1, 2, 5, 5, 5, 5);

		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.add(Box.createHorizontalGlue());
		buttonPane.add(saveButton);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(deleteButton);

		contentPane.add(panel, BorderLayout.NORTH);
		contentPane.add(areaPanel, BorderLayout.CENTER);
		contentPane.add(buttonPane, BorderLayout.SOUTH);

		frame.setVisible(true);
		frame.pack();
	}

	/**
	 * Create a Panel containing the given field + a "m²" label
	 * @param field input field
	 * @return JPanel containing the field
	 */
	private JPanel createSizePanel(JTextField field){
		JPanel sizePane = new JPanel(new SpringLayout());
		sizePane.add(field);
		sizePane.add(new JLabel("m²"));
		SpringUtilities.makeCompactGrid(sizePane, 1, 2, 0, 5, 5, 5);
		return sizePane;
	}
	
	/**
	 * Create our save & delete buttons / add action listeners
	 */
	private void createButtons(){
		saveButton = new JButton("Speichern");
		deleteButton = new JButton("Loeschen");
		
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (petSpace == null) {
						petSpace = new Petplace();
					}
					writePetSpace();
					tdm.save(petSpace);

					synchronized (gui.getOverviewUpdater()) {
						gui.getOverviewUpdater().notify();
					}

					String meldung = "Erfolgreich gespeichert";
					JOptionPane.showMessageDialog(null, meldung, "Information",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					String meldung = "Beim Speichern in die Datenbank ist ein Fehler aufgetreten: "
							+ e1.getStackTrace();
					JOptionPane.showMessageDialog(null, meldung,
							"Betriebliche Benachrichtigung",
							JOptionPane.ERROR_MESSAGE);

				} catch (ValidationException e1) {
					String meldung2 = ValidationException.createErrorMsg(e1);
					JOptionPane.showMessageDialog(null, meldung2,
							"Betriebliche Benachrichtigung",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					tdm.delete(petSpace);

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
	}
	
}