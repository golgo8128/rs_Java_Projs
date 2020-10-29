package org.cytoscape.rsCy3App.internal;

import java.util.HashSet;
import java.util.Set;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.rsCy3App.internal.rs_Java_Proj2_src_copy.general.set.rsHashStringSet1;
import org.cytoscape.rsCy3App.internal.rs_Java_Proj2_src_copy.general.strproc.SumUpUntilAvail1;
import org.cytoscape.view.layout.CyLayoutAlgorithm;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.vizmap.VisualStyle;
import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.Task;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskMonitor;

public class SubgCreationTask1_2 extends AbstractTask {

	private final RegServiceBag1_3 rSB;

	private final int WAIT_SUBGC_SEC = 5;

	public SubgCreationTask1_2(RegServiceBag1_3 regServiceBag){

		this.rSB = regServiceBag;
	}


	public void run(final TaskMonitor taskMonitor) throws Exception {
		// Target nodes must be selected on the current view.
		
		Build_metabPPI1_4 bmetabPPI = Build_metabPPI1_4.getInstance();
		CyNetwork net1              = bmetabPPI.build_net();		
		VisualStyle vs              = VizMap1_3.get_vstyle();

		// Generate subnetwork from selected nodes
		HashSet<String> netNameSetBefore = rsCy3App_Usefuls1.getNetNames(rSB);

		TaskIterator itrNn = rSB.newNetfromSelNodesTFactory.createTaskIterator(net1);
		Task selectionTaskNn = itrNn.next();
		selectionTaskNn.run(taskMonitor);

		CyNetwork newNet = null;

		for(int second = 0;second < WAIT_SUBGC_SEC;second ++){
			rsHashStringSet1 netNameSetAfter = new rsHashStringSet1((String[])rsCy3App_Usefuls1.getNetNames(rSB).toArray(new String[0]));

			String[] newNetNames = (String[])netNameSetAfter.diff(netNameSetBefore).toArray(new String[0]);
			String newNetName = newNetNames[0];


			// Make the view of the network
			Set<CyNetwork> netSet = rSB.cyNetworkManager.getNetworkSet();

			for(CyNetwork netelem:netSet){
				if(netelem.getRow(netelem).get(CyNetwork.NAME, String.class).equals(newNetName)){
					newNet = netelem;
					break;
				}
			}

			// Check whether newNet is null or not
			if(newNet != null){ break; }

			try{
				Thread.sleep(1000);
			}
			catch (InterruptedException e){}

		}

		if(newNet == null){
			throw new IllegalStateException("New subnetwork was not generated for unknown reason.");
		}		


		CyNetworkView newnetview = rSB.networkViewFactory.createNetworkView(newNet);
		rSB.networkViewManager.addNetworkView(newnetview);			

		SumUpUntilAvail1 suua = new SumUpUntilAvail1("MetabPPI net. #%d");
		String newSubnetName = suua.get_avail(rsCy3App_Usefuls1.getNetNames(rSB));
		newNet.getRow(newNet).set(CyNetwork.NAME, newSubnetName);


		// Layout network
		CyLayoutAlgorithm cylayoutAlgorithm = rSB.cyLayoutManager.getLayout("force-directed");
		TaskIterator itrLay = cylayoutAlgorithm.createTaskIterator(newnetview,
				cylayoutAlgorithm.getDefaultLayoutContext(),
				CyLayoutAlgorithm.ALL_NODE_VIEWS, null);
		Task selectionTaskLay  = itrLay.next();
		selectionTaskLay.run(taskMonitor);

		vs.apply(newnetview);
		newnetview.updateView();

	}
}


