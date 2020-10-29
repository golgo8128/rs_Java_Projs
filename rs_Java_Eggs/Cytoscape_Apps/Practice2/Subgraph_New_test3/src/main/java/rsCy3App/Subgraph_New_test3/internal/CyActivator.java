package rsCy3App.Subgraph_New_test3.internal;

import java.util.Properties;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CyAction;
import org.cytoscape.model.CyNetworkManager;
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
		CyNetworkManager cyNetworkManager
			= getService(context, CyNetworkManager.class);
		NewNetworkSelectedNodesOnlyTaskFactory fromSelection
			= getService(context, NewNetworkSelectedNodesOnlyTaskFactory.class);
		TaskManager<?,?> taskManager = getService(context, TaskManager.class);

		// Instance for original App.
		MenuAction_SubgCreation action = 
				new MenuAction_SubgCreation(cyApplicationManager, cyNetworkManager,
						taskManager, fromSelection, "Subg. extract test 3");
		
		// Instance for original task.
		SubgCreationTaskFactory taskFactory = new SubgCreationTaskFactory();
		
		// Register original services to Cytoscape
		registerService(context, action, CyAction.class, new Properties()); 					
		registerService(context, taskFactory, TaskFactory.class, new Properties());
		
	}

}
