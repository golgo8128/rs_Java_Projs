package org.cytoscape.rsCy3App.rsMetabPPI1_10_5.internal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.rsCy3App.rsMetabPPI1_10_5.internal.rs_Java_Proj2_src_copy.general.set.rsHashStringSet1;
import org.cytoscape.rsCy3App.rsMetabPPI1_10_5.internal.rs_Java_Proj2_src_copy.general.strproc.SumUpUntilAvail1;
import org.cytoscape.view.layout.CyLayoutAlgorithm;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.vizmap.VisualStyle;
import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.Task;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskMonitor;

public class SubgDecorationTask1_3 extends AbstractTask {

	private final RegServiceBag1_3 rSB;
	private HashMap<String, Object> tsi_h;
	
	public SubgDecorationTask1_3(RegServiceBag1_3 regServiceBag,
			HashMap<String, Object> task_share_info_h){

		this.rSB = regServiceBag;
		this.tsi_h = task_share_info_h;	
		
	}


	public void run(final TaskMonitor taskMonitor) throws Exception {
		// Target nodes must be selected on the current view.
		
		Build_metabPPI1_4 bmetabPPI = Build_metabPPI1_4.getInstance();
		CyNetwork net1              = bmetabPPI.build_net();		
		VisualStyle vs              = VizMap1_3.get_vstyle();

		CyNetwork newNet = null;


		rsHashStringSet1 netNameSetAfter = new rsHashStringSet1((String[])rsCy3App_Usefuls1.getNetNames(rSB).toArray(new String[0]));
		
		String[] newNetNames = (String[])netNameSetAfter.diff((HashSet<String>) tsi_h.get("netNameSetBefore")).toArray(new String[0]);
		String newNetName = newNetNames[0];

		// Make the view of the network
		Set<CyNetwork> netSet = rSB.cyNetworkManager.getNetworkSet();

		for(CyNetwork netelem:netSet){
			if(netelem.getRow(netelem).get(CyNetwork.NAME, String.class).equals(newNetName)){
				newNet = netelem;
				break;
			}
		}

		if(newNet == null){
			throw new IllegalStateException("New subnetwork was not generated for unknown reason.");
		}		

		tsi_h.put("New subnet", newNet);

		CyNetworkView newnetview = rSB.networkViewFactory.createNetworkView(newNet);
		rSB.networkViewManager.addNetworkView(newnetview);			

		SumUpUntilAvail1 suua = new SumUpUntilAvail1("MetabPPI net. #%d");
		String newSubnetName = suua.get_avail(rsCy3App_Usefuls1.getNetNames(rSB));
		newNet.getRow(newNet).set(CyNetwork.NAME, newSubnetName);

		vs.apply(newnetview);
		newnetview.updateView();

		
		CyLayoutAlgorithm cylayoutAlgorithm = rSB.cyLayoutManager.getLayout("force-directed");		

		TaskIterator itrLaySubn
		= cylayoutAlgorithm.createTaskIterator(
				newnetview,
				cylayoutAlgorithm.getDefaultLayoutContext(),
				CyLayoutAlgorithm.ALL_NODE_VIEWS, null);
		Task laySubn_task = itrLaySubn.next();		
		this.insertTasksAfterCurrentTask(laySubn_task);
		
	}
}


