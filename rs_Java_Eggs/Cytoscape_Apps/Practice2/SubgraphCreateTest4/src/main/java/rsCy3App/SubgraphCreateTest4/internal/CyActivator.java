package rsCy3App.SubgraphCreateTest4.internal;

import java.util.Properties;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CyAction;
import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.task.create.NewNetworkSelectedNodesOnlyTaskFactory;
import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskManager;
import org.osgi.framework.BundleContext;


public class CyActivator extends AbstractCyActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		
		// Get necessary Cytoscape services
		CyApplicationManager cyApplicationManager
			= getService(context, CyApplicationManager.class);
		NewNetworkSelectedNodesOnlyTaskFactory fromSelection
			= getService(context, NewNetworkSelectedNodesOnlyTaskFactory.class);
		TaskManager<?,?> taskManager = getService(context, TaskManager.class);
		
		// Instance for original task.
		SubgCreationTaskFactory taskFactory = new SubgCreationTaskFactory();

		// Instance for original App.
		MenuActionSubgCreation action = 
				new MenuActionSubgCreation(cyApplicationManager, 
						taskManager, taskFactory, fromSelection, "Subg. extract test 4");		
		
		// Register original services to Cytoscape
		registerService(context, action, CyAction.class, new Properties()); 					
		registerService(context, taskFactory, TaskFactory.class, new Properties());
		
	}

}
