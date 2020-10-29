package org.cytoscape.rsCy3App.rsMetabPPI1_10_5.internal;

import java.util.HashMap;
import java.util.HashSet;

import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;

public class Rec_NetNames1 extends AbstractTask {
	
	private RegServiceBag1_3 rSB;
	// private CyNetwork metabPPI_cynet = null;
	private HashMap<String, Object> tsi_h;
	
	public Rec_NetNames1(RegServiceBag1_3 regServiceBag,
			HashMap<String, Object> task_share_info_h){

		this.rSB   = regServiceBag;
		this.tsi_h = task_share_info_h;
		
	}
	
	@Override
	public void run(TaskMonitor taskMonitor) throws Exception {

		// Generate subnetwork from selected nodes
		HashSet<String> netNameSetBefore = rsCy3App_Usefuls1.getNetNames(rSB);
		tsi_h.put("netNameSetBefore", netNameSetBefore);		
		
	}

}
