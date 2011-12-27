package ch.hszt.tierverwaltung.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ch.hszt.tierverwaltung.backend.Customer;
import ch.hszt.tierverwaltung.backend.Pet;
import ch.hszt.tierverwaltung.backend.Petspace;
import ch.hszt.tierverwaltung.gui.listings.CustomerOverview;
import ch.hszt.tierverwaltung.gui.listings.OverviewUpdater;
import ch.hszt.tierverwaltung.gui.listings.PetOverview;
import ch.hszt.tierverwaltung.gui.listings.PetSpaceOverview;

public class MainGui {
	private JFrame frame;
	private OverviewUpdater overviewUpdater;
	private JPanel cards;
	
	private final String TIERPANEL = "tier";
	private final String KUNDENPANEL = "kunde";
	
	public MainGui(){
		overviewUpdater = new OverviewUpdater();
		
		createFrame();
		
		new Thread(overviewUpdater).run();
	}
	
	public OverviewUpdater getOverviewUpdater(){
		return overviewUpdater;
	}
	
	private void createFrame(){
		frame = new JFrame("Tierverwaltungssystem");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //Fullscreen
		
		frame.getContentPane().add(new JScrollPane(new Navigation(this).getTree()), BorderLayout.WEST);
		
		try {
			CustomerOverview ko = new CustomerOverview(this);
			PetOverview to = new PetOverview(this);
			PetSpaceOverview pso = new PetSpaceOverview(this);
			
			overviewUpdater.registerOverview(to);
			overviewUpdater.registerOverview(ko);
			overviewUpdater.registerOverview(pso);
			
			cards = new JPanel(new CardLayout());
			cards.add(to, Pet.class.getSimpleName());
			cards.add(ko, Customer.class.getSimpleName());
			cards.add(pso, Petspace.class.getSimpleName());
						
			frame.getContentPane().add(new JScrollPane(cards), BorderLayout.CENTER);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		createMenuBar();
		
		frame.setVisible(true);
	}
	
	public void selectOverview(String panelSelection){
		CardLayout cl = (CardLayout)(cards.getLayout());
		cl.show(cards, panelSelection);
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
