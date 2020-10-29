package rsCy3App.rsMetabPPI1_5.internal;

import java.util.HashSet;
import java.util.Set;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.layout.CyLayoutAlgorithm;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.vizmap.VisualStyle;
import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.Task;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskMonitor;

import rsCy3App.rsMetabPPI1_5.internal.rs_Java_Proj2_src_copy.general.set.rsHashStringSet1;
import rsCy3App.rsMetabPPI1_5.internal.rs_Java_Proj2_src_copy.general.strproc.SumUpUntilAvail1;

public class SubgCreationTask extends AbstractTask {

	private final RegServiceBag1 rSB;
	private final String[] targetNodeNames;
	
	public SubgCreationTask(RegServiceBag1 regServiceBag, String[] targetNodeNames){
		
		this.rSB = regServiceBag;
		this.targetNodeNames = targetNodeNames;
	}

	public HashSet<String> getNetNames(){
		
		Set<CyNetwork> netSet = rSB.cyNetworkManager.getNetworkSet();
		HashSet<String> netNameSet = new HashSet<String>();
	
		for(CyNetwork net:netSet){
			netNameSet.add(net.getRow(net).get(CyNetwork.NAME, String.class));
		}
		
		return netNameSet;
		
	}

	public String[] getNetNamesStrArray(){
		
		Set<CyNetwork> netSet = rSB.cyNetworkManager.getNetworkSet();
		String[] netNameStrs = new String[netSet.size()];
	
		int i = 0;
		for(CyNetwork net:netSet){
			netNameStrs[i] = net.getRow(net).get(CyNetwork.NAME, String.class);
			i ++;
		}
		
		return netNameStrs;
		
	}	
	
	
	public void run(final TaskMonitor taskMonitor) throws Exception {

		CyNetwork net1 = rSB.cyApplicationManager.getCurrentNetwork();		
		VisualStyle vs = rSB.vmmServiceRef.getCurrentVisualStyle();
				
		for (final CyNode node : net1.getNodeList()) {
			for(final String targetNodeName : targetNodeNames){
				if(net1.getRow(node).get(CyNetwork.NAME, String.class).equals(targetNodeName)){
					net1.getRow(node).set(CyNetwork.SELECTED, true);
				}

			}
		}
				
		// First neighbor
		TaskIterator itrNei = rSB.sel1stNeiTFactory.createTaskIterator(net1); // First try
		Task selectionTaskNei = itrNei.next();
		selectionTaskNei.run(taskMonitor);
		itrNei = rSB.sel1stNeiTFactory.createTaskIterator(net1); // Second try
		selectionTaskNei = itrNei.next();
		selectionTaskNei.run(taskMonitor);
		
		
		// Generate subnetwork from selected nodes
		HashSet<String> netNameSetBefore = getNetNames();
			
		TaskIterator itrNn = rSB.newNetfromSelNodesTFactory.createTaskIterator(net1);
		Task selectionTaskNn = itrNn.next();
		selectionTaskNn.run(taskMonitor);
		rsHashStringSet1 netNameSetAfter = new rsHashStringSet1((String[])getNetNames().toArray(new String[0]));
		
		String[] newNetNames = (String[])netNameSetAfter.diff(netNameSetBefore).toArray(new String[0]);
		String newNetName = newNetNames[0];
			
		
		// Make the view of the network
		Set<CyNetwork> netSet = rSB.cyNetworkManager.getNetworkSet();
		CyNetwork newNet = null;
		for(CyNetwork netelem:netSet){
			if(netelem.getRow(netelem).get(CyNetwork.NAME, String.class).equals(newNetName)){
				newNet = netelem;
				break;
			}
		}
		
		
		// Check whether newNet is null or not... Lazy ...
		
		CyNetworkView netview = rSB.networkViewFactory.createNetworkView(newNet);
		rSB.networkViewManager.addNetworkView(netview);
		SumUpUntilAvail1 suua = new SumUpUntilAvail1("MetabPPI net. #%d");
		String newSubnetName = suua.get_avail(getNetNames());
		newNet.getRow(newNet).set(CyNetwork.NAME, newSubnetName);
		
		
		// Layout network
		CyLayoutAlgorithm cylayoutAlgorithm = rSB.cyLayoutManager.getLayout("force-directed");
		TaskIterator itrLay = cylayoutAlgorithm.createTaskIterator(netview,
				cylayoutAlgorithm.getDefaultLayoutContext(),
				CyLayoutAlgorithm.ALL_NODE_VIEWS, null);
		Task selectionTaskLay  = itrLay.next();
		selectionTaskLay.run(taskMonitor);
	
		vs.apply(netview);
		
		netview.updateView();
				
	}

}
