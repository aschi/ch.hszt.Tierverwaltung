package ch.hszt.tierverwaltung.gui.forms;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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

import ch.hszt.tierverwaltung.backend.ValidationException;
import ch.hszt.tierverwaltung.gui.MainGui;
import ch.hszt.tierverwaltung.tier.backend.Tier;

public class GuiTierEintrag {

      private JFrame fenster;
      private JPanel panel;
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
      private JButton speichern;
      private JButton loeschen;
      
     

      public GuiTierEintrag(MainGui gui) {

          this.gui = gui;  
    	  fensterErzeugen();
            
      }
      
      public GuiTierEintrag(Tier tier, MainGui gui) {
    	  this(gui);
    	  this.tier = tier;
    	  loadTierValue();
    	  
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
    	  zusatzText.setText(String.valueOf(tier.getZusatzkosten()));
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
    		  tier.setZusatzkosten(-1);
    	  }
    	  else if (zusatzText.getText().equals("")) {
    		  tier.setZusatzkosten(0);
    	  } else {
    		  tier.setZusatzkosten(Double.parseDouble(zusatzText.getText()));
    	  }
    	  
      }
      
      private void closeFenster() {
    	  	fenster.dispose();
      }
      
      private void fensterErzeugen() {

            fenster = new JFrame("Tiereintrag");
            fenster.setLocation(400, 300);
            fenster.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            fenster.setLayout(new BorderLayout());
           
            panel = new JPanel(new SpringLayout());
            
            
            String[] petStrings = {"", "Katze", "Hund", "Hamster", "Hase", "Meerschweinchen"};
            String[] petHeights = {"", "< 30cm", "< 60cm", "> 100cm"};
           
            JLabel tierart = new JLabel("Tierart");
            JLabel rasse = new JLabel("Rasse");
            JLabel name = new JLabel("Name");  
            JLabel alter = new JLabel("Alter");
            JLabel groesse = new JLabel("Groesse");
            JLabel krankheitsBild = new JLabel("Krankheitsbild");
            JLabel gewohnheiten = new JLabel("Gewohnheiten / Verhalten:");
            JLabel essen = new JLabel("Essen");
            JLabel auslauf = new JLabel("Auslauf");
            JLabel umgangTier = new JLabel("Umgang mit anderen Tieren");
            JLabel umgangMensch = new JLabel("Umgang mit Menschen");
            JLabel sonstiges = new JLabel("Sonstige Anmerkungen");
            JLabel zusatz = new JLabel("Zusätzliche Kosten");
            
            speichern = new JButton("Speichern");
            loeschen = new JButton("Loeschen");
            
            rasseText = new JTextField();
            //rasseText.setSize(new Dimension(250,20));
            rasse.setLabelFor(rasseText);
            nameText = new JTextField();
            name.setLabelFor(nameText);
            alterText = new JTextField();
            alter.setLabelFor(alterText);
            krankText = new JTextField();
            krankheitsBild.setLabelFor(krankText);
            essText = new JTextField();
            essText.setSize(20, 100);
            essen.setLabelFor(essText);
            auslaufText = new JCheckBox("Ja");
            auslauf.setLabelFor(auslaufText);
            umgangTierText = new JTextArea(3, 20);
            JScrollPane scrollTier = new JScrollPane(umgangTierText);
            umgangTier.setLabelFor(umgangTierText);
            umgangMenschText = new JTextArea(3, 20);
            JScrollPane scrollMensch = new JScrollPane(umgangMenschText);
            umgangMensch.setLabelFor(umgangMenschText);
            sonstigesText = new JTextArea(3, 20);
            JScrollPane scrollSonst = new JScrollPane(sonstigesText);
            sonstiges.setLabelFor(sonstigesText);
            zusatzText = new JTextField();
            zusatz.setLabelFor(zusatzText);
            
            tierCombo = new JComboBox(petStrings);
            tierart.setLabelFor(tierCombo);
            
            groesseCombo = new JComboBox(petHeights);
            groesse.setLabelFor(groesseCombo);
            
            speichern.addActionListener(new ActionListener() {
              	@Override
              	public void actionPerformed(ActionEvent e) {
              		try {
	              			if(tier == null){
	              				tier = new Tier();
	              			}
              				createTierValue();
							tier.save();
							
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
        					tier.delete();
        					
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
           
            panel.add(tierart);
            panel.add(tierCombo);
            panel.add(rasse);
            panel.add(rasseText);
            panel.add(name);
            panel.add(nameText);
            panel.add(alter);
            panel.add(alterText);
            panel.add(groesse);
            panel.add(groesseCombo);
            panel.add(krankheitsBild);
            panel.add(krankText);
            panel.add(gewohnheiten);
            panel.add(new JLabel());
            panel.add(essen);
            panel.add(essText);
            panel.add(auslauf);
            panel.add(auslaufText);
            panel.add(umgangTier);
            panel.add(scrollTier);
            panel.add(umgangMensch);
            panel.add(scrollMensch);
            panel.add(sonstiges);
            panel.add(scrollSonst);
            panel.add(zusatz);
            panel.add(zusatzText);
            panel.add(speichern, 26);
            panel.add(loeschen, 27);
            
            fenster.getContentPane().add(panel);
            SpringUtilities.makeCompactGrid(panel, 14, 2, 5, 5, 5, 5);
            
            fenster.setVisible(true);
            fenster.pack();
      }    

} 