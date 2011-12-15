package ch.hszt.tierverwaltung.gui.forms;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

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
import ch.hszt.tierverwaltung.gui.MainGui;
import ch.hszt.tierverwaltung.gui.listings.TierOverview;

public class GuiKundenEintrag {
	
    private JFrame fenster;
    private JPanel panel;
    private Kunde kunde;
    private MainGui gui;
    private JTextField nameText;
    private JTextField vornameText;
    private JTextField adresseText;
    private JTextField plzText;
    private JTextField ortText;
    private JTextField telnoText;
    private JTextField emailText;
    private TierOverview tiereTable;
    private JButton speichern;
    private JButton loeschen;
    
    public GuiKundenEintrag(MainGui gui) {
    	createFrame();
    }
    
    public GuiKundenEintrag (Kunde kunde, MainGui gui) {
    	this.kunde = kunde;
    	createFrame();
  	   	loadKundeValue();
    }
    
    private void loadKundeValue() {
    	
    	
  	  nameText.setText(kunde.getName());
  	  vornameText.setText(kunde.getVorname());
  	  adresseText.setText(kunde.getAdresse());
  	  plzText.setText(kunde.getPlz());
  	  ortText.setText(kunde.getOrt());
  	  telnoText.setText(kunde.getTelefon());
  	  emailText.setText(kunde.getEMail());
  	  tiereTable.updateTableValues(kunde.getTiere());
  	  
    }
    
    private void createKundeValue() {
    	
    	kunde.setName(nameText.getText());
    	kunde.setVorname(vornameText.getText());
    	kunde.setAdresse(adresseText.getText());
    	kunde.setPlz(plzText.getText());
    	kunde.setOrt(ortText.getText());
    	kunde.setTelefon(telnoText.getText());
    	kunde.setEMail(emailText.getText());
  	  
    }
    
    private void closeFenster() {
  	  	fenster.dispose();
    }
    
    private void createFrame() {

          fenster = new JFrame("Kundeneintrag");
          fenster.setLocation(400, 300);
          fenster.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          fenster.setLayout(new BorderLayout());
          
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
          JLabel tiere = new JLabel("Tiere");
          
          speichern = new JButton("Speichern");
          loeschen = new JButton("Loeschen");
          
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
          
          String[] columnNames = {"Name", "Rasse"};
       
          tiereTable = new TierOverview(gui, kunde.getTiere(), createButtonPane());
          //tiereTable.updateTableValues(new ArrayList<Tier>());
          //tiereTable.setButtonPane(createButtonPane());
          
          
          speichern.addActionListener(new ActionListener() {
            	@Override
            	public void actionPerformed(ActionEvent e) {
            		try {
	              			if(kunde == null){
	              				kunde = new Kunde();
	              			}
            				createKundeValue();
            				new KundeDataMapper().save(kunde);
            				
         					synchronized (gui.getOverviewUpdater()) {
        						gui.getOverviewUpdater().notify();
							}
            				
							String meldung = "Erfolgreich gespeichert";
							JOptionPane.showMessageDialog(null, meldung, "Information", JOptionPane.INFORMATION_MESSAGE);
            			} catch (SQLException e1) {
      		            	String meldung = "Beim Speichern in die Datenbank ist ein Fehler aufgetreten: " + e1.getStackTrace();
      		            	JOptionPane.showMessageDialog(null, meldung, "Betriebliche Benachrichtigung", JOptionPane.ERROR_MESSAGE);
      					
      				} catch (ValidationException e1) {
								String meldung2 = e1.createErrorMsg(e1);
								JOptionPane.showMessageDialog(null, meldung2, "Betriebliche Benachrichtigung", JOptionPane.ERROR_MESSAGE);
						}
            	}
            });
            
            loeschen.addActionListener(new ActionListener() {
            	@Override
            	public void actionPerformed(ActionEvent e) {
            		try {
      					new KundeDataMapper().delete(kunde);
      					
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
          buttonPane.add(speichern);
          buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
          buttonPane.add(loeschen);
          
          tblPane.add(tiereTable);
          
          fenster.getContentPane().add(contentPane);
          SpringUtilities.makeCompactGrid(panel, 7, 2, 5, 5, 5, 5);
          contentPane.add(panel, BorderLayout.CENTER);
          contentPane.add(tblPane, BorderLayout.EAST);
          contentPane.add(buttonPane, BorderLayout.SOUTH);
          fenster.setVisible(true);
          fenster.pack();
    }    
    
    
    private JPanel createButtonPane(){
    	// Buttons
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JButton addButton = new JButton("Hinzufügen");
		JButton removeButton = new JButton("Entfernen");
		
		buttonPane.add(addButton);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(removeButton);
		
		//Add
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		//Remove
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				kunde.getTiere().remove(tiereTable.getSelectedRow());
				tiereTable.updateTableValues(kunde.getTiere());
			}
		});
		
		return buttonPane;
	}

}