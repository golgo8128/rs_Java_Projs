package rsCy3App.Hello_Dialog2.internal;

import java.util.Properties;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CyAction;
import org.cytoscape.service.util.AbstractCyActivator;
import org.osgi.framework.BundleContext;

public class CyActivator extends AbstractCyActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		
		CyApplicationManager cyApplicationManager = getService(context, CyApplicationManager.class);
		
		MenuAction action = new MenuAction(cyApplicationManager, "Hello ver.2");
		// Defines action of the App by the instace "action".
		
		registerService(context, action, CyAction.class, new Properties());
		// This is necessary to add App service to Cytoscape
	}

}
