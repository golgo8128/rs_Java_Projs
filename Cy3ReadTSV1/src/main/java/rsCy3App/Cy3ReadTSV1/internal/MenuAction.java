package rsCy3App.Cy3ReadTSV1.internal;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.cytoscape.application.swing.AbstractCyAction;


/**
 * Creates a new menu item under Apps menu section.
 *
 */
public class MenuAction extends AbstractCyAction {

	private final RegServiceBag1_4 rSB;
	
	public MenuAction(RegServiceBag1_4 regServiceBag, final String menuTitle) {
		
		super(menuTitle, regServiceBag.cyApplicationManager, null, null);
		setPreferredMenu("Apps");
	
		rSB = regServiceBag;
		
	}

	public void actionPerformed(ActionEvent e) {

		// Write your own function here.
		JOptionPane.showMessageDialog(null, "Hello Cytoscape World!");
		setName("Changed name title"); // This will change menu title.
		
	}
}
