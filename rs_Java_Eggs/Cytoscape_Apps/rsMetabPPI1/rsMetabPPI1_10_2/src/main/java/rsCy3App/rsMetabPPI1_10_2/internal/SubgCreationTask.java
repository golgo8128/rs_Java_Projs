package rsCy3App.rsMetabPPI1_10_2.internal;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyRow;
import org.cytoscape.model.CyTable;
import org.cytoscape.model.CyTableUtil;
import org.cytoscape.view.layout.CyLayoutAlgorithm;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.vizmap.VisualStyle;
import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.Task;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskMonitor;

import rsCy3App.rsMetabPPI1_10_2.internal.rs_Java_Proj2_src_copy.general.set.rsHashStringSet1;
import rsCy3App.rsMetabPPI1_10_2.internal.rs_Java_Proj2_src_copy.general.strproc.SumUpUntilAvail1;

public class SubgCreationTask extends AbstractTask {

	private final RegServiceBag1_3 rSB;
	private final HashSet<String> targetNodeNameSet;
	
	private final int WAIT_SUBGC_SEC = 5;
	
	public SubgCreationTask(RegServiceBag1_3 regServiceBag, String[] targetNodeNames){
		
		this.rSB = regServiceBag;
		this.targetNodeNameSet = new HashSet<String>(Arrays.asList(targetNodeNames)); 
	}

	private static Set<CyNode> getNodesWithValue(
			final CyNetwork net, final CyTable table,
			final String colname, final Object value){
		
		final Collection<CyRow> matchingRows = table.getMatchingRows(colname, value);
		final Set<CyNode> nodes = new HashSet<CyNode>();
		final String primaryKeyColname = table.getPrimaryKey().getName();
		for (final CyRow row : matchingRows)
		{
			final Long nodeId = row.get(primaryKeyColname, Long.class);
			if (nodeId == null)
				continue;
			final CyNode node = net.getNode(nodeId);
			if (node == null)
				continue;
			nodes.add(node);
		}
		return nodes;
	}	

	
	public void run(final TaskMonitor taskMonitor) throws Exception {

		Build_metabPPI1_3 bmetabPPI = Build_metabPPI1_3.getInstance();
		CyNetwork net1 = bmetabPPI.build_net();		
		VisualStyle vs = VizMap1_2.get_vstyle();
		// CyNetworkView netview = rSB.networkViewFactory.createNetworkView(net1);
		CyNetworkView netview = rsCy3App_Usefuls1.take_first_view(rSB, net1);
		
		Set<CyNode> targetNodeSet = new HashSet<CyNode>();
		Set<CyNode> metabnodeset = getNodesWithValue(
				net1, net1.getDefaultNodeTable(), "Node type", "Metabolite");	

		for (final CyNode node : net1.getNodeList()){ net1.getRow(node).set(CyNetwork.SELECTED, false); }
		
		for(final String targetNodeName : targetNodeNameSet){
			for (final CyNode node : net1.getNodeList()) {
				if(net1.getRow(node).get(CyNetwork.NAME, String.class).equals(targetNodeName)){
					net1.getRow(node).set(CyNetwork.SELECTED, true);
					targetNodeSet.add(node);
				}
			}
		}
		
		// If no target was found ...
		if(targetNodeSet.isEmpty()){
			throw new IllegalStateException("No matching metabolite found.");
		} else {
		
			// First neighbor
			TaskIterator itrNei = rSB.sel1stNeiTFactory.createTaskIterator(net1); // First try
			Task selectionTaskNei = itrNei.next();
			selectionTaskNei.run(taskMonitor);
			itrNei = rSB.sel1stNeiTFactory.createTaskIterator(net1); // Second try
			selectionTaskNei = itrNei.next();
			selectionTaskNei.run(taskMonitor);
	
			List<CyNode> selnodes = CyTableUtil.getNodesInState(net1, "selected", true);
	
			for (final CyNode node : net1.getNodeList()){ net1.getRow(node).set(CyNetwork.SELECTED, false); }		
	
			/* Unselect metabolites not in target */
			for (final CyNode node : selnodes){
				if(!metabnodeset.contains(node) || targetNodeSet.contains(node)){
					net1.getRow(node).set(CyNetwork.SELECTED, true);
				}
			}
				
			netview.updateView();
				
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
		
//			CyNetworkView newnetview = null;
//			for(int second = 0;second < WAIT_SUBGC_SEC;second ++){
//				newnetview = rsCy3App_Usefuls1.take_first_view(rSB, newNet);
//				if(newnetview != null){ break; }
//				try{
//					  Thread.sleep(1000);
//				}
//				catch (InterruptedException e){}
//			}
//			
//			if(newnetview == null){
//				throw new IllegalStateException("New subnetwork view was not generated for unknown reason.");				
//			}
			
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

}
