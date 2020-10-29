package rsCy3App.Load_Attrib_Test1_2.internal;

import java.awt.event.ActionEvent;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyEdge;
// import org.cytoscape.model.CyTable;


/**
 * Creates a new menu item under Apps menu section.
 *
 */


public class MenuAction extends AbstractCyAction {

	private static final long serialVersionUID = -5398181468247015339L;
	
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
	    myNet.getRow(myNet).set(CyNetwork.NAME, "Test network 20130421");

	    // Add two nodes to the network
	    CyNode node1 = myNet.addNode();
	    CyNode node2 = myNet.addNode();
	    CyNode node3 = myNet.addNode();
	    
	    // Set name for new nodes
	    myNet.getRow(node1).set(CyNetwork.NAME, "Node A");
	    myNet.getRow(node2).set(CyNetwork.NAME, "Node B");
	    myNet.getRow(node3).set(CyNetwork.NAME, "Node C");
            
	    // Add an edge
	    CyEdge edge12 = myNet.addEdge(node1, node2, true);
	    CyEdge edge23 = myNet.addEdge(node2, node3, true);
	    
	    myNet.getDefaultNodeTable().createColumn("Test column 1", String.class, false);
	    // CyTable nodeTable = myNet.getDefaultNodeTable();
	    myNet.getRow(node1).set("Test column 1", "Value A");
	    myNet.getRow(node2).set("Test column 1", "Value B");
	    
	    myNet.getDefaultEdgeTable().createColumn("Edge test attr.", String.class, false);
	    myNet.getRow(edge12).set("Edge test attr.", "Value C");
	    myNet.getRow(edge23).set("Edge test attr.", "Value D");
	    
	    // Add the network to Cytoscape
	    netmanager.addNetwork(myNet);
	    
	}
}