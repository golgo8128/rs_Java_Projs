package rsCy3App.rsXGMML_test1_3.internal;

import java.util.Properties;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CyAction;
import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.task.read.LoadNetworkFileTaskFactory;
import org.cytoscape.view.layout.CyLayoutAlgorithmManager;
import org.osgi.framework.BundleContext;

public class CyActivator extends AbstractCyActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		
		CyApplicationManager cyApplicationManager
			= getService(context, CyApplicationManager.class);
		LoadNetworkFileTaskFactory loadNetworkFileTaskFactory
			= getService(context, LoadNetworkFileTaskFactory.class);
		CyLayoutAlgorithmManager cyLayoutManager
			= getService(context, CyLayoutAlgorithmManager.class);	
		
		MenuAction action
			= new MenuAction(cyApplicationManager,
							 loadNetworkFileTaskFactory,
							 cyLayoutManager,
							 "rsXGMML Auto Read");
		
		registerService(context, action, CyAction.class, new Properties()); 
		
	}

}
