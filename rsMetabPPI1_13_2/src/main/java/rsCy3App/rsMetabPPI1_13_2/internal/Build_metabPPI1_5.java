package rsCy3App.rsMetabPPI1_13_2.internal;
import metabPPI.MetabPPI_EdgeTable_Info1_3;
import metabPPI.MetabPPI_NodeTable_Info1_3;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.TaskMonitor;


// This class should be singleton.
public final class Build_metabPPI1_5 {

	private static Build_metabPPI1_5 instance;	
	private static RegServiceBag1_4 rSB;
	private static CyNetwork gen_cynet;
	
	public static boolean done_layout_mboard = false;
	
	private Build_metabPPI1_5(){}; 
	
	public static synchronized Build_metabPPI1_5 getInstance(RegServiceBag1_4 rSB){
		if(instance == null){
			instance = new Build_metabPPI1_5();
			Build_metabPPI1_5.rSB = rSB;
		}
		else if(!rSB.equals(Build_metabPPI1_5.rSB)){
			throw new IllegalStateException("Service bag already set for building MetabPPI network");	
		}
		
		return instance;
	}	
	
	public static synchronized Build_metabPPI1_5 getInstance(){
		 if(instance == null){ 
			 throw new IllegalStateException("Service bag must be stated.");
	     }
		 
		 return instance;
	}	
	
	public CyNetwork build_net(TaskMonitor taskMonitor) {
			
		if(gen_cynet == null || !rsCy3App_Usefuls1.getNetNames(rSB).contains(MetabPPIPanel1_2.NETWORKNAME)){

			// Create a new network
			gen_cynet = rSB.cyNetworkFactory.createNetwork();
	
			
		    // Set name for network
		    gen_cynet.getRow(gen_cynet).set(CyNetwork.NAME, MetabPPIPanel1_2.NETWORKNAME);
	        
		    MakeCyNetfromTables1 makenetfromtbl = new MakeCyNetfromTables1(rSB, gen_cynet, taskMonitor);
		    makenetfromtbl.make_node_table(
		    		MetabPPI_NodeTable_Info1_3.clsnam_nodeattr,
		    		MetabPPI_NodeTable_Info1_3.colnam_nodeattr,
		    		MetabPPI_NodeTable_Info1_3.assemble_ObjArrayParts());

		    makenetfromtbl.make_edge_table(
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
	
}
