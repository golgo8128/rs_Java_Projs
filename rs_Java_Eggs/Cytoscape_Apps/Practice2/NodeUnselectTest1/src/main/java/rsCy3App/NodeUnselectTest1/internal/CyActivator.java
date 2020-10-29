package rsCy3App.NodeUnselectTest1.internal;

import java.util.Properties;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CyAction;
import org.cytoscape.event.CyEventHelper;
import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.osgi.framework.BundleContext;

public class CyActivator extends AbstractCyActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		
		CyApplicationManager cyApplicationManager = getService(context, CyApplicationManager.class);
		CyNetworkViewFactory networkViewFactory
		= getService(context, CyNetworkViewFactory.class);			
		VisualMappingManager vmmServiceRef = getService(context, VisualMappingManager.class);
		CyEventHelper eventHelper = getService(context, CyEventHelper.class);
		
		
		MenuAction action = new MenuAction(cyApplicationManager,
				networkViewFactory,
				vmmServiceRef,
				eventHelper,
				"Unselect Node Test v1");

		Properties properties = new Properties();		
		registerService(context, action, CyAction.class, properties);
		
	}

}
