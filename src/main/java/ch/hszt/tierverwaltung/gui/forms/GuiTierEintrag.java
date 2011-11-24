package ch.hszt.tierverwaltung.gui.forms;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class GuiTierEintrag {

      private JFrame fenster;
      private JPanel panel;

      public static void main(String[] args) {

            new GuiTierEintrag();
      }

      public GuiTierEintrag() {

            fensterErzeugen();
            //fenster.setSize(600, 400);
            
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
            panel.add(tierart);
            JComboBox combo = new JComboBox(petStrings);
            combo.setPreferredSize(new Dimension(167,20));
            tierart.setLabelFor(combo);
            panel.add(combo);
            JLabel name = new JLabel("Name");          
            panel.add(name);
            JTextField nameText = new JTextField(15);
            panel.add(nameText);
            name.setLabelFor(nameText);
            JLabel alter = new JLabel("Alter");
            panel.add(alter);
            JTextField alterText = new JTextField(15);
            alter.setLabelFor(alterText);
            panel.add(alterText);
            JLabel groesse = new JLabel("Groesse");
            panel.add(groesse);
            JComboBox groesseCombo = new JComboBox(petHeights);
            groesseCombo.setPreferredSize(new Dimension(167,20));
            groesse.setLabelFor(groesseCombo);
            panel.add(groesseCombo);
            JLabel krankheitsBild = new JLabel("Krankheitsbild");
            panel.add(krankheitsBild);
            JTextField krankText = new JTextField(15);
            krankheitsBild.setLabelFor(krankText);
            panel.add(krankText);
            JLabel gewohnheiten = new JLabel("Gewohnheiten / Verhalten:");
            panel.add(gewohnheiten);
            JLabel essen = new JLabel("Essen");
            panel.add(essen);
            JTextField essText = new JTextField(15);
            essen.setLabelFor(essText);
            panel.add(essText);
            JLabel auslauf = new JLabel("Auslauf");
            panel.add(auslauf);
            JTextField auslaufText = new JTextField(15);
            auslauf.setLabelFor(auslaufText);
            panel.add(auslaufText);
            JLabel umgangTier = new JLabel("Umgang mit anderen Tieren");
            panel.add(umgangTier);
            JTextArea umgangTierText = new JTextArea(3,15);
            JScrollPane scrollTier = new JScrollPane(umgangTierText);
            umgangTier.setLabelFor(umgangTierText);
            panel.add(scrollTier);
            JLabel umgangMensch = new JLabel("Umgang mit Menschen");
            panel.add(umgangMensch);
            JTextArea umgangMenschText = new JTextArea(3,15);
            JScrollPane scrollMensch = new JScrollPane(umgangMenschText);
            umgangMensch.setLabelFor(umgangMenschText);
            panel.add(umgangMenschText);
            panel.add(scrollMensch);
            JLabel sonstiges = new JLabel("Sonstige Anmerkungen");
            panel.add(sonstiges);
            JTextArea sonstigesText = new JTextArea(3,15);
            JScrollPane scrollSonst = new JScrollPane(sonstigesText);
            sonstiges.setLabelFor(sonstigesText);
            panel.add(sonstigesText);
            panel.add(scrollSonst);
            JLabel zusatz = new JLabel("Zusätzliche Kosten");
            panel.add(zusatz);
            JTextField zusatzText = new JTextField(15);
            zusatz.setLabelFor(zusatzText);
            panel.add(zusatzText);
            
            fenster.getContentPane().add(panel);
            SpringUtilities.makeCompactGrid(panel, 12, 2, 5, 5, 5, 5);
            
            fenster.setVisible(true);
            fenster.pack();
      }
} 