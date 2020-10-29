package rsCy3App.Load_Attrib_Test1_2.internal;

import java.util.Properties;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CyAction;
import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.service.util.AbstractCyActivator;
import org.osgi.framework.BundleContext;

public class CyActivator extends AbstractCyActivator {

	@Override
	public void start(BundleContext bcontext) throws Exception {
		
		CyApplicationManager cyApplicationManager = getService(bcontext, CyApplicationManager.class);
		CyNetworkManager networkManager = getService(bcontext, CyNetworkManager.class);
		CyNetworkFactory networkFactory = getService(bcontext, CyNetworkFactory.class);		
				
		MenuAction action = new MenuAction(cyApplicationManager, "Load attr. test 1", 
				networkFactory, networkManager);
				
		registerService(bcontext, action, CyAction.class, new Properties());
		// Appears in pull-down menu?
		
	}	
}
