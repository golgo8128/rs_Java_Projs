package rsCy3App.Cy3AutoReadTSV1_1.internal;

import java.io.File;
import java.util.HashSet;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.model.CyNetworkView;

import rsCy3App.Cy3AutoReadTSV1_1.internal.rs_Java_Proj4_cp.general.datastruct.RSTable1;
import rsCy3App.Cy3AutoReadTSV1_1.internal.rs_Java_Proj4_cp.general.fileproc.NewFileAddedWatcher1_2;
import rsCy3App.Cy3AutoReadTSV1_1.internal.rs_Java_Proj4_cp.general.strproc.StrUtil1;
import rsCy3App.Cy3AutoReadTSV1_1.internal.rs_Java_Proj4_cp.general.strproc.SumUpUntilAvail1;


public final class NewFileAutoRead1 {

	private static NewFileAutoRead1 instance  = null;
	private static NewFileAddedWatcher1_2 newfileaddedwatcher;
	private static String[] tpn_path_split    = {".rs_Progs_data", "Cytoscape", "TPN", "attribs"};	
	private static final String[] filnam_ends = {"_ok.txt", "_nodes.tsv", "_edges.tsv"}; // Must be in this order.
	// *_ok.txt must be created at last step.
	
	private static RegServiceBag1_4 rSB = null;
	
	
	private NewFileAutoRead1(){
		
		File watch_path = new File(System.getProperty("user.home"), (StrUtil1.strjoin(tpn_path_split, File.separator)));
		NewFileAutoRead1.newfileaddedwatcher = new NewFileAddedWatcher1_2(watch_path, filnam_ends);
		
	}	

	public static synchronized NewFileAutoRead1 getInstance(RegServiceBag1_4 rSB){
		
		NewFileAutoRead1.rSB = rSB;
		
		if(instance == null){
			NewFileAutoRead1.instance = new NewFileAutoRead1();
		}
			
		return NewFileAutoRead1.instance;
	}
	
	
	public static synchronized NewFileAutoRead1 getInstance(){
		
		if(instance == null){
			throw new IllegalStateException("Service bag must be stated.");
		}
		
		return NewFileAutoRead1.instance;
	}
	
	
	public int scan_and_read(){
		// _ok.txt may not be complete at the time of polling.
				
		HashSet<String> newfiles_wo_ends = newfileaddedwatcher.get_new_file_paths_wo_ends();
		
		for(String istr:newfiles_wo_ends){
			System.out.println(istr);
			
			String bsname_wo_ends = (new File(istr)).getName();
			
			// Creates a new network
			CyNetwork newCynet = rSB.cyNetworkFactory.createNetwork();
			SumUpUntilAvail1 suua = new SumUpUntilAvail1(bsname_wo_ends + " #%d");
			String newNetName = suua.get_avail(rsCy3App_Usefuls1.getNetNames(rSB));
			newCynet.getRow(newCynet).set(CyNetwork.NAME, newNetName);		
			rSB.cyNetworkManager.addNetwork(newCynet);				

			// Creates a new network view				
			CyNetworkView cyNetView = rSB.networkViewFactory.createNetworkView(newCynet);
			rSB.networkViewManager.addNetworkView(cyNetView);

			
			// Reads node attribute table	
			RSTable1 rstbl_nodes = new RSTable1();
			rstbl_nodes.read_table(istr + filnam_ends[1], "\\t", false, true);

			// Reads edge attribute table	
			RSTable1 rstbl_edges = new RSTable1();
			rstbl_edges.read_table(istr + filnam_ends[2], "\\t", false, true);
			
			// Puts node and edge attributes onto the network	
			NetReadTSV1 netreadtsv = new NetReadTSV1(rSB);
			netreadtsv.make_node_table(newCynet, rstbl_nodes);
			netreadtsv.make_edge_table(newCynet, rstbl_edges);
			
			
			// Applies visual style			
			VizMap1_3_II vizmap = VizMap1_3_II.getInstance(rSB);
			vizmap.apply_VStyle_I(newCynet);
			
			
			cyNetView.updateView();				

		}
			
		return newfiles_wo_ends.size();
		
	}
	
	
}
