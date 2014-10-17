package org.cytoscape.rsCy3App.rsMetabPPI1_12_1.internal;

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
	
	public double calc_hygecdf_pval(CyNode protnode,
			CyNetwork netall, CyNetwork netsub){
		
		List<CyNode>neighb_netall = netall.getNeighborList(protnode, CyEdge.Type.ANY);
		List<CyNode>neighb_netsub = netsub.getNeighborList(protnode, CyEdge.Type.ANY);

		CyTable nodetable_netall = netall.getDefaultNodeTable();
		CyTable nodetable_netsub = netsub.getDefaultNodeTable();	
		
		int num_enz_netall = 0;
		int num_enz_netsub = 0;
		
		for(CyNode node: netall.getNodeList()){
			
			String nodetype = nodetable_netall.getRow(node).get("Node type", String.class);
			if(nodetype.equals("Enzyme")){
				num_enz_netall ++;
				if(netsub.containsNode(node)){
					num_enz_netsub ++;
				}
			}

		}
		
		HypergeometricDistribution hyged =
				new HypergeometricDistribution(num_enz_netall,
						num_target_netall, num_enz_netsub);
		
		System.out.println(hyged.upperCumulativeProbability(5));

		
	}	
	
}
