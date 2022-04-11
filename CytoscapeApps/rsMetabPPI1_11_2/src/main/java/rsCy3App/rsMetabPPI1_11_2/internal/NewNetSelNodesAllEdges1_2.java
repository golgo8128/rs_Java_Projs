package rsCy3App.rsMetabPPI1_11_2.internal;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyTable;
import org.cytoscape.view.model.CyNetworkView;

import rsCy3App.rsMetabPPI1_11_2.internal.rs_Java_Proj4_cp.general.strproc.SumUpUntilAvail1;

public class NewNetSelNodesAllEdges1_2 {

	private final RegServiceBag1_4 rSB;	
	private final String[] intact_colnames_node_strs = {"SUID", "selected"}; // {"SUID","shared name","selected"};
	private final String[] intact_colnames_edge_strs = {"SUID", "selected"}; // {"SUID","shared name","selected","shared interaction"};
	private final HashSet<String> intact_colnames_node_set;
	private final HashSet<String> intact_colnames_edge_set;
	
	
	public NewNetSelNodesAllEdges1_2(RegServiceBag1_4 rSB){
		this.rSB = rSB;
		
		intact_colnames_node_set = new HashSet<String>();
		for(String istr: intact_colnames_node_strs){
			intact_colnames_node_set.add(istr);
		}
		
		intact_colnames_edge_set = new HashSet<String>();
		for(String istr: intact_colnames_edge_strs){
			intact_colnames_edge_set.add(istr);
		}
		
	}
	
	public CyNetworkView CreateNewNetandView(CyNetwork src_cynet,
			Collection<CyNode> picknodes_set,
			String subnetname){
				
		CyTable src_nodetable = src_cynet.getDefaultNodeTable();
		CyTable src_edgetable = src_cynet.getDefaultEdgeTable();
				
		// Create a new network
		CyNetwork new_cynet = rSB.cyNetworkFactory.createNetwork();
		SumUpUntilAvail1 suua = new SumUpUntilAvail1(subnetname + " #%d");
		String newSubnetName = suua.get_avail(rsCy3App_Usefuls1.getNetNames(rSB));
		new_cynet.getRow(new_cynet).set(CyNetwork.NAME, newSubnetName);		
		rSB.cyNetworkManager.addNetwork(new_cynet);	

	    // Create new tables for the new network
		CyTable new_nodetable = new_cynet.getDefaultNodeTable();
		CyTable new_edgetable = new_cynet.getDefaultEdgeTable();		
		
		// Copy all node attribute names (columns)
		for(CyColumn src_nodecol: src_nodetable.getColumns()){
			// System.out.println("Node table column " + src_nodecol.getName());
			if(new_nodetable.getColumn(src_nodecol.getName()) == null){
				new_nodetable.createColumn(src_nodecol.getName(),
						src_nodecol.getType(),
						true);
				// System.out.println("Node table column created: " + src_nodecol.getName());
			}
		}
	
		// Copy all edge attribute names (columns)
		for(CyColumn src_edgecol: src_edgetable.getColumns()){
			// System.out.println("Edge table column " + src_edgecol.getName());
			if(new_edgetable.getColumn(src_edgecol.getName()) == null){
				new_edgetable.createColumn(src_edgecol.getName(),
						src_edgecol.getType(),
						true);
				// System.out.println("Edge table column created: " + src_edgecol.getName());
			}
		}		
	
		HashMap<CyNode, CyNode> src2new_node_h = new HashMap<CyNode, CyNode>();
		
	    for(CyNode inode: picknodes_set){
	    	// System.out.println("Dealing with node " + inode.toString());
	    	CyNode newnode = new_cynet.addNode();
	    	src2new_node_h.put(inode, newnode);
	    	Map<String,Object> colnam2obj = src_cynet.getRow(inode).getAllValues();
	    	for(String colnam: colnam2obj.keySet()){
	    		if(!intact_colnames_node_set.contains(colnam)){
	    			Object attrval = colnam2obj.get(colnam);
	    			new_nodetable.getRow(newnode.getSUID()).set(colnam, attrval);
	    	    	// System.out.println("Copied node attribute " + inode.toString() + " " + colnam);
	    		}
	    	}
	    }
	    
	    HashSet<CyEdge> pickedges_set = new HashSet<CyEdge>();
	    for(CyNode inode:picknodes_set){
	    	for(CyEdge iedge:src_cynet.getAdjacentEdgeIterable(inode, CyEdge.Type.ANY)){
	    		if(  (iedge.getSource().equals(inode) && picknodes_set.contains(iedge.getTarget())) 
	    		  || (iedge.getTarget().equals(inode) && picknodes_set.contains(iedge.getSource()))){
	    			pickedges_set.add(iedge);
	    		}
	    	}
	    }
	    
	    for(CyEdge pickedge: pickedges_set){
	    	CyNode node1_new = src2new_node_h.get(pickedge.getSource());
	    	CyNode node2_new = src2new_node_h.get(pickedge.getTarget());
	    	CyEdge edge_new  = new_cynet.addEdge(node1_new, node2_new, pickedge.isDirected());
	    	
	    	Map<String,Object> colnam2obj = src_cynet.getRow(pickedge).getAllValues();
	    	for(String colnam: colnam2obj.keySet()){
		    	// System.out.println("Dealing with edge column " + colnam);
		    	if(!intact_colnames_edge_set.contains(colnam)){
		    		Object attrval = colnam2obj.get(colnam);
		    		new_edgetable.getRow(edge_new.getSUID()).set(colnam, attrval);
		    		// System.out.println("Copied edge attribute " + edge_new.toString() + " " + colnam);	
		    	}
	    	}	    		
	    }
	    
		CyNetworkView new_netview = rSB.networkViewFactory.createNetworkView(new_cynet);
		// new_netview.updateView();
		rSB.networkViewManager.addNetworkView(new_netview);		
		
		return new_netview; // new_netview.getModel() will be new_cynet
		
	}

}
