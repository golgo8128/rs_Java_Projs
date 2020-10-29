package rsCy3App.SubgraphCreateTest8.internal;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.task.create.NewNetworkSelectedNodesOnlyTaskFactory;
import org.cytoscape.task.select.SelectFirstNeighborsTaskFactory;
import org.cytoscape.view.layout.CyLayoutAlgorithmManager;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskIterator;



public class SubgCreationTaskFactory extends AbstractTaskFactory {

	private final CyApplicationManager cyApplicationManager;
	private final CyNetworkManager cyNetworkManager;
	private final CyNetworkViewFactory networkViewFactory;
	private final CyNetworkViewManager networkViewManager;
	private final NewNetworkSelectedNodesOnlyTaskFactory newNetfromSelNodesTFactory;
	private final SelectFirstNeighborsTaskFactory sel1stNeiTFactory;
	private final CyLayoutAlgorithmManager cyLayoutManager;	
	private final VisualMappingManager vmmServiceRef;
	
	public SubgCreationTaskFactory(CyApplicationManager cyApplicationManager,
			CyNetworkManager cyNetworkManager,
			CyNetworkViewFactory networkViewFactory,
			CyNetworkViewManager networkViewManager,
			NewNetworkSelectedNodesOnlyTaskFactory newNetfromSelNodesTFactory, 
			SelectFirstNeighborsTaskFactory sel1stNeiTFactory,
			CyLayoutAlgorithmManager cyLayoutManager,
			VisualMappingManager vmmServiceRef){
		
		this.cyApplicationManager = cyApplicationManager;
		this.cyNetworkManager = cyNetworkManager;
		this.networkViewFactory = networkViewFactory;
		this.networkViewManager = networkViewManager;		
		this.newNetfromSelNodesTFactory = newNetfromSelNodesTFactory;
		this.sel1stNeiTFactory = sel1stNeiTFactory;
		this.cyLayoutManager = cyLayoutManager;
		this.vmmServiceRef = vmmServiceRef;
	}
	
	@Override
	public TaskIterator createTaskIterator() {
		// TODO Auto-generated method stub
		return new TaskIterator(new SubgCreationTask(cyApplicationManager,
				cyNetworkManager, networkViewFactory, networkViewManager,
				newNetfromSelNodesTFactory, sel1stNeiTFactory, cyLayoutManager, 
				vmmServiceRef));		
	} 		

}
