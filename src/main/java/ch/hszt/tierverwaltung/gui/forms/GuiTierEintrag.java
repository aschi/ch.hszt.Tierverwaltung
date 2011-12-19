package ch.hszt.tierverwaltung.gui.forms;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import ch.hszt.tierverwaltung.backend.Tier;
import ch.hszt.tierverwaltung.backend.ValidationException;
import ch.hszt.tierverwaltung.database.tier.TierDataMapper;
import ch.hszt.tierverwaltung.gui.MainGui;

public class GuiTierEintrag {

      private JFrame frame;
      private JPanel panel;
      private JPanel areaPanel;
      private Tier tier;
      private MainGui gui;
      private JTextField nameText;
      private JTextField rasseText;
      private JTextField alterText;
      private JComboBox groesseCombo;
      private JComboBox tierCombo;
      private JTextField krankText;
      private JTextField essText;
      private JCheckBox auslaufText;
      private JTextArea umgangTierText;
      private JTextArea umgangMenschText;
      private JTextArea sonstigesText;
      private JTextField zusatzText;
      private JButton saveButton;
      private JButton deleteButton;
      
      private TierDataMapper tdm;
      
     

      public GuiTierEintrag(MainGui gui) {

          this.gui = gui;  
    	  fensterErzeugen();
    	  tdm = new TierDataMapper();
            
      }
      
      public GuiTierEintrag(Tier tier, MainGui gui) {
    	  this(gui);
    	  this.tier = tier;
    	  loadTierValue();
    	  tdm = new TierDataMapper();
    	  
      }
      
      private void loadTierValue() {
    	  tierCombo.setSelectedItem(tier.getArt());
    	  rasseText.setText(tier.getRasse());
    	  nameText.setText(tier.getName());
    	  alterText.setText(String.valueOf(tier.getTieralter()));
    	  groesseCombo.setSelectedIndex(tier.getGroesseID());
    	  tierCombo.setName(tier.getArt());
    	  krankText.setText(tier.getKrankheitsbild());
    	  essText.setText(tier.getEssgewohnheit());
    	  if (tier.getAuslauf() == '1') {
    		  auslaufText.setSelected(true);
    	  } else {
    		  auslaufText.setSelected(false);
    	  }
    	  auslaufText.setText(String.valueOf(tier.getAuslauf()));
    	  umgangTierText.setText(tier.getUmgangTier());
    	  umgangMenschText.setText(tier.getUmgangMensch());
    	  sonstigesText.setText(tier.getAnmerkungen());
    	  zusatzText.setText(String.valueOf(tier.getAdditionalCosts()));
      }
      
      private void createTierValue() {
    	  tier.setArt(tierCombo.getSelectedItem().toString());
    	  tier.setRasse(rasseText.getText());
    	  tier.setName(nameText.getText());
    	  if (alterText.getText() == null || alterText.getText().equals("")) {
    		  tier.setTieralter(-1);
    	  } else {
    		  tier.setTieralter(Integer.parseInt(alterText.getText()));
    	  }
    	  tier.setGroesseID(groesseCombo.getSelectedIndex());
    	  tier.setKrankheitsbild(krankText.getText());
    	  tier.setEssgewohnheit(essText.getText());
    	  if (auslaufText.isSelected()) {
    		  tier.setAuslauf('1');
    	  } else {
    		  tier.setAuslauf('0');
    	  }
    	  tier.setUmgangTier(umgangTierText.getText());
    	  tier.setUmgangMensch(umgangMenschText.getText());
    	  tier.setAnmerkungen(sonstigesText.getText());
    	  if (zusatzText.getText() == null) {
    		  tier.setAdditionalCosts(-1);
    	  }
    	  else if (zusatzText.getText().equals("")) {
    		  tier.setAdditionalCosts(0);
    	  } else {
    		  tier.setAdditionalCosts(Double.parseDouble(zusatzText.getText()));
    	  }
    	  
      }
      
      private void closeFenster() {
    	  	frame.dispose();
      }
      
