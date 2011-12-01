package ch.hszt.tierverwaltung.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

import ch.hszt.tierverwaltung.gui.listings.TierOverview;

public class MainGui {
	private JFrame frame;
	
	MainGui(){
		createFrame();
	}
	
	private void createFrame(){
		frame = new JFrame("Tierverwaltungssystem");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //Fullscreen
		
		frame.getContentPane().add(new JScrollPane(new Navigation(this).getTree()), BorderLayout.WEST);
		try {
			setCenterPane(new TierOverview());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		createMenuBar();
		
		frame.setVisible(true);
	}
	
	public void setCenterPane(Component comp){
		frame.getContentPane().add(new JScrollPane(comp), BorderLayout.CENTER);
		frame.validate();
		frame.repaint();
	}

	
	private void createMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		
		frame.setJMenuBar(menuBar);
		
		JMenu create = new JMenu("Erstellen");
		JMenu edit = new JMenu("Bearbeiten");
		
		JMenuItem createTier = new JMenuItem("Tier");
		JMenuItem createKunde = new JMenuItem("Kunde");
		
		JMenuItem delete = new JMenuItem("Eintrag löschen");
		JMenuItem editEntry = new JMenuItem("Eintrag bearbeiten");
		
		menuBar.add(create);
		menuBar.add(edit);
		
		create.add(createTier);
		create.add(createKunde);
	}
	
	
	
}
