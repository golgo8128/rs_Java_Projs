package rsCy3App.rsMetabPPI1_8_2.internal;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.task.create.NewNetworkSelectedNodesOnlyTaskFactory;
import org.cytoscape.task.select.SelectFirstNeighborsTaskFactory;
import org.cytoscape.view.layout.CyLayoutAlgorithmManager;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.work.TaskManager;

public class RegServiceBag1 {

	public final CyApplicationManager cyApplicationManager;
	public final CyNetworkManager cyNetworkManager;
	public final CyNetworkViewFactory networkViewFactory;
	public final CyNetworkViewManager networkViewManager;
	public final TaskManager<?,?> taskManager;
	public final NewNetworkSelectedNodesOnlyTaskFactory newNetfromSelNodesTFactory;
	public final SelectFirstNeighborsTaskFactory sel1stNeiTFactory;
	public final CyLayoutAlgorithmManager cyLayoutManager;
	public final VisualMappingManager vmmServiceRef;
	
	public RegServiceBag1(CyApplicationManager cyApplicationManager,
			CyNetworkManager cyNetworkManager,
			CyNetworkViewFactory networkViewFactory,
			CyNetworkViewManager networkViewManager,
			TaskManager<?,?> taskManager,
			NewNetworkSelectedNodesOnlyTaskFactory newNetfromSelNodesTFactory,
			SelectFirstNeighborsTaskFactory sel1stNeiTFactory,
			CyLayoutAlgorithmManager cyLayoutManager,
			VisualMappingManager vmmServiceRef){
		
		this.cyApplicationManager = cyApplicationManager;
		this.cyNetworkManager = cyNetworkManager;
		this.networkViewFactory = networkViewFactory;
		this.networkViewManager = networkViewManager;
		this.taskManager = taskManager;	
		this.newNetfromSelNodesTFactory = newNetfromSelNodesTFactory;
		this.sel1stNeiTFactory = sel1stNeiTFactory;
		this.cyLayoutManager = cyLayoutManager;
		this.vmmServiceRef = vmmServiceRef;
		
	}

}
