package rsCy3App.Cy3AutoReadTSV1_1.internal;

import rsCy3App.Cy3AutoReadTSV1_1.internal.rs_Java_Proj4_cp.general.fileproc.NewFileAddedWatcher1;

public final class NewFileAutoRead1 {

	private static final NewFileAutoRead1 instance = new NewFileAutoRead1();
	private static final NewFileAddedWatcher1 newfileaddedwatcher;
		
	
	public static NewFileAutoRead1 getInstance(){
		return NewFileAutoRead1.instance;
	}

	private NewFileAutoRead1(){
		
		newfileaddedwatcher = new NewFileAddedWatcher1(File check_folder, String[] filnam_ends);
		
	}	
	
	
}
