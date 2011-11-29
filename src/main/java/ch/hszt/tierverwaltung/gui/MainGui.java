package ch.hszt.tierverwaltung.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.sql.SQLException;

import javax.swing.JFrame;
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
		
		frame.setVisible(true);
	}
	
	public void setCenterPane(Component comp){
		frame.getContentPane().add(new JScrollPane(comp), BorderLayout.CENTER);
		frame.validate();
		frame.repaint();
	}

	
	
	
}