      private void fensterErzeugen() {

            frame = new JFrame("Tiereintrag");
            frame.setLocation(400, 300);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            
            JPanel contentPane = new JPanel(new BorderLayout());
            JPanel buttonPane = new JPanel();
            
            panel = new JPanel(new SpringLayout());
            areaPanel = new JPanel(new SpringLayout());
            
            JLabel speciesLbl = new JLabel("Tierart");
            JLabel raceLbl = new JLabel("Rasse");
            JLabel nameLbl = new JLabel("Name");  
            JLabel ageLbl = new JLabel("Alter");
            JLabel sizeLbl = new JLabel("Groesse");
            JLabel diseaseLbl = new JLabel("Krankheitsbild");
            JLabel habbitsLbl = new JLabel("Gewohnheiten / Verhalten:");
            JLabel foodLbl = new JLabel("Essen");
            JLabel runLbl = new JLabel("Auslauf");
            JLabel contactAnimalsLbl = new JLabel("Umgang mit anderen Tieren");
            JLabel contactHumanLbl = new JLabel("Umgang mit Menschen");
            JLabel miscLbl = new JLabel("Sonstige Anmerkungen");
            JLabel additionalCostsLbl = new JLabel("Zusätzliche Kosten");
            
            saveButton = new JButton("Speichern");
            deleteButton = new JButton("Loeschen");
            
            rasseText = new JTextField();
            raceLbl.setLabelFor(rasseText);
            nameText = new JTextField();
            nameLbl.setLabelFor(nameText);
            alterText = new JTextField();
            ageLbl.setLabelFor(alterText);
            krankText = new JTextField();
            diseaseLbl.setLabelFor(krankText);
            essText = new JTextField();
            essText.setSize(20, 100);
            foodLbl.setLabelFor(essText);
            auslaufText = new JCheckBox("Ja");
            runLbl.setLabelFor(auslaufText);
            umgangTierText = new JTextArea(3, 20);
            JScrollPane scrollTier = new JScrollPane(umgangTierText);
            contactAnimalsLbl.setLabelFor(umgangTierText);
            umgangMenschText = new JTextArea(3, 20);
            JScrollPane scrollMensch = new JScrollPane(umgangMenschText);
            contactHumanLbl.setLabelFor(umgangMenschText);
            sonstigesText = new JTextArea(3, 20);
            JScrollPane scrollSonst = new JScrollPane(sonstigesText);
            miscLbl.setLabelFor(sonstigesText);
            zusatzText = new JTextField();
            additionalCostsLbl.setLabelFor(zusatzText);
            
            tierCombo = new JComboBox(Config.species);
            speciesLbl.setLabelFor(tierCombo);
            
            groesseCombo = new JComboBox(Config.petSize);
            sizeLbl.setLabelFor(groesseCombo);
            
            saveButton.addActionListener(new ActionListener() {
              	@Override
              	public void actionPerformed(ActionEvent e) {
              		try {
	              			if(tier == null){
	              				tier = new Tier();
	              			}
              				createTierValue();
							tdm.save(tier);
							
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
              
              deleteButton.addActionListener(new ActionListener() {
              	@Override
              	public void actionPerformed(ActionEvent e) {
              		try {
              				tdm.delete(tier);
        					
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
           
            panel.add(speciesLbl);
            panel.add(tierCombo);
            panel.add(raceLbl);
            panel.add(rasseText);
            panel.add(nameLbl);
            panel.add(nameText);
            panel.add(ageLbl);
            panel.add(alterText);
            panel.add(sizeLbl);
            panel.add(groesseCombo);
            panel.add(diseaseLbl);
            panel.add(krankText);
            panel.add(habbitsLbl);
            panel.add(new JLabel());
            panel.add(foodLbl);
            panel.add(essText);
            panel.add(runLbl);
            panel.add(auslaufText);
            panel.add(additionalCostsLbl);
            panel.add(zusatzText);
            areaPanel.add(contactAnimalsLbl);
            areaPanel.add(scrollTier);
            areaPanel.add(contactHumanLbl);
            areaPanel.add(scrollMensch);
            areaPanel.add(miscLbl);
            areaPanel.add(scrollSonst);
            
            frame.getContentPane().add(contentPane);
            SpringUtilities.makeCompactGrid(panel, 10, 2, 5, 5, 5, 5);
            SpringUtilities.makeCompactGrid(areaPanel, 3, 2, 5, 5, 5, 5);
            
            
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

} 