package rsCyApp3.rsMetabPPI1_11.internal;


import java.util.Properties;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CyAction;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.application.swing.CytoPanelComponent;
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
	public void start(BundleContext context) throws Exception {
		
		final String rsmetabppiver = "v1.11.1";
		
		// Get necessary Cytoscape services
		CySwingApplication cytoscapeDesktopService
			= getService(context, CySwingApplication.class);
		CyApplicationManager cyApplicationManager
			= getService(context, CyApplicationManager.class);
		CyNetworkManager cyNetworkManager 
			= getService(context, CyNetworkManager.class);
		CyNetworkFactory cyNetworkFactory 
			= getService(context, CyNetworkFactory.class);
		CyNetworkViewFactory networkViewFactory
			= getService(context, CyNetworkViewFactory.class);		
		CyNetworkViewManager networkViewManager
			= getService(context, CyNetworkViewManager.class);	
		
		NewNetworkSelectedNodesOnlyTaskFactory newNetfromSelNodesTFactory
			= getService(context, NewNetworkSelectedNodesOnlyTaskFactory.class);
		
		CyEventHelper eventHelper = getService(context, CyEventHelper.class);		
		
		SelectFirstNeighborsTaskFactory sel1stNeiTFactory 
			= getService(context, SelectFirstNeighborsTaskFactory.class);
		
		CyLayoutAlgorithmManager cyLayoutManager
			= getService(context, CyLayoutAlgorithmManager.class);
		
		VisualMappingManager vmmServiceRef = getService(context, VisualMappingManager.class);
		
		TaskManager<?,?> taskManager = getService(context, TaskManager.class);
		
		VisualStyleFactory visualStyleFactoryServiceRef = getService(context,VisualStyleFactory.class);	                
		VisualMappingFunctionFactory vmfFactoryC = getService(context,VisualMappingFunctionFactory.class, "(mapping.type=continuous)");
		VisualMappingFunctionFactory vmfFactoryD = getService(context,VisualMappingFunctionFactory.class, "(mapping.type=discrete)");
		VisualMappingFunctionFactory vmfFactoryP = getService(context,VisualMappingFunctionFactory.class, "(mapping.type=passthrough)");
		
		
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
		

		MetabPPIPanel1_2 metabPPIPanel
			= new MetabPPIPanel1_2(regServiceBag, rsmetabppiver);
		MenuActInvokePanel1_2 menuActInvokePanel
			= new MenuActInvokePanel1_2(regServiceBag, rsmetabppiver, metabPPIPanel);

		registerService(context, metabPPIPanel, CytoPanelComponent.class, new Properties()); // Panel
		registerService(context, menuActInvokePanel, CyAction.class, new Properties());      // Pull-down menu		
		
	}

}

