package org.cytoscape.myapp.scratch1_1.internal;

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
	private static final long serialVersionUID = -2480638807233148250L;
	private CyNetworkFactory factory;
	private CyNetworkManager netmanage;

	public MenuAction(CyApplicationManager cyApplicationManager, final String menuTitle,
					  CyNetworkFactory factory, CyNetworkManager networkManager) {
		
		super(menuTitle, cyApplicationManager, null, null);
		setPreferredMenu("Apps");
		this.factory = factory;
		this.netmanage = networkManager;
		
	}

	public void actionPerformed(ActionEvent e) {

		// Write your own function here.
		
		  // Create a new network
		  CyNetwork myNet = factory.createNetwork();
		  
		  // Set name for network
		  myNet.getRow(myNet).set(CyNetwork.NAME, "My network");

		  // Add two nodes to the network
		  CyNode node1 = myNet.addNode();
		  CyNode node2 = myNet.addNode();
		                
		  // Set name for new nodes
		  myNet.getRow(node1).set(CyNetwork.NAME, "Node1");
		  myNet.getRow(node2).set(CyNetwork.NAME, "Node2");
		                
		  // Add an edge
		  myNet.addEdge(node1, node2, true);

		  // Add the network to Cytoscape
		  netmanage.addNetwork(myNet);

	}
}
