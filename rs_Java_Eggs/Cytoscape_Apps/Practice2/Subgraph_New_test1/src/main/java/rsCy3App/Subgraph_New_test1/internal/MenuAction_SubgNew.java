package rsCy3App.Subgraph_New_test1.internal;

import java.awt.event.ActionEvent;
import java.util.Set;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.model.CyNode;


/**
 * Creates a new menu item under Apps menu section.
 *
 */
public class MenuAction_SubgNew extends AbstractCyAction {

	private final CyNetworkManager cyNetworkManager;	
	private final CyApplicationManager cyApplicationManager;
	
	public MenuAction_SubgNew(CyApplicationManager cyApplicationManager,
							  CyNetworkManager cyNetworkManager,
							  final String menuTitle) {
		
		super(menuTitle, cyApplicationManager, null, null);
		setPreferredMenu("Apps");
		
		this.cyApplicationManager = cyApplicationManager;
		this.cyNetworkManager = cyNetworkManager;
		
	}

	public void actionPerformed(ActionEvent e) {
		// CyNetwork net1 = generate_scratch_net1();

		CyNetwork net1 = cyApplicationManager.getCurrentNetwork();		
		
		String[] targetNodeNames = {"Node F", "Node G", "Node H"};
		
		for (final CyNode node : net1.getNodeList()) {
			for(final String targetNodeName : targetNodeNames){
				if(net1.getRow(node).get(CyNetwork.NAME, String.class).equals(targetNodeName)){
					net1.getRow(node).set(CyNetwork.SELECTED, true);
				}

			}
		}
		
		cyApplicationManager.getCurrentNetworkView().updateView();
	}
	
	public CyNetwork generate_scratch_net1(){
		
		// Just selects first network.
		Set<CyNetwork> netSet = cyNetworkManager.getNetworkSet();
		Object net_objs[] = netSet.toArray();
		CyNetwork net_1st = (CyNetwork) net_objs[0];
		
	    // Set name for network
	    net_1st.getRow(net_1st).set(CyNetwork.NAME, "Scratch network");

	    // Add two nodes to the network
	    CyNode node1  = net_1st.addNode();
	    CyNode node2  = net_1st.addNode();
	    CyNode node3  = net_1st.addNode();
	    CyNode node4  = net_1st.addNode();
	    CyNode node5  = net_1st.addNode();	    
	    CyNode node6  = net_1st.addNode();
	    CyNode node7  = net_1st.addNode();
	    CyNode node8  = net_1st.addNode();
	    CyNode node9  = net_1st.addNode();
	    CyNode node10 = net_1st.addNode();	
	    
	    // Set name for new nodes
	    net_1st.getRow(node1).set(CyNetwork.NAME,  "Node A");
	    net_1st.getRow(node2).set(CyNetwork.NAME,  "Node B");
	    net_1st.getRow(node3).set(CyNetwork.NAME,  "Node C");
	    net_1st.getRow(node4).set(CyNetwork.NAME,  "Node D");	    
	    net_1st.getRow(node5).set(CyNetwork.NAME,  "Node E");
	    net_1st.getRow(node6).set(CyNetwork.NAME,  "Node F");
	    net_1st.getRow(node7).set(CyNetwork.NAME,  "Node G");
	    net_1st.getRow(node8).set(CyNetwork.NAME,  "Node H");
	    net_1st.getRow(node9).set(CyNetwork.NAME,  "Node I");	    
	    net_1st.getRow(node10).set(CyNetwork.NAME, "Node J");
	    
	    // Add an edge
	    net_1st.addEdge(node1, node2,  true);
	    net_1st.addEdge(node1, node3,  true);
	    net_1st.addEdge(node4, node5,  true);	    
	    net_1st.addEdge(node6, node7,  true);
	    net_1st.addEdge(node6, node8,  true);
	    net_1st.addEdge(node7, node8,  true);
	    net_1st.addEdge(node7, node9,  true);	    
	    net_1st.addEdge(node7, node10, true);	
	    
	    return(net_1st);
	    
	}
		
}
