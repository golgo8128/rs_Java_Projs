package rsCy3App.Cy3ReadTSV1.internal;

import java.util.Properties;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CyAction;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.event.CyEventHelper;
import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.task.create.NewNetworkSelectedNodesOnlyTaskFactory;
import org.cytoscape.task.select.SelectFirstNeighborsTaskFactory;
import org.cytoscape.view.layout.CyLayoutAlgorithmManager;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.vizmap.VisualMappingFunctionFactory;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.view.vizmap.VisualStyleFactory;
import org.cytoscape.work.TaskManager;
import org.osgi.framework.BundleContext;

public class CyActivator extends AbstractCyActivator {

	@Override
	public void start(BundleContext bcontext) throws Exception {
		
		// Get necessary Cytoscape services
		CySwingApplication cytoscapeDesktopService
			= getService(bcontext, CySwingApplication.class);
		CyApplicationManager cyApplicationManager
			= getService(bcontext, CyApplicationManager.class);
		CyNetworkManager cyNetworkManager 
			= getService(bcontext, CyNetworkManager.class);
		CyNetworkFactory cyNetworkFactory 
			= getService(bcontext, CyNetworkFactory.class);
		CyNetworkViewFactory networkViewFactory
			= getService(bcontext, CyNetworkViewFactory.class);		
		CyNetworkViewManager networkViewManager
			= getService(bcontext, CyNetworkViewManager.class);	
		
		NewNetworkSelectedNodesOnlyTaskFactory newNetfromSelNodesTFactory
			= getService(bcontext, NewNetworkSelectedNodesOnlyTaskFactory.class);
		
		CyEventHelper eventHelper = getService(bcontext, CyEventHelper.class);		
		
		SelectFirstNeighborsTaskFactory sel1stNeiTFactory 
			= getService(bcontext, SelectFirstNeighborsTaskFactory.class);
		
		CyLayoutAlgorithmManager cyLayoutManager
			= getService(bcontext, CyLayoutAlgorithmManager.class);
		
		VisualMappingManager vmmServiceRef = getService(bcontext, VisualMappingManager.class);
		
		TaskManager<?,?> taskManager = getService(bcontext, TaskManager.class);
		
		VisualStyleFactory visualStyleFactoryServiceRef = getService(bcontext,VisualStyleFactory.class);	                
		VisualMappingFunctionFactory vmfFactoryC = getService(bcontext,VisualMappingFunctionFactory.class, "(mapping.type=continuous)");
		VisualMappingFunctionFactory vmfFactoryD = getService(bcontext,VisualMappingFunctionFactory.class, "(mapping.type=discrete)");
		VisualMappingFunctionFactory vmfFactoryP = getService(bcontext,VisualMappingFunctionFactory.class, "(mapping.type=passthrough)");
		
		
		RegServiceBag1_4 regServiceBag
			= new RegServiceBag1_4(cyApplicationManager,
					cytoscapeDesktopService,
					cyNetworkManager,
					cyNetworkFactory,
					networkViewFactory,
					networkViewManager,
					taskManager,
					eventHelper,
					vmmServiceRef,
					visualStyleFactoryServiceRef,
					vmfFactoryC,
					vmfFactoryD,
					vmfFactoryP,
					cyLayoutManager,			
					sel1stNeiTFactory,
					newNetfromSelNodesTFactory);
		
		MenuAction menuaction = new MenuAction(regServiceBag, "NetTSVFile Autoreader");
		registerService(bcontext, menuaction, CyAction.class, new Properties());      // Pull-down menu	

	}

}
