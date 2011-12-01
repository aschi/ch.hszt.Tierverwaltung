package ch.hszt.tierverwaltung.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import ch.hszt.tierverwaltung.kunden.backend.Kunde;
import ch.hszt.tierverwaltung.tier.backend.Tier;

public class Navigation {
	private JTree tree;
	private MainGui gui;

	/**
	 * Builds the Tree. GUI is used to
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
		DefaultMutableTreeNode tierList = new DefaultMutableTreeNode("Tiere");
		DefaultMutableTreeNode kundeList = new DefaultMutableTreeNode("Kunden");

		top.add(tierList);
		top.add(kundeList);

		tree.scrollPathToVisible(new TreePath(top.getLastLeaf().getPath()));
		addListener();
	}

	private void addListener() {
		tree.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					// Get location
					TreePath p = tree.getPathForLocation(e.getX(), e.getY());
					TreeNode node = (TreeNode) p.getLastPathComponent();

					// Add actions
					// TODO: Better way to do this without if / else if?
					System.out.println(node.toString());
					if (node.toString().equals("Tiere")) {
						System.out.println("load tier overview...");
						gui.selectOverview(Tier.class.getSimpleName());
					} else if (node.toString().equals("Kunden")) {
						System.out.println("load kunden overview...");
						gui.selectOverview(Kunde.class.getSimpleName());
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
