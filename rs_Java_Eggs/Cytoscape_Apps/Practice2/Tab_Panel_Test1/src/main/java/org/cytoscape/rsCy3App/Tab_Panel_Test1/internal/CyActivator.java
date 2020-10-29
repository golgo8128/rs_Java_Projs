package org.cytoscape.rsCy3App.Tab_Panel_Test1.internal;

import java.util.Properties;

import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.service.util.AbstractCyActivator;
import org.osgi.framework.BundleContext;

public class CyActivator extends AbstractCyActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		
		CytoPanelComponent cytoPanelComponent = getService(context, CytoPanelComponent.class);
				
		MyCytoPanel myPanel = new MyCytoPanel(cytoPanelComponent);	
		
		Properties properties = new Properties();
		
		registerAllServices(context, myPanel, properties);
	}

}
