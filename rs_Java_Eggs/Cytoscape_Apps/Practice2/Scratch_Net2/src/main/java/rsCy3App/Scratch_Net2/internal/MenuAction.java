package rsCy3App.Scratch_Net2.internal;

import java.awt.event.ActionEvent;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.model.CyNode;


/**
 * Creates a new menu item under Apps menu section.
 *
 */
public class MenuAction extends AbstractCyAction {

	/**
	 * 
	 */

    private CyNetworkFactory netfactory;
    private CyNetworkManager netmanager;
	
	public MenuAction(CyApplicationManager cyApplicationManager, final String menuTitle,
					  CyNetworkFactory netfactory, CyNetworkManager netmanager) {
		
		super(menuTitle, cyApplicationManager, null, null);
		setPreferredMenu("Apps");
	
		this.netfactory = netfactory;
		this.netmanager = netmanager;	
		
	}

	public void actionPerformed(ActionEvent e) {

		// Write your own function here.

	    // Create a new network
	    CyNetwork myNet = netfactory.createNetwork();
         
	    // Set name for network
	    myNet.getRow(myNet).set(CyNetwork.NAME, "Scratch network 2");

	    // Add two nodes to the network
	    CyNode node1 = myNet.addNode();
	    CyNode node2 = myNet.addNode();

	    // Set name for new nodes
	    myNet.getRow(node1).set(CyNetwork.NAME, "Node A");
	    myNet.getRow(node2).set(CyNetwork.NAME, "Node B");
            
	    // Add an edge
	    myNet.addEdge(node1, node2, true);
	    // Add the network to Cytoscape
	    netmanager.addNetwork(myNet);		

	}
}
