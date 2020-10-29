package rsCy3App.VizMap_test1_1.internal;

import java.util.Properties;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CyAction;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.vizmap.VisualMappingFunctionFactory;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.view.vizmap.VisualStyleFactory;
import org.osgi.framework.BundleContext;

// Refer to http://chianti.ucsd.edu/cytoscape-3.1.0/API/

public class CyActivator extends AbstractCyActivator {

	@Override
	public void start(BundleContext context) throws Exception {

		CyApplicationManager cyApplicationManager
			= getService(context, CyApplicationManager.class);
		CyNetworkManager cyNetworkManager 
			= getService(context, CyNetworkManager.class);
		CyNetworkViewFactory networkViewFactory
			= getService(context, CyNetworkViewFactory.class);		
		CyNetworkViewManager networkViewManager
			= getService(context, CyNetworkViewManager.class);			
		
		// To get references to services in CyActivator class
		VisualMappingManager vmmServiceRef = getService(context,VisualMappingManager.class);
		VisualStyleFactory visualStyleFactoryServiceRef = getService(context,VisualStyleFactory.class);	                
		VisualMappingFunctionFactory vmfFactoryC = getService(context,VisualMappingFunctionFactory.class, "(mapping.type=continuous)");
		VisualMappingFunctionFactory vmfFactoryD = getService(context,VisualMappingFunctionFactory.class, "(mapping.type=discrete)");
		VisualMappingFunctionFactory vmfFactoryP = getService(context,VisualMappingFunctionFactory.class, "(mapping.type=passthrough)");		

		MenuAction action = new MenuAction(cyApplicationManager,
				cyNetworkManager,
				networkViewFactory,
				networkViewManager,
				"VizMapper test #1",
				vmmServiceRef,
				visualStyleFactoryServiceRef,
				vmfFactoryC, vmfFactoryD, vmfFactoryP);
		
		registerService(context, action, CyAction.class, new Properties()); 

	}

}
