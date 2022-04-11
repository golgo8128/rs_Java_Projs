package rsCy3App.Cy3NetTSVAutoImport1_2.internal;

import java.awt.event.ActionEvent;

import org.cytoscape.application.swing.AbstractCyAction;


/**
 * Creates a new menu item under Apps menu section.
 *
 */
public class MenuAction extends AbstractCyAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final RegServiceBag1_4 rSB;
	
	public MenuAction(RegServiceBag1_4 regServiceBag, final String menuTitle) {
		
		super(menuTitle, regServiceBag.cyApplicationManager, null, null);
		setPreferredMenu("Apps");
	
		rSB = regServiceBag;
		
	}

	public void actionPerformed(ActionEvent e) {

		// Write your own function here.
		// JOptionPane.showMessageDialog(null, "NetReadTSV test ...");
		setName("Cy3NetTSVAutoImport On"); // This will change menu title.
		
		SwingPoller1_2 swingpoller = SwingPoller1_2.getInstance(rSB);
		swingpoller.turn_on();

	
		
	}
}
