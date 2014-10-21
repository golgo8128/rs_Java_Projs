package org.cytoscape.rsCy3App.rsMetabPPI1_12_1.internal;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.math3.distribution.HypergeometricDistribution;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyTable;

public class AddHygecdf_pvals1 {

	private RegServiceBag1_4 rSB;
	
	public AddHygecdf_pvals1(RegServiceBag1_4 regServiceBag){	
	
		this.rSB   = regServiceBag;
	}
	
	public double calc_hygecdf_pval(CyNode protnode_netsub,
			CyNetwork netall, CyNetwork netsub){
		
		HashMap<String,CyNode> nodename2cynode_netall_h = rsCy3App_Usefuls1.get_nodename_to_cynode_h(netall);		
		HashMap<String,CyNode> nodename2cynode_netsub_h = rsCy3App_Usefuls1.get_nodename_to_cynode_h(netsub);		
		
		CyTable nodetable_netall = netall.getDefaultNodeTable();
		CyTable nodetable_netsub = netsub.getDefaultNodeTable();		
		
		String protnodename = 
				nodetable_netsub.getRow(protnode_netsub.getSUID()).get(CyNetwork.NAME, String.class);
		
		CyNode protnode_netall = nodename2cynode_netall_h.get(protnodename);
		
		System.out.printf("%s: %s %s\n", protnodename, 
						  protnode_netall.toString(), protnode_netsub.toString());
		
		List<CyNode>neighb_netall = netall.getNeighborList(protnode_netall, CyEdge.Type.ANY);
		List<CyNode>neighb_netsub = netsub.getNeighborList(protnode_netsub, CyEdge.Type.ANY);
		
		int num_enz_netall = 0;
		int num_enz_netsub = 0;
		int num_enz_target_netall = 0;
		int num_enz_target_netsub = 0;
		
		for(CyNode node_netall: neighb_netall){

			String nodename = nodetable_netall.getRow(node_netall.getSUID()).get(CyNetwork.NAME, String.class);
			Boolean intrx_bit = netall.containsEdge(protnode_netall, node_netall);
			
			String nodetype = nodetable_netall.getRow(node_netall.getSUID()).get("Node type", String.class);
			if(nodetype.equals("Enzyme")){
				num_enz_netall ++;
				if(intrx_bit){
					num_enz_target_netall += 1;
				}
				
				CyNode cynode_in_netsub = nodename2cynode_netsub_h.get(nodename);
				if(neighb_netsub.contains(cynode_in_netsub)){
					num_enz_netsub ++;
					if(intrx_bit){
						num_enz_target_netsub += 1;
					}
				}
			}
			
		}
		
		System.out.printf("%s: %d %d %d\n", protnode_netsub.toString(), 
						  num_enz_netall, num_enz_target_netall, num_enz_netsub);
		
		HypergeometricDistribution hyged =
				new HypergeometricDistribution(num_enz_netall,
						num_enz_target_netall, num_enz_netsub);
		
		return(hyged.upperCumulativeProbability(num_enz_target_netsub));
		
	}	
	
}
