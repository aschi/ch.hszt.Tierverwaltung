package ch.hszt.tierverwaltung.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import ch.hszt.tierverwaltung.backend.Customer;
import ch.hszt.tierverwaltung.backend.Pet;
import ch.hszt.tierverwaltung.backend.Petspace;
import ch.hszt.tierverwaltung.backend.Stay;

public class Navigation {
	private JTree tree;
	private MainGui gui;

	/**
	 * Builds the Tree. GUI is used to add the action listener to switch
	 * overviews
	 * 
	 * @param gui
	 */
	public Navigation(MainGui gui) {
		this.gui = gui;
		// Top node
		DefaultMutableTreeNode top = new DefaultMutableTreeNode(
				"Tierverwaltung");
		tree = new JTree(top);

		// Nodes for listings
		DefaultMutableTreeNode petList = new DefaultMutableTreeNode("Tiere");
		DefaultMutableTreeNode customerList = new DefaultMutableTreeNode(
				"Kunden");
		DefaultMutableTreeNode petSpaceList = new DefaultMutableTreeNode(
				"Tierplatz");
		DefaultMutableTreeNode stayList = new DefaultMutableTreeNode(
				"Aufenthalt");

		top.add(petList);
		top.add(customerList);
		top.add(petSpaceList);
		top.add(stayList);

		tree.scrollPathToVisible(new TreePath(top.getLastLeaf().getPath()));
		addListener();
	}

	private void addListener() {
		tree.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					// Get location
					TreePath p = tree.getPathForLocation(e.getX(), e.getY());
					if (p != null) {
						TreeNode node = (TreeNode) p.getLastPathComponent();

						// Add actions
						// TODO: Better way to do this without if / else if?
						System.out.println(node.toString());
						if (node.toString().equals("Tiere")) {
							gui.selectOverview(Pet.class.getSimpleName());
						} else if (node.toString().equals("Kunden")) {
							gui.selectOverview(Customer.class.getSimpleName());
						} else if (node.toString().equals("Tierplatz")) {
							gui.selectOverview(Petspace.class.getSimpleName());
						} else if (node.toString().equals("Aufenthalt")) {
							gui.selectOverview(Stay.class.getSimpleName());
						}
					}
				}
			}
		});
	}

	/**
	 * Returns the generated JTree
	 * 
	 * @return the generated JTree
	 */
	public JTree getTree() {
		return tree;
	}
}
