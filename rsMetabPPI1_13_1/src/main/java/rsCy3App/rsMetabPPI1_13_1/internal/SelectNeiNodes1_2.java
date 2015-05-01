package rsCy3App.rsMetabPPI1_13_1.internal;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyTableUtil;

public class SelectNeiNodes1_2 {

	private RegServiceBag1_4 rSB;
	
	public SelectNeiNodes1_2(RegServiceBag1_4 regServiceBag){	
	
		this.rSB   = regServiceBag;
	}
		
		
	public HashSet<CyNode> doit(String[] lines){

		CyNetwork  metabPPI_cynet        = Build_metabPPI1_5.getInstance().build_net(null);
		// CyNetworkView metabPPI_cynetview = rsCy3App_Usefuls1.take_first_view(rSB, metabPPI_cynet);

		Set<CyNode> metabnodeset  = rsCy3App_Usefuls1.getNodesWithValue(
				metabPPI_cynet, metabPPI_cynet.getDefaultNodeTable(), "Node type", "Metabolite");			
		
		CyNode[] selCyNodes     = rsCy3App_Usefuls1.get_cynodes_from_their_names(metabPPI_cynet, lines);
		if(selCyNodes.length == 0){
			throw new IllegalStateException("No matching metabolite found.");
		} 		
		
		HashSet<CyNode> selCyNodesSet = new HashSet<CyNode>(Arrays.asList(selCyNodes));	
		CyNode[] selCyNodes_nei = rsCy3App_Usefuls1.first_neighbors(metabPPI_cynet, selCyNodes); // First neighbors
		selCyNodes_nei          = rsCy3App_Usefuls1.first_neighbors(metabPPI_cynet, selCyNodes_nei); // to the second neighbors
		
		for (final CyNode node : metabPPI_cynet.getNodeList()){ metabPPI_cynet.getRow(node).set(CyNetwork.SELECTED, false); }
		
		for (final CyNode node : selCyNodes_nei){
			if(!metabnodeset.contains(node) || selCyNodesSet.contains(node)){
				metabPPI_cynet.getRow(node).set(CyNetwork.SELECTED, true);
			}
			else {
				metabPPI_cynet.getRow(node).set(CyNetwork.SELECTED, false);
				/* Metabolites not in the target will NOT be selected. */
			}
		}		

		// VizMap1_3 metabPPI_vizmap = VizMap1_3.getInstance();
		// metabPPI_vizmap.apply_VStyle_I(metabPPI_cynet);
		// metabPPI_cynetview.updateView();			
		
		List<CyNode> selnodes = CyTableUtil.getNodesInState(metabPPI_cynet, "selected", true);
		return new HashSet<CyNode>(selnodes);
			
	}

}
