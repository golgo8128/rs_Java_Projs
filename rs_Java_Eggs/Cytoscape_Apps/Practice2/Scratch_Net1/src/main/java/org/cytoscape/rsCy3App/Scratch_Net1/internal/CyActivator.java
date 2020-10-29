package org.cytoscape.rsCy3App.Scratch_Net1.internal;

import java.util.Properties;

import org.cytoscape.application.CyApplicationManager;
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
		
		MenuAction action = new MenuAction(cyApplicationManager, "Generate Scratch Net.",
										   networkFactory, networkManager);
		
		Properties properties = new Properties();
		
		registerAllServices(bcontext, action, properties);
	}

}
