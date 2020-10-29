package rsCy3App.Subgraph_New_test2_2.internal;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.Task;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.task.create.NewNetworkSelectedNodesOnlyTaskFactory;

public class Subg_Create_Task1 extends AbstractTask {

	private final CyNetwork inetwork;
	private final NewNetworkSelectedNodesOnlyTaskFactory fromSelection;
	              
	public Subg_Create_Task1(CyNetwork inetwork, 
			NewNetworkSelectedNodesOnlyTaskFactory fromSelection){
		this.inetwork = inetwork;
		this.fromSelection = fromSelection;
	}
	
	
	@Override
	public void run(TaskMonitor taskMonitor) throws Exception {
		// TODO Auto-generated method stub

		TaskIterator itr = fromSelection.createTaskIterator(inetwork);
		Task selectionTask = itr.next();
		selectionTask.run(taskMonitor);

	}

}
