package rsCy3App.rsMetabPPI1_6.internal;

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

import rsCy3App.rsMetabPPI1_6.internal.rs_Java_Proj2_src_copy.general.set.rsHashStringSet1;
import rsCy3App.rsMetabPPI1_6.internal.rs_Java_Proj2_src_copy.general.strproc.SumUpUntilAvail1;

public class SubgCreationTask extends AbstractTask {

	private final RegServiceBag1 rSB;
	private final HashSet<String> targetNodeNameSet;
	
	public SubgCreationTask(RegServiceBag1 regServiceBag, String[] targetNodeNames){
		
		this.rSB = regServiceBag;
		this.targetNodeNameSet = new HashSet<String>(Arrays.asList(targetNodeNames)); 
	}

	public HashSet<String> getNetNames(){
		
		Set<CyNetwork> netSet = rSB.cyNetworkManager.getNetworkSet();
		HashSet<String> netNameSet = new HashSet<String>();
	
		for(CyNetwork net:netSet){
			netNameSet.add(net.getRow(net).get(CyNetwork.NAME, String.class));
		}
		
		return netNameSet;
		
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

		CyNetwork net1 = rSB.cyApplicationManager.getCurrentNetwork();		
		VisualStyle vs = rSB.vmmServiceRef.getCurrentVisualStyle();
		CyNetworkView netview = rSB.networkViewFactory.createNetworkView(net1);
		
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
		
		CyNetworkView newnetview = rSB.networkViewFactory.createNetworkView(newNet);
		rSB.networkViewManager.addNetworkView(newnetview);
		SumUpUntilAvail1 suua = new SumUpUntilAvail1("MetabPPI net. #%d");
		String newSubnetName = suua.get_avail(getNetNames());
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
