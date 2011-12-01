package ch.hszt.tierverwaltung.gui.forms;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import ch.hszt.tierverwaltung.backend.ValidationException;
import ch.hszt.tierverwaltung.gui.MainGui;
import ch.hszt.tierverwaltung.gui.listings.ReadOnlyTableModel;
import ch.hszt.tierverwaltung.gui.listings.TierOverview;
import ch.hszt.tierverwaltung.kunden.backend.Kunde;
import ch.hszt.tierverwaltung.tier.backend.Tier;

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

          fensterErzeugen();
          
    }
    
    public GuiKundenEintrag (Kunde kunde, MainGui gui) {
  	  this(gui);
  	  this.kunde = kunde;
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
    
    private void createTierValue() {
    	
    	kunde.setName(nameText.getText());
    	kunde.setVorname(vornameText.getText());
    	

  	  
    }
    
    private void closeFenster() {
  	  	fenster.dispose();
    }
    
    private void fensterErzeugen() {

          fenster = new JFrame("Kundeneintrag");
          fenster.setLocation(400, 300);
          fenster.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          fenster.setLayout(new BorderLayout());
         
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
          
          try {
			tiereTable = new TierOverview(gui);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
          tiereTable.updateTableValues(new ArrayList<Tier>());
          ReadOnlyTableModel model;
          

          tiere.setLabelFor(tiereTable);
          
          speichern.addActionListener(new ActionListener() {
            	@Override
            	public void actionPerformed(ActionEvent e) {
            		try {
	              			if(kunde == null){
	              				kunde = new Kunde();
	              			}
            				createTierValue();
							kunde.save();
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
      					kunde.delete();
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
          panel.add(tiere);
          panel.add(tiereTable);
          panel.add(speichern, 16);
          panel.add(loeschen, 17);
          
          fenster.getContentPane().add(panel);
          SpringUtilities.makeCompactGrid(panel, 9, 2, 5, 5, 5, 5);
          
          fenster.setVisible(true);
          fenster.pack();
    }    

}