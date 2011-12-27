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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ch.hszt.tierverwaltung.backend.Customer;
import ch.hszt.tierverwaltung.backend.Pet;
import ch.hszt.tierverwaltung.backend.ValidationException;
import ch.hszt.tierverwaltung.database.pet.PetDataMapper;
import ch.hszt.tierverwaltung.gui.MainGui;
import ch.hszt.tierverwaltung.gui.listings.AssignedPetsOverview;
import ch.hszt.tierverwaltung.gui.listings.Overview;
import ch.hszt.tierverwaltung.gui.listings.UnassignedPetsOverview;

public class AddPetToCustomerDialog {
	private MainGui gui;
	private JFrame frame;
	private Overview<Pet> o;
	private Customer customer;
	private AssignedPetsOverview apo;
	
	public AddPetToCustomerDialog(MainGui gui, Customer customer, AssignedPetsOverview apo) {
		this.gui = gui;
		this.customer = customer;
		this.apo = apo;
		createFrame();
		
		// TODO Auto-generated constructor stub
	}
	
	private void createFrame(){
		frame = new JFrame("Tier auswählen...");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		try {
			o = new UnassignedPetsOverview(gui, createPetDialogButtonPane());			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		frame.getContentPane().add(o, BorderLayout.CENTER);
		frame.setSize(230, 300);
		frame.setVisible(true);
	}
	
	private JPanel createPetDialogButtonPane() {
		// Buttons
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JButton addButton = new JButton("Dem Kunden hinzufügen");
		buttonPane.add(addButton);

		// Add
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Pet t = o.getInput().get(o.getSelectedRow());
				t.setFkCustomer(customer.getID());
				
				try {
					new PetDataMapper().save(t);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(frame, "SQL Exception. Stirb");
				} catch (ValidationException e) {
					JOptionPane.showMessageDialog(frame, "Validation Exception. Stirb");
				}
				
				customer.getPets().add(t);
				
				frame.dispose();
				apo.updateTableValues(customer.getPets());
			}

		});


		return buttonPane;
	}

}
