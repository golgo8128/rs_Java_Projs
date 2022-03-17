package rsCy3App.SubgraphCreateTest7_2.internal;

import java.util.Set;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.model.CyNode;
import org.cytoscape.task.create.NewNetworkSelectedNodesOnlyTaskFactory;
import org.cytoscape.task.select.SelectFirstNeighborsTaskFactory;
import org.cytoscape.task.visualize.ApplyPreferredLayoutTaskFactory;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.Task;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskMonitor;

public class SubgCreationTask extends AbstractTask {

	private final CyApplicationManager cyApplicationManager;
	private final CyNetworkManager cyNetworkManager;
	private final CyNetworkViewFactory networkViewFactory;
	private final CyNetworkViewManager networkViewManager;	
	private final NewNetworkSelectedNodesOnlyTaskFactory newNetfromSelNodesTFactory;
	private final SelectFirstNeighborsTaskFactory sel1stNeiTFactory;	
	private final ApplyPreferredLayoutTaskFactory layoutTaskFactory;	
	
	public SubgCreationTask(CyApplicationManager cyApplicationManager,
				CyNetworkManager cyNetworkManager,
				CyNetworkViewFactory networkViewFactory,
				CyNetworkViewManager networkViewManager,				
				NewNetworkSelectedNodesOnlyTaskFactory newNetfromSelNodesTFactory,
				SelectFirstNeighborsTaskFactory sel1stNeiTFactory,
				ApplyPreferredLayoutTaskFactory layoutTaskFactory) {
		this.cyApplicationManager = cyApplicationManager;
		this.cyNetworkManager = cyNetworkManager;
		this.networkViewFactory = networkViewFactory;
		this.networkViewManager = networkViewManager;			
		this.newNetfromSelNodesTFactory = newNetfromSelNodesTFactory;
		this.sel1stNeiTFactory = sel1stNeiTFactory;
		this.layoutTaskFactory = layoutTaskFactory;	
	}

	public void run(final TaskMonitor taskMonitor) throws Exception {

		CyNetwork net1 = cyApplicationManager.getCurrentNetwork();		
		
		String[] targetNodeNames = {"Node F", "Node G", "Node H"};
		
		for (final CyNode node : net1.getNodeList()) {
			for(final String targetNodeName : targetNodeNames){
				if(net1.getRow(node).get(CyNetwork.NAME, String.class).equals(targetNodeName)){
					net1.getRow(node).set(CyNetwork.SELECTED, true);
				}

			}
		}
		
		TaskIterator itrNei = sel1stNeiTFactory.createTaskIterator(net1);
		Task selectionTaskNei = itrNei.next();
		selectionTaskNei.run(taskMonitor);
		
		TaskIterator itrNn = newNetfromSelNodesTFactory.createTaskIterator(net1);
		Task selectionTaskNn = itrNn.next();
		selectionTaskNn.run(taskMonitor);
		
		// Make the view of the network
		Set<CyNetwork> netSet = cyNetworkManager.getNetworkSet();
		Object net_objs[] = netSet.toArray();
		CyNetwork net_last = (CyNetwork) net_objs[net_objs.length - 1];
		CyNetworkView netview = networkViewFactory.createNetworkView(net_last);
		networkViewManager.addNetworkView(netview);
		
		// Layout network
		TaskIterator itrLay = layoutTaskFactory.createTaskIterator(arg0)createTaskIterator(netview);
		Task selectionTaskLay  = itrLay.next();
		selectionTaskLay.run(taskMonitor);
		
		netview.updateView();
	}

}