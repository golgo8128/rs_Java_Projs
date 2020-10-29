package rsCy3App.SubgraphCreateTest5_2.internal;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.task.create.NewNetworkSelectedNodesOnlyTaskFactory;
import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskIterator;


public class SubgCreationTaskFactory extends AbstractTaskFactory {

	private CyApplicationManager cyApplicationManager;
	private NewNetworkSelectedNodesOnlyTaskFactory fromSelection;
	
	public SubgCreationTaskFactory(CyApplicationManager cyApplicationManager,
			NewNetworkSelectedNodesOnlyTaskFactory fromSelection){
		
		this.cyApplicationManager = cyApplicationManager;
		this.fromSelection = fromSelection;
		
	}
	
	@Override
	public TaskIterator createTaskIterator() {
		// TODO Auto-generated method stub
		return new TaskIterator(new SubgCreationTask(cyApplicationManager, fromSelection));		
	} 		

}
