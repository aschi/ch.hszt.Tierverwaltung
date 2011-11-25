package ch.hszt.tierverwaltung.gui.forms;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import ch.hszt.tierverwaltung.tier.backend.Tier;

public class GuiTierEintrag {

      private JFrame fenster;
      private JPanel panel;
      private Tier tier;
      private JTextField nameText;
      private JTextField rasseText;
      private JTextField alterText;
      private JComboBox groesseCombo;
      private JComboBox tierCombo;
      private JTextField krankText;
      private JTextField essText;
      private JTextField auslaufText;
      private JTextArea umgangTierText;
      private JTextArea umgangMenschText;
      private JTextArea sonstigesText;
      private JTextField zusatzText;
      private JButton speichern;
      private JButton loeschen;
      
      
      public static void main(String[] args) {

            new GuiTierEintrag();
      }

      public GuiTierEintrag() {

            fensterErzeugen();
            
      }
      
      public GuiTierEintrag (Tier tier) {
    	  this();
    	  this.tier = tier;
    	  loadTierValue();
    	  
      }
      
      private void loadTierValue() {
    	  rasseText.setText(tier.getRasse());
    	  nameText.setText(tier.getName());
    	  alterText.setText(String.valueOf(tier.getTieralter()));
    	  groesseCombo.setName(String.valueOf(tier.getGroesseID()));
    	  tierCombo.setName(tier.getArt());
    	  krankText.setText(tier.getKrankheitsbild());
    	  essText.setText(tier.getEssgewohnheit());
    	  auslaufText.setText(String.valueOf(tier.getAuslauf()));
    	  umgangTierText.setText(tier.getUmgangTier());
    	  umgangMenschText.setText(tier.getUmgangMensch());
    	  sonstigesText.setText(tier.getAnmerkungen());
    	  zusatzText.setText(String.valueOf(tier.getZusatzkosten()));
    	  
      }

      private void fensterErzeugen() {

            fenster = new JFrame("Tiereintrag");
            fenster.setLocation(400, 300);
            fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fenster.setLayout(new BorderLayout());
           
            panel = new JPanel(new SpringLayout());
            
            String[] petStrings = {"", "Katze", "Hund", "Hamster", "Hase", "Meerschweinchen"};
            String[] petHeights = {"", "kleiner 30cm", "kleiner 60cm", "kleiner 1m", "1m"};
           
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
            rasse.setLabelFor(rasseText);
            nameText = new JTextField();
            name.setLabelFor(nameText);
            alterText = new JTextField();
            alter.setLabelFor(alterText);
            krankText = new JTextField();
            krankheitsBild.setLabelFor(krankText);
            essText = new JTextField();
            essen.setLabelFor(essText);
            auslaufText = new JTextField();
            auslauf.setLabelFor(auslaufText);
            umgangTierText = new JTextArea();
            JScrollPane scrollTier = new JScrollPane(umgangTierText);
            umgangTier.setLabelFor(umgangTierText);
            umgangMenschText = new JTextArea();
            JScrollPane scrollMensch = new JScrollPane(umgangMenschText);
            umgangMensch.setLabelFor(umgangMenschText);
            sonstigesText = new JTextArea();
            JScrollPane scrollSonst = new JScrollPane(sonstigesText);
            sonstiges.setLabelFor(sonstigesText);
            zusatzText = new JTextField();
            zusatz.setLabelFor(zusatzText);
            
            tierCombo = new JComboBox(petStrings);
            //combo.setPreferredSize(new Dimension(167,20));
            tierart.setLabelFor(tierCombo);
            
            groesseCombo = new JComboBox(petHeights);
            groesseCombo.setPreferredSize(new Dimension(167,20));
            groesse.setLabelFor(groesseCombo);
            
            speichern.addActionListener(new ActionListener() {
              	@Override
              	public void actionPerformed(ActionEvent e) {
              		try {
        					tier.save();
        				} catch (SQLException e1) {
        					e1.printStackTrace();
        				}
              	}
              });
              
              loeschen.addActionListener(new ActionListener() {
              	@Override
              	public void actionPerformed(ActionEvent e) {
              		try {
        					tier.delete();
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