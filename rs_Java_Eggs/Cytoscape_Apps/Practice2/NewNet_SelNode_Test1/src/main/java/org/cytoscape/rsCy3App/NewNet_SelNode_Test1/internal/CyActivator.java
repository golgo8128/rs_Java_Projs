package org.cytoscape.rsCy3App.NewNet_SelNode_Test1.internal;

import java.util.Properties;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.event.CyEventHelper;
import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.task.select.InvertSelectedNodesTaskFactory;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.work.TaskManager;
import org.osgi.framework.BundleContext;

public class CyActivator extends AbstractCyActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		
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
		VisualMappingManager vmmServiceRef
		= getService(context, VisualMappingManager.class);
		CyEventHelper eventHelper
			= getService(context, CyEventHelper.class);	
		TaskManager<?,?> taskManager
			= getService(context, TaskManager.class);
		
		InvertSelectedNodesTaskFactory invertSelectedNodesTaskFactory
			= getService(context, InvertSelectedNodesTaskFactory.class);
		
		MenuAction action = new MenuAction(cyApplicationManager,
				cyNetworkFactory,
				cyNetworkManager,
				networkViewFactory,
				networkViewManager,
				vmmServiceRef,
				eventHelper,
				taskManager,
				invertSelectedNodesTaskFactory,
				"NewNet_SelNode_Test");
		
		Properties properties = new Properties();
		
		registerAllServices(context, action, properties);
	}

}
