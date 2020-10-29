package rsCy3App.SubgraphCreateTest6.internal;

import java.util.Properties;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CyAction;
import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.task.create.NewNetworkSelectedNodesOnlyTaskFactory;
import org.cytoscape.task.select.SelectFirstNeighborsTaskFactory;
import org.cytoscape.work.TaskManager;
import org.osgi.framework.BundleContext;

public class CyActivator extends AbstractCyActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		
		// Get necessary Cytoscape services
		CyApplicationManager cyApplicationManager
			= getService(context, CyApplicationManager.class);
		NewNetworkSelectedNodesOnlyTaskFactory newNetfromSelNodesTFactory
			= getService(context, NewNetworkSelectedNodesOnlyTaskFactory.class);
		SelectFirstNeighborsTaskFactory sel1stNeiTFactory 
			= getService(context, SelectFirstNeighborsTaskFactory.class);
		
		TaskManager<?,?> taskManager = getService(context, TaskManager.class);
		
		// Instance for original App.
		MenuActionSubgCreation action = 
				new MenuActionSubgCreation(cyApplicationManager, 
						taskManager,
						newNetfromSelNodesTFactory,
						sel1stNeiTFactory,
						"Subg. extract test 6");		
		
		// Register original services to Cytoscape
		registerService(context, action, CyAction.class, new Properties()); 					

	}

}
