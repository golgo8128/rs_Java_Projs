package rsCy3App.SubgraphCreateTest6.internal;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.task.create.NewNetworkSelectedNodesOnlyTaskFactory;
import org.cytoscape.task.select.SelectFirstNeighborsTaskFactory;
import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskIterator;


public class SubgCreationTaskFactory extends AbstractTaskFactory {

	private CyApplicationManager cyApplicationManager;
	private NewNetworkSelectedNodesOnlyTaskFactory newNetfromSelNodesTFactory;
	private SelectFirstNeighborsTaskFactory sel1stNeiTFactory;
	
	public SubgCreationTaskFactory(CyApplicationManager cyApplicationManager,
			NewNetworkSelectedNodesOnlyTaskFactory fromSelection, 
			SelectFirstNeighborsTaskFactory sel1stNeiTFactory){
		
		this.cyApplicationManager = cyApplicationManager;
		this.newNetfromSelNodesTFactory = fromSelection;
		this.sel1stNeiTFactory = sel1stNeiTFactory;
		
	}
	
	@Override
	public TaskIterator createTaskIterator() {
		// TODO Auto-generated method stub
		return new TaskIterator(new SubgCreationTask(cyApplicationManager, newNetfromSelNodesTFactory, sel1stNeiTFactory));		
	} 		

}
