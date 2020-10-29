package rsCy3App.SubgraphCreateTest7_3.internal;

import java.util.Properties;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CyAction;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.task.create.NewNetworkSelectedNodesOnlyTaskFactory;
import org.cytoscape.task.select.SelectFirstNeighborsTaskFactory;
import org.cytoscape.view.layout.CyLayoutAlgorithmManager;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
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
		
		TaskManager<?,?> taskManager = getService(context, TaskManager.class);
		
		// Instance for original App.
		MenuActionSubgCreation action = 
				new MenuActionSubgCreation(cyApplicationManager,
						cyNetworkManager,
						networkViewFactory,
						networkViewManager,
						taskManager,
						newNetfromSelNodesTFactory,
						sel1stNeiTFactory,
						cyLayoutManager,
						"Subg. extract test 7-3");		
		
		// Register original services to Cytoscape
		registerService(context, action, CyAction.class, new Properties()); 					

	}

}
