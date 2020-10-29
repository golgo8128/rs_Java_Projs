package rsCy3App.SubgraphCreateTest5.internal;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.task.create.NewNetworkSelectedNodesOnlyTaskFactory;
import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.Task;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskMonitor;

public class SubgCreationTask extends AbstractTask {

	private final CyApplicationManager cyApplicationManager;
	private final NewNetworkSelectedNodesOnlyTaskFactory fromSelection;
	
	public SubgCreationTask(CyApplicationManager cyApplicationManager,
				NewNetworkSelectedNodesOnlyTaskFactory fromSelection) {
		this.cyApplicationManager = cyApplicationManager;
		this.fromSelection = fromSelection;		
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
		
		TaskIterator itr = fromSelection.createTaskIterator(net1);
		Task selectionTask = itr.next();
		selectionTask.run(taskMonitor);
		
		// How to make a view?

	}

}
