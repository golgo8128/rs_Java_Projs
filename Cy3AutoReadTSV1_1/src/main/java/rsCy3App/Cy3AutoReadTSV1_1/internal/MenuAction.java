package rsCy3App.Cy3AutoReadTSV1_1.internal;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.model.CyNetworkView;

import rsCy3App.Cy3AutoReadTSV1_1.internal.rs_Java_Proj4_cp.general.datastruct.RSTable1;


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
		JOptionPane.showMessageDialog(null, "NetReadTSV test ...");
		setName("NetTSVFile Auto On"); // This will change menu title.
		
		SwingPoller1 swingpoller = SwingPoller1.getInstance(rSB);
		swingpoller.turn_on();
		
	}
}
