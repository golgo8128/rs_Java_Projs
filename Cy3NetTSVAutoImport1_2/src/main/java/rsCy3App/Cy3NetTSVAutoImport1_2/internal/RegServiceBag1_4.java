package rsCy3App.Cy3NetTSVAutoImport1_2.internal;


import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.event.CyEventHelper;
import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.task.create.NewNetworkSelectedNodesOnlyTaskFactory;
import org.cytoscape.task.select.SelectFirstNeighborsTaskFactory;
import org.cytoscape.view.layout.CyLayoutAlgorithmManager;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.vizmap.VisualMappingFunctionFactory;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.view.vizmap.VisualStyleFactory;
import org.cytoscape.work.TaskManager;

public class RegServiceBag1_4 {

	public final CyApplicationManager cyApplicationManager;
	public final CySwingApplication cytoscapeDesktopService;
	public final CyNetworkManager cyNetworkManager;
	public final CyNetworkFactory cyNetworkFactory;
	public final CyNetworkViewFactory networkViewFactory;
	public final CyNetworkViewManager networkViewManager;
	public final TaskManager<?,?> taskManager;
	public final CyEventHelper eventHelper;
	public final NewNetworkSelectedNodesOnlyTaskFactory newNetfromSelNodesTFactory;
	public final SelectFirstNeighborsTaskFactory sel1stNeiTFactory;
	public final CyLayoutAlgorithmManager cyLayoutManager;
	public final VisualMappingManager vmmServiceRef;
	public final VisualStyleFactory visualStyleFactoryServiceRef;
	public final VisualMappingFunctionFactory vmfFactoryC;
	public final VisualMappingFunctionFactory vmfFactoryD;
	public final VisualMappingFunctionFactory vmfFactoryP;		
	
	public RegServiceBag1_4(CyApplicationManager cyApplicationManager,
			CySwingApplication cytoscapeDesktopService,
			CyNetworkManager cyNetworkManager,
			CyNetworkFactory cyNetworkFactory,
			CyNetworkViewFactory networkViewFactory,
			CyNetworkViewManager networkViewManager,
			TaskManager<?,?> taskManager,
			CyEventHelper eventHelper,
			VisualMappingManager vmmServiceRef,
			VisualStyleFactory visualStyleFactoryServiceRef,
			VisualMappingFunctionFactory vmfFactoryC,
			VisualMappingFunctionFactory vmfFactoryD,
			VisualMappingFunctionFactory vmfFactoryP,
			CyLayoutAlgorithmManager cyLayoutManager,			
			SelectFirstNeighborsTaskFactory sel1stNeiTFactory,
			NewNetworkSelectedNodesOnlyTaskFactory newNetfromSelNodesTFactory){

		this.cyApplicationManager = cyApplicationManager;
		this.cytoscapeDesktopService = cytoscapeDesktopService;
		this.cyNetworkManager = cyNetworkManager;
		this.cyNetworkFactory = cyNetworkFactory;
		this.networkViewFactory = networkViewFactory;
		this.networkViewManager = networkViewManager;
		this.taskManager = taskManager;
		this.eventHelper = eventHelper;
		this.newNetfromSelNodesTFactory = newNetfromSelNodesTFactory;
		this.sel1stNeiTFactory = sel1stNeiTFactory;
		this.cyLayoutManager = cyLayoutManager;
		this.vmmServiceRef = vmmServiceRef;
		this.visualStyleFactoryServiceRef = visualStyleFactoryServiceRef;
		this.vmfFactoryC = vmfFactoryC;
		this.vmfFactoryD = vmfFactoryD;
		this.vmfFactoryP = vmfFactoryP;

	}

}
