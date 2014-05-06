package rsCy3App.Cy3AutoReadTSV1_1.internal;

import java.io.File;
import java.util.HashSet;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.model.CyNetworkView;

import rsCy3App.Cy3AutoReadTSV1_1.internal.rs_Java_Proj4_cp.general.datastruct.RSTable1;
import rsCy3App.Cy3AutoReadTSV1_1.internal.rs_Java_Proj4_cp.general.fileproc.NewFileAddedWatcher1_2;
import rsCy3App.Cy3AutoReadTSV1_1.internal.rs_Java_Proj4_cp.general.strproc.StrUtil1;


public final class NewFileAutoRead1 {

	private static NewFileAutoRead1 instance = null;
	private static NewFileAddedWatcher1_2 newfileaddedwatcher;
	private static String[] tpn_path_split = {".rs_Progs_data", "Cytoscape", "TPN", "attribs"};	

	private static RegServiceBag1_4 rSB = null;
	
	
	private NewFileAutoRead1(){
		
		String[] filnam_ends = {"_info.txt", "_nodes.tsv", "_edges.tsv"};

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
	
		HashSet<String> newfiles_wo_ends = newfileaddedwatcher.get_new_file_paths_wo_ends();
		
		for(String istr:newfiles_wo_ends){
			System.out.println(istr);
		
			/*
			CyNetwork cynet = rSB.cyNetworkFactory.createNetwork();
			cynet.getRow(cynet).set(CyNetwork.NAME, "Destroy me");		
			rSB.cyNetworkManager.addNetwork(cynet);			
			
			CyNetworkView cynetview = rSB.networkViewFactory.createNetworkView(cynet);
			rSB.networkViewManager.addNetworkView(cynetview);
		
			RSTable1 rstbl_nodes = new RSTable1();
			rstbl_nodes.read_table(rstbl_nodes.maketesttablefile(2), "\\t", false, true);
	
			RSTable1 rstbl_edges = new RSTable1();
			rstbl_edges.read_table(rstbl_nodes.maketesttablefile(3), "\\t", false, true);
			
			NetReadTSV1 netreadtsv = new NetReadTSV1(rSB);
			netreadtsv.make_node_table(cynet, rstbl_nodes);
			netreadtsv.make_edge_table(cynet, rstbl_edges);
			
			VizMap1_3_II vizmap = VizMap1_3_II.getInstance(rSB);
			vizmap.apply_VStyle_I(cynet);
			
			cynetview.updateView();				
		*/
		
		}
			
		return newfiles_wo_ends.size();
		
	}
	
	
}
