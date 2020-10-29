package rsCy3App.rsMetabPPI1_4.internal;

import java.util.Properties;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CyAction;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.task.create.NewNetworkSelectedNodesOnlyTaskFactory;
import org.cytoscape.task.select.SelectFirstNeighborsTaskFactory;
import org.cytoscape.view.layout.CyLayoutAlgorithmManager;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.work.TaskManager;
import org.osgi.framework.BundleContext;

public class CyActivator extends AbstractCyActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		
		// Get necessary Cytoscape services
		CySwingApplication cytoscapeDesktopService
			= getService(context, CySwingApplication.class);
		CyApplicationManager cyApplicationManager
			= getService(context, CyApplicationManager.class);
		CyNetworkManager cyNetworkManager 
			= getService(context, CyNetworkManager.class);
		CyNetworkViewFactory networkViewFactory
			= getService(context, CyNetworkViewFactory.class);		
		CyNetworkViewManager networkViewManager
			= getService(context, CyNetworkViewManager.class);	
		
		NewNetworkSelectedNodesOnlyTaskFactory newNetfromSelNodesTFactory
			= getService(context, NewNetworkSelectedNodesOnlyTaskFactory.class);
		SelectFirstNeighborsTaskFactory sel1stNeiTFactory 
			= getService(context, SelectFirstNeighborsTaskFactory.class);
		
		CyLayoutAlgorithmManager cyLayoutManager
			= getService(context, CyLayoutAlgorithmManager.class);
		
		VisualMappingManager vmmServiceRef = getService(context, VisualMappingManager.class);
		
		TaskManager<?,?> taskManager = getService(context, TaskManager.class);
		
		RegServiceBag1 regServiceBag
		= new RegServiceBag1(cyApplicationManager,
				cyNetworkManager,
				networkViewFactory,
				networkViewManager,
				taskManager,
				newNetfromSelNodesTFactory,
				sel1stNeiTFactory,
				cyLayoutManager,
				vmmServiceRef);

		MetabPPIPanel metabPPIPanel = new MetabPPIPanel(regServiceBag);
		MenuActInvokePanel menuActInvokePanel
			= new MenuActInvokePanel(cytoscapeDesktopService,metabPPIPanel);

		registerService(context, metabPPIPanel, CytoPanelComponent.class, new Properties()); // Panel?
		registerService(context, menuActInvokePanel, CyAction.class, new Properties());      // Pull-down menu?		
		
	}

}
