package rsCy3App.Cy3ReadTSV1.internal;


import general.datastruct.RSTable1;

import java.util.HashMap;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;


// This class should be singleton.
public final class NetReadTSV1 {

	private RegServiceBag1_4 rSB;
	
	public NetReadTSV1(RegServiceBag1_4 rSB){
		
		this.rSB = rSB;
	
	}; 
	
	public void make_node_table(CyNetwork myNet, RSTable1 rstbl){
			
		CyNode nodes[] = new CyNode[rstbl.get_nrows()];
		for(int i = 0;i < rstbl.get_nrows();i ++){
			nodes[i] = myNet.addNode();			
		}		
		
		for(int j = 0;j < rstbl.get_ncols();j ++){
						
			myNet.getDefaultNodeTable().createColumn(rstbl.get_collabels()[j], rstbl.get_coldatClasses()[j], false);
			
			for(int i = 0;i < rstbl.get_nrows();i ++){
				
				if(!(rstbl.get_coldatClasses()[j].equals(Double.class))
						|| !(Double.isNaN((Double) rstbl.get_elem_by_idx(i, j)))){
					myNet.getRow(nodes[i]).set(rstbl.get_collabels()[j], rstbl.get_elem_by_idx(i, j));
				}
				if(j == 0){
					myNet.getRow(nodes[i]).set(CyNetwork.NAME, rstbl.get_elem_by_idx(i, j));
					// Node attributes in the first column will automatically be set to CyNetwork.NAME
				}
			}			

		}
		
		rSB.eventHelper.flushPayloadEvents();
		
	}	
	
	public void make_edge_table(CyNetwork myNet, RSTable1 rstbl){
		// The first two columns should be pair of nodes.
		
		HashMap<String,CyNode> cynode_h = rsCy3App_Usefuls1.get_nodename_to_cynode_h(myNet);
		
		CyEdge edges[] = new CyEdge[rstbl.get_nrows()];
		for(int i = 0;i < rstbl.get_nrows();i ++)
			edges[i] = myNet.addEdge(cynode_h.get(rstbl.get_elem_by_idx(i,0)),
					                 cynode_h.get(rstbl.get_elem_by_idx(i,1)),
					                 true);	
		
		for(int j = 2;j < rstbl.get_ncols();j ++){
			if(!rstbl.get_collabels()[j].equals("interaction"))
				myNet.getDefaultEdgeTable().createColumn(rstbl.get_collabels()[j], rstbl.get_coldatClasses()[j], false);
			
			for(int i = 0;i < rstbl.get_nrows();i ++){
				if(!(rstbl.get_coldatClasses()[j].equals(Double.class))
						|| !(Double.isNaN((Double) rstbl.get_elem_by_idx(i, j)))){
					myNet.getRow(edges[i]).set(rstbl.get_collabels()[j], rstbl.get_elem_by_idx(i, j));
					if(rstbl.get_collabels()[j].equals("interaction")){
						myNet.getRow(edges[i]).set(CyNetwork.NAME,
								String.format("%s (%s) %s",
										rstbl.get_elem_by_idx(i, 0),
										rstbl.get_elem_by_idx(i, j),
										rstbl.get_elem_by_idx(i, 1)));
					}
				}
			}			

		}			
	
		rSB.eventHelper.flushPayloadEvents();
		
	}
	
}
