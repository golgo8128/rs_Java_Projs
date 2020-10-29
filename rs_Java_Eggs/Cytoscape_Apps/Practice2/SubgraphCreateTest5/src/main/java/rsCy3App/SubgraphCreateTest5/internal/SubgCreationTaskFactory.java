package rsCy3App.SubgraphCreateTest5.internal;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.task.create.NewNetworkSelectedNodesOnlyTaskFactory;
import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskIterator;


public class SubgCreationTaskFactory extends AbstractTaskFactory {

	@Override
	public TaskIterator createTaskIterator() {
		// TODO Auto-generated method stub
		return null;
	} 		
	
	public TaskIterator createTaskIterator(CyApplicationManager cyApplicationManager,
			NewNetworkSelectedNodesOnlyTaskFactory fromSelection) {
		
		return new TaskIterator(new SubgCreationTask(cyApplicationManager, fromSelection));
	
	}

}
