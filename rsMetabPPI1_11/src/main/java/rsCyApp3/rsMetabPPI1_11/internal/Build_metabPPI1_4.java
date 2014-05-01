package rsCyApp3.rsMetabPPI1_11.internal;


import java.util.HashMap;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;


// This class should be singleton.
public final class Build_metabPPI1_4 {

	private static Build_metabPPI1_4 instance;	
	private static RegServiceBag1_4 rSB;
	private static CyNetwork gen_cynet;
	
	public static boolean done_layout_mboard = false;
	
	private Build_metabPPI1_4(){}; 
	
	public static synchronized Build_metabPPI1_4 getInstance(RegServiceBag1_4 rSB){
		if(instance == null){
			instance = new Build_metabPPI1_4();
			Build_metabPPI1_4.rSB = rSB;
		}
		else if(!rSB.equals(Build_metabPPI1_4.rSB)){
			throw new IllegalStateException("Service bag already set for building MetabPPI network");	
		}
		
		return instance;
	}	
	
	public static synchronized Build_metabPPI1_4 getInstance(){
		 if(instance == null){ 
			 throw new IllegalStateException("Service bag must be stated.");
	     }
		 
		 return instance;
	}	
	
	public CyNetwork build_net() {
			
		if(gen_cynet == null || !rsCy3App_Usefuls1.getNetNames(rSB).contains(MetabPPIPanel1_2.NETWORKNAME)){

			// Create a new network
			gen_cynet = rSB.cyNetworkFactory.createNetwork();
	
			
		    // Set name for network
		    gen_cynet.getRow(gen_cynet).set(CyNetwork.NAME, MetabPPIPanel1_2.NETWORKNAME);
	        
		    make_node_table(gen_cynet,
		    		MetabPPI_NodeTable_Info1_3.clsnam_nodeattr,
		    		MetabPPI_NodeTable_Info1_3.colnam_nodeattr,
		    		MetabPPI_NodeTable_Info1_3.assemble_ObjArrayParts());
		    	    
		    make_edge_table(gen_cynet,
		    		MetabPPI_EdgeTable_Info1_3.clsnam_edgeattr,
		    		MetabPPI_EdgeTable_Info1_3.colnam_edgeattr,
		    		MetabPPI_EdgeTable_Info1_3.assemble_ObjArrayParts());	   
	
		    
		    // Add the network to Cytoscape
		    rSB.cyNetworkManager.addNetwork(gen_cynet);		
		
		}
		
		if(rSB.networkViewManager.getNetworkViews(gen_cynet).isEmpty()){
			CyNetworkView netview = rSB.networkViewFactory.createNetworkView(gen_cynet);
			// netview.updateView();
			rSB.networkViewManager.addNetworkView(netview);
			done_layout_mboard = false;
		}
			
		return gen_cynet;
		
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
		
		rSB.eventHelper.flushPayloadEvents();
		
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
	
		rSB.eventHelper.flushPayloadEvents();
		
	}
	
}
