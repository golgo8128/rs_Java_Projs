package rsCy3App.Cy3AutoReadTSV1_1.internal;

import java.io.File;
import java.util.HashSet;

import rsCy3App.Cy3AutoReadTSV1_1.internal.rs_Java_Proj4_cp.general.fileproc.NewFileAddedWatcher1;
import rsCy3App.Cy3AutoReadTSV1_1.internal.rs_Java_Proj4_cp.general.strproc.StrUtil1;


public final class NewFileAutoRead1 {

	private static NewFileAutoRead1 instance = null;
	private static NewFileAddedWatcher1 newfileaddedwatcher;
	private static String[] tpn_path_split = {".rs_Progs_data", "Cytoscape", "TPN", "attribs"};	
	
	private NewFileAutoRead1(){
		
		String[] filnam_ends = {"_info.txt", "_nodes.tsv", "_edges.tsv"};

		File watch_path = new File(System.getProperty("user.home"), (StrUtil1.strjoin(tpn_path_split, File.separator)));
		NewFileAutoRead1.newfileaddedwatcher = new NewFileAddedWatcher1(watch_path, filnam_ends);
		
	}	

	
	public static synchronized NewFileAutoRead1 getInstance(){
		
		if(instance == null){
			instance = new NewFileAutoRead1();
		}
		
		return NewFileAutoRead1.instance;
	}
	
	
	public int scan_and_read(){
	
		HashSet<String> newfiles_wo_ends = newfileaddedwatcher.get_new_file_paths_wo_ends();
		
		for(String istr:newfiles_wo_ends){
			System.out.println(istr);
		}
		
		
		return newfiles_wo_ends.size();
		
	}
	
	
}
