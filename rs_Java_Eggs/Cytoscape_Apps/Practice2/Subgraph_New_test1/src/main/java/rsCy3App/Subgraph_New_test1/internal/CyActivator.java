package rsCy3App.Subgraph_New_test1.internal;

import java.util.Properties;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CyAction;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.service.util.AbstractCyActivator;
import org.osgi.framework.BundleContext;

public class CyActivator extends AbstractCyActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		
		CyApplicationManager cyApplicationManager = getService(context, CyApplicationManager.class);
		CyNetworkManager     cyNetworkManager     = getService(context, CyNetworkManager.class);
		
		MenuAction_SubgNew action = 
				new MenuAction_SubgNew(cyApplicationManager, cyNetworkManager, "Subg. extract test");
		
		registerService(context, action, CyAction.class, new Properties()); 		

	}

}
