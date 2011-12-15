package ch.hszt.tierverwaltung.gui.forms;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import ch.hszt.tierverwaltung.backend.Kunde;
import ch.hszt.tierverwaltung.backend.Tier;
import ch.hszt.tierverwaltung.backend.ValidationException;
import ch.hszt.tierverwaltung.database.kunde.KundeDataMapper;
import ch.hszt.tierverwaltung.database.tier.TierDataMapper;
import ch.hszt.tierverwaltung.gui.MainGui;
import ch.hszt.tierverwaltung.gui.listings.AssignedTierOverview;

public class GuiKundenEintrag {

	private JFrame frame;
	private JPanel panel;
	private Kunde customer;
	private MainGui gui;
	private JTextField nameText;
	private JTextField vornameText;
	private JTextField adresseText;
	private JTextField plzText;
	private JTextField ortText;
	private JTextField telnoText;
	private JTextField emailText;
	private AssignedTierOverview petTable;
	private JButton save;
	private JButton delete;
	private JButton addPetButton;
	private JButton removePetButton;
	
	
	public GuiKundenEintrag(MainGui gui) {
		this.gui = gui;
		createFrame();
	}

	public GuiKundenEintrag(Kunde kunde, MainGui gui) {
		this.customer = kunde;
		this.gui = gui;
		createFrame();
		loadKundeValue();
	}

	private void loadKundeValue() {

		nameText.setText(customer.getName());
		vornameText.setText(customer.getVorname());
		adresseText.setText(customer.getAdresse());
		plzText.setText(customer.getPlz());
		ortText.setText(customer.getOrt());
		telnoText.setText(customer.getTelefon());
		emailText.setText(customer.getEMail());
		petTable.updateTableValues(customer.getTiere());

	}

	private void createKundeValue() {

		customer.setName(nameText.getText());
		customer.setVorname(vornameText.getText());
		customer.setAdresse(adresseText.getText());
		customer.setPlz(plzText.getText());
		customer.setOrt(ortText.getText());
		customer.setTelefon(telnoText.getText());
		customer.setEMail(emailText.getText());

	}

	private void closeFenster() {
		frame.dispose();
	}

	private void createFrame() {

		frame = new JFrame("Kundeneintrag");
		frame.setLocation(400, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		JPanel contentPane = new JPanel(new BorderLayout());

		JPanel tblPane = new JPanel();
		JPanel buttonPane = new JPanel();

		panel = new JPanel(new SpringLayout());

		JLabel name = new JLabel("Name");
		JLabel vorname = new JLabel("Vorname");
		JLabel adresse = new JLabel("Adresse");
		JLabel plz = new JLabel("PLZ");
		JLabel ort = new JLabel("Ort");
		JLabel telno = new JLabel("Telefonnummer");
		JLabel email = new JLabel("E-Mail Adresse");

		save = new JButton("Speichern");
		delete = new JButton("Loeschen");

		nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(200, 20));
		name.setLabelFor(nameText);
		vornameText = new JTextField();
		vorname.setLabelFor(vornameText);
		adresseText = new JTextField();
		adresse.setLabelFor(adresseText);
		plzText = new JTextField();
		plz.setLabelFor(plzText);
		ortText = new JTextField();
		ort.setLabelFor(ortText);
		telnoText = new JTextField();
		telno.setLabelFor(telnoText);
		emailText = new JTextField();
		email.setLabelFor(emailText);

		petTable = new AssignedTierOverview(gui, customer.getTiere(),
				createPetTableButtonPane());
		// tiereTable.updateTableValues(new ArrayList<Tier>());
		// tiereTable.setButtonPane(createButtonPane());

		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (customer == null) {
						customer = new Kunde();
					}
					createKundeValue();
					new KundeDataMapper().save(customer);

					synchronized (gui.getOverviewUpdater()) {
						gui.getOverviewUpdater().notify();
					}

					checkPetButtonVisibility();
					
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

		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new KundeDataMapper().delete(customer);

					synchronized (gui.getOverviewUpdater()) {
						gui.getOverviewUpdater().notify();
					}

					closeFenster();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		panel.add(name);
		panel.add(nameText);
		panel.add(vorname);
		panel.add(vornameText);
		panel.add(adresse);
		panel.add(adresseText);
		panel.add(plz);
		panel.add(plzText);
		panel.add(ort);
		panel.add(ortText);
		panel.add(telno);
		panel.add(telnoText);
		panel.add(email);
		panel.add(emailText);

		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.add(Box.createHorizontalGlue());
		buttonPane.add(save);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(delete);

		tblPane.add(petTable);

		frame.getContentPane().add(contentPane);
		SpringUtilities.makeCompactGrid(panel, 7, 2, 5, 5, 5, 5);
		contentPane.add(panel, BorderLayout.CENTER);
		contentPane.add(tblPane, BorderLayout.EAST);
		contentPane.add(buttonPane, BorderLayout.SOUTH);
		frame.setVisible(true);
		frame.pack();
	}

	private JPanel createPetTableButtonPane() {
		// Buttons
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		addPetButton = new JButton("Hinzufügen");
		removePetButton = new JButton("Entfernen");

		buttonPane.add(addPetButton);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(removePetButton);
		
		// Add
		addPetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AddPetToCustomerDialog(gui, customer, petTable);
			}

		});

		// Remove
		removePetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Tier t = customer.getTiere().get(petTable.getSelectedRow());
				t.setFkKunde(-1);
				try {
					new TierDataMapper().save(t);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ValidationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				customer.getTiere().remove(t);
				petTable.updateTableValues(customer.getTiere());
			}
		});
		
		checkPetButtonVisibility();
		
		return buttonPane;
	}
	
	private void checkPetButtonVisibility(){
		if(customer.getID() > 0){
			addPetButton.setVisible(true);
			removePetButton.setVisible(true);
		}else{
			addPetButton.setVisible(false);
			removePetButton.setVisible(false);
		}
	}

}