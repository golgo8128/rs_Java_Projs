package org.cytoscape.myapp.scratch1_1.internal;

import java.util.Properties;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.service.util.AbstractCyActivator;
import org.osgi.framework.BundleContext;

public class CyActivator extends AbstractCyActivator {

	@Override
	public void start(BundleContext bc) throws Exception {
	
		// To get a reference of CyNetworkFactory at CyActivator class of the App
		CyNetworkFactory networkFactory = getService(bc, CyNetworkFactory.class);		
		CyNetworkManager networkManager = getService(bc, CyNetworkManager.class); // Added by R.S.
		
		CyApplicationManager cyApplicationManager = getService(bc, CyApplicationManager.class);
		
		MenuAction action = new MenuAction(cyApplicationManager, "TestNetCreate1_1", networkFactory, networkManager);
		
		Properties properties = new Properties();
		
		registerAllServices(bc, action, properties);
	}

}
