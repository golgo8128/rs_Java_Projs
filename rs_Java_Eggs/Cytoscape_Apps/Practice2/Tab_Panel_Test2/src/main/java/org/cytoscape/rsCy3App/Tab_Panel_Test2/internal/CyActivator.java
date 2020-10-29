package org.cytoscape.rsCy3App.Tab_Panel_Test2.internal;

import java.util.Properties;

import org.cytoscape.application.swing.CyAction;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.service.util.AbstractCyActivator;
import org.osgi.framework.BundleContext;

public class CyActivator extends AbstractCyActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		
		CySwingApplication cytoscapeDesktopService = getService(context,CySwingApplication.class);

		MyCytoPanel myCytoPanel = new MyCytoPanel();
		Sample02 sample02Action = new Sample02(cytoscapeDesktopService,myCytoPanel);

		registerService(context,myCytoPanel,CytoPanelComponent.class, new Properties()); // Panel?
		registerService(context,sample02Action,CyAction.class, new Properties());        // Pull-down menu?
	

	}

}
