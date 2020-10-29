package rsCy3App.rsMetabPPI1_3t1.internal;

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

		MetabPPIPanel metabPPIPanel = new MetabPPIPanel();
		MenuActInvokePanel menuActInvokePanel = new MenuActInvokePanel(cytoscapeDesktopService,metabPPIPanel);

		registerService(context, metabPPIPanel, CytoPanelComponent.class, new Properties()); // Panel?
		registerService(context, menuActInvokePanel, CyAction.class, new Properties());      // Pull-down menu?
	

	}

}
