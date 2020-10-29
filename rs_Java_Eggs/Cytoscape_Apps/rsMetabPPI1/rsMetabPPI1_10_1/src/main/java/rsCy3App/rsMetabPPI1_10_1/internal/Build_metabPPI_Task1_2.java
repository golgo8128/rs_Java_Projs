package rsCy3App.rsMetabPPI1_10_1.internal;


import java.util.HashMap;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.layout.CyLayoutAlgorithm;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.Task;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskMonitor;

// This should be singleton class.
public class Build_metabPPI_Task1_2 extends AbstractTask {

	private final RegServiceBag1_2 rSB;
	private static CyNetwork last_gen_cynet;
	
	public Build_metabPPI_Task1_2(RegServiceBag1_2 rSB){
		
		this.rSB   = rSB;
		last_gen_cynet = null;
	}
	
	public static CyNetwork get_last_gen_cynet(){
		return last_gen_cynet;
	}
	
	@Override
	public void run(TaskMonitor taskMonitor) throws Exception {
			
	    // Create a new network
	    last_gen_cynet = rSB.cyNetworkFactory.createNetwork();
	    // Set name for network
	    last_gen_cynet.getRow(last_gen_cynet).set(CyNetwork.NAME, MetabPPIPanel.NETWORKNAME);
        
	    make_node_table(last_gen_cynet,
	    		MetabPPI_NodeTable_Info1.clsnam_nodeattr,
	    		MetabPPI_NodeTable_Info1.colnam_nodeattr,
	    		MetabPPI_NodeTable_Info1.values_nodeattr);
	    	    
	    make_edge_table(last_gen_cynet,
	    		MetabPPI_EdgeTable_Info1.clsnam_edgeattr,
	    		MetabPPI_EdgeTable_Info1.colnam_edgeattr,
	    		MetabPPI_EdgeTable_Info1.values_edgeattr);	   

	    
	    // Add the network to Cytoscape
	    rSB.cyNetworkManager.addNetwork(last_gen_cynet);		
		
		CyNetworkView netview = rSB.networkViewFactory.createNetworkView(last_gen_cynet);
		
		CyLayoutAlgorithm cylayoutAlgorithm = rSB.cyLayoutManager.getLayout("force-directed");
		TaskIterator itrLay = cylayoutAlgorithm.createTaskIterator(netview,
				cylayoutAlgorithm.getDefaultLayoutContext(),
				CyLayoutAlgorithm.ALL_NODE_VIEWS, null);
		Task selectionTaskLay  = itrLay.next();
		selectionTaskLay.run(taskMonitor);
		
		rSB.networkViewManager.addNetworkView(netview);
		
	}
	
	public HashMap<String,CyNode> nodename_to_cynode(CyNetwork myNet){
		
		HashMap<String,CyNode> nodename_to_cynode_h = new HashMap<String,CyNode>();
		
		for(CyNode cynode: myNet.getNodeList()){
			nodename_to_cynode_h.put(myNet.getRow(cynode).get(CyNetwork.NAME, String.class), cynode);
		}
			
		return(nodename_to_cynode_h);
		
	}	
	
	public void make_node_table(CyNetwork myNet,
			String[] classnames,
			String[] colnames,
			Object[][] values){
			
		CyNode nodes[] = new CyNode[values.length];
		for(int i = 0;i < values.length;i ++){
			nodes[i] = myNet.addNode();			
		}		
		
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

		}		
		
	}	
	
	public void make_edge_table(CyNetwork myNet,
			String[] classnames,
			String[] colnames,
			Object[][] values
			// The first two columns should be pair of nodes.
			){
	
		HashMap<String,CyNode> cynode_h = nodename_to_cynode(myNet);
		
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
		
	}	
	
}
