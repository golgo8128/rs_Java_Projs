package rsCy3App.Cy3ReadTSV1.internal;

import general.datastruct.RSTable1;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.model.CyNetwork;


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
		JOptionPane.showMessageDialog(null, "NetReadTSV test...");
		setName("Changed name title"); // This will change menu title.
		
		CyNetwork new_cynet = rSB.cyNetworkFactory.createNetwork();
		new_cynet.getRow(new_cynet).set(CyNetwork.NAME, "Destroy me");		
		rSB.cyNetworkManager.addNetwork(new_cynet);			
		
		RSTable1 rstbl = new RSTable1();
		
		NetReadTSV1 netreadtsv = new NetReadTSV1(rSB);
		rstbl.read_table(rstbl.maketesttablefile(2), "\\t", false, true);
		netreadtsv.make_node_table(new_cynet, rstbl);
		
	}
}
