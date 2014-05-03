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
				if(!(values[i][j] instanceof Double) || !(Double.isNaN((Double) values[i][j]))){
					myNet.getRow(nodes[i]).set(colnames[j], rstbl.get_elem_by_idx(i, j));
				}
				if(j == 0){
					myNet.getRow(nodes[i]).set(CyNetwork.NAME, values[i][j]);
					// Node attributes in the first column will automatically be set to CyNetwork.NAME
				}
			}			

		}
		
		rSB.eventHelper.flushPayloadEvents();
		
	}	
	
	public void make_edge_table(CyNetwork myNet,
			String[] classnames,
			String[] colnames,
			Object[][] values
			// The first two columns should be pair of nodes.
			){
	
		HashMap<String,CyNode> cynode_h = rsCy3App_Usefuls1.get_nodename_to_cynode_h(myNet);
		
		CyEdge edges[] = new CyEdge[values.length];
		for(int i = 0;i < values.length;i ++){
			edges[i] = myNet.addEdge(cynode_h.get(values[i][0]),
					                 cynode_h.get(values[i][1]),
					                 true);	
		}	
		
		for(int j = 2;j < colnames.length;j ++){
			
			if(classnames[j].equals("String")){
				if(!colnames[j].equals("interaction")){
					myNet.getDefaultEdgeTable().createColumn(colnames[j], String.class, false);
				}
			}
			else if(classnames[j].equals("Double")){
				myNet.getDefaultEdgeTable().createColumn(colnames[j], Double.class, false);			
			}
			else if(classnames[j].equals("Integer")){
				myNet.getDefaultEdgeTable().createColumn(colnames[j], Integer.class, false);			
			}
			
			for(int i = 0;i < values.length;i ++){
				if(!(values[i][j] instanceof Double) || !(Double.isNaN((Double) values[i][j]))){
					myNet.getRow(edges[i]).set(colnames[j], values[i][j]);
					if(colnames[j].equals("interaction")){
						myNet.getRow(edges[i]).set(CyNetwork.NAME,
								String.format("%s (%s) %s",
										      values[i][0], values[i][j], values[i][1]));
					}
				}
			}			

		}			
	
		rSB.eventHelper.flushPayloadEvents();
		
	}
	
}
