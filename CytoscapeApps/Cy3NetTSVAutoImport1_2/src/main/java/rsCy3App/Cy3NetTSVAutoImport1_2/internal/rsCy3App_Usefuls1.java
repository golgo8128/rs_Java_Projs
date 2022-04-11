package rsCy3App.Cy3NetTSVAutoImport1_2.internal;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyRow;
import org.cytoscape.model.CyTable;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.vizmap.VisualStyle;

public class rsCy3App_Usefuls1 {

	
	public static HashSet<String> getNetNames(RegServiceBag1_4 rSB){
		
		Set<CyNetwork> netSet = rSB.cyNetworkManager.getNetworkSet();
		HashSet<String> netNameSet = new HashSet<String>();
	
		for(CyNetwork net:netSet){
			netNameSet.add(net.getRow(net).get(CyNetwork.NAME, String.class));
		}
		
		return netNameSet;
		
	}	

	
	public static HashSet<String> getVisualStyleNames(RegServiceBag1_4 rSB){
		
		Set<VisualStyle>vsSet = rSB.vmmServiceRef.getAllVisualStyles();	
		HashSet<String> vsNameSet = new HashSet<String>();
	
		for(VisualStyle vs:vsSet){
			vsNameSet.add(vs.getTitle());
		}
		
		return vsNameSet;
		
	}		
	
	
	public static CyNetworkView take_first_view(RegServiceBag1_4 rSB, CyNetwork net1){
	
		CyNetworkView[] netviews
			= (CyNetworkView[])rSB.networkViewManager.getNetworkViews(net1).toArray(new CyNetworkView[0]);
		if(netviews.length > 0){
			return netviews[0]; // Just take the first view.
		}
		else {
			return null;
		}
	}

	public static CyNode[] first_neighbors(CyNetwork net1, CyNode[] cynodes){
		// cynodes themselves will be included.
		
		HashSet<CyNode> cynode_set = new HashSet<CyNode>();
		
		for(CyNode cynode: cynodes){
			cynode_set.add(cynode);
			for(CyNode neicynode: net1.getNeighborList(cynode, CyEdge.Type.ANY)){
				cynode_set.add(neicynode);
			}
		}
		
		return (CyNode[])cynode_set.toArray(new CyNode[0]);
		
	}

	public static HashMap<String,CyNode> get_nodename_to_cynode_h(CyNetwork myNet){
		
		HashMap<String,CyNode> nodename_to_cynode_h = new HashMap<String,CyNode>();
		
		for(CyNode cynode: myNet.getNodeList()){
			nodename_to_cynode_h.put(myNet.getRow(cynode).get(CyNetwork.NAME, String.class), cynode);
		}
			
		return(nodename_to_cynode_h);
		
	}	
	
	public static CyNode[] get_cynodes_from_their_names(CyNetwork net1, String[] targetNodeNames){
		
		HashSet<String> targetNodeNameSet = new HashSet<String>(Arrays.asList(targetNodeNames));		
		Set<CyNode> targetNodeSet         = new HashSet<CyNode>();
		
		for(final String targetNodeName : targetNodeNameSet){
			for (final CyNode node : net1.getNodeList()) {
				if(net1.getRow(node).get(CyNetwork.NAME, String.class).equals(targetNodeName)){
					targetNodeSet.add(node);
				}
			}
		}
		
		return (CyNode[])targetNodeSet.toArray(new CyNode[0]);
		
	}
	
	public static Set<CyNode> getNodesWithValue(
			final CyNetwork net, final CyTable table,
			final String colname, final Object value){
		
		final Collection<CyRow> matchingRows = table.getMatchingRows(colname, value);
		final Set<CyNode> nodes = new HashSet<CyNode>();
		final String primaryKeyColname = table.getPrimaryKey().getName();
		for (final CyRow row : matchingRows)
		{
			final Long nodeId = row.get(primaryKeyColname, Long.class);
			if (nodeId == null)
				continue;
			final CyNode node = net.getNode(nodeId);
			if (node == null)
				continue;
			nodes.add(node);
		}
		return nodes;
	}		
	
}
