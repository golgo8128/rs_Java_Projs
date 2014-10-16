package org.cytoscape.rsCy3App.rsMetabPPI1_12_1.internal;

import java.util.HashMap;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.work.TaskMonitor;

public class MakeCyNetfromTables1 {

	private final RegServiceBag1_4 rSB;
	private CyNetwork myNet;
	private TaskMonitor tM;
	
	public MakeCyNetfromTables1(RegServiceBag1_4 rSB,
			CyNetwork myNet,
			TaskMonitor taskMonitor){
		
		this.myNet = myNet;
		this.rSB   = rSB;
		this.tM    = taskMonitor;
	}
	
	public void make_node_table(
			String[] classnames,
			String[] colnames,
			Object[][] values){
			
		CyNode nodes[] = new CyNode[values.length];
		for(int i = 0;i < values.length;i ++){
			nodes[i] = myNet.addNode();			
		}		
		
		tM.setStatusMessage("Adding nodes");
		
		for(int j = 0;j < colnames.length;j ++){
			
			if(classnames[j].equals("String")){
				myNet.getDefaultNodeTable().createColumn(colnames[j], String.class, false);
			}
			else if(classnames[j].equals("Double")){
				myNet.getDefaultNodeTable().createColumn(colnames[j], Double.class, false);			
			}
			else if(classnames[j].equals("Integer")){
				myNet.getDefaultNodeTable().createColumn(colnames[j], Integer.class, false);			
			}
			
			for(int i = 0;i < values.length;i ++){
				if(!(values[i][j] instanceof Double) || !(Double.isNaN((Double) values[i][j]))){
					myNet.getRow(nodes[i]).set(colnames[j], values[i][j]);
				}
				if(j == 0){
					myNet.getRow(nodes[i]).set(CyNetwork.NAME, values[i][j]);
					// Node attributes in the first column will automatically be set to CyNetwork.NAME
				}
			}			
		
			tM.setProgress(1.0*j / colnames.length);
			
		}
		
		rSB.eventHelper.flushPayloadEvents();
		
	}	
	
	public void make_edge_table(
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
		
		tM.setStatusMessage("Adding edges");
		
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

			tM.setProgress(1.0*j / colnames.length);
			
		}			
	
		rSB.eventHelper.flushPayloadEvents();
		
	}
	
	
}
