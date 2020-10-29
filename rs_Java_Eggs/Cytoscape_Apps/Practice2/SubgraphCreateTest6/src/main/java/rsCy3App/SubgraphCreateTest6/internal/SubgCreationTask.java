package rsCy3App.SubgraphCreateTest6.internal;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.task.create.NewNetworkSelectedNodesOnlyTaskFactory;
import org.cytoscape.task.select.SelectFirstNeighborsTaskFactory;
import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.Task;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskMonitor;

public class SubgCreationTask extends AbstractTask {

	private final CyApplicationManager cyApplicationManager;
	private final NewNetworkSelectedNodesOnlyTaskFactory newNetfromSelNodesTFactory;
	private final SelectFirstNeighborsTaskFactory sel1stNeiTFactory;	
	
	public SubgCreationTask(CyApplicationManager cyApplicationManager,
				NewNetworkSelectedNodesOnlyTaskFactory newNetfromSelNodesTFactory,
				SelectFirstNeighborsTaskFactory sel1stNeiTFactory) {
		this.cyApplicationManager = cyApplicationManager;
		this.newNetfromSelNodesTFactory = newNetfromSelNodesTFactory;
		this.sel1stNeiTFactory = sel1stNeiTFactory;
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
		
		// How to make a view?

	}

}
