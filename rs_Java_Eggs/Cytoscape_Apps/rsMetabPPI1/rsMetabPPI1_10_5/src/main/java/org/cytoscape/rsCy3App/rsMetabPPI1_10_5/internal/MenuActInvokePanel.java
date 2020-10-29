package org.cytoscape.rsCy3App.rsMetabPPI1_10_5.internal;


import java.awt.event.ActionEvent;

import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.application.swing.CytoPanel;
import org.cytoscape.application.swing.CytoPanelName;
import org.cytoscape.application.swing.CytoPanelState;

public class MenuActInvokePanel extends AbstractCyAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3704744068925993359L;

	private CySwingApplication desktopApp;
	private final CytoPanel cytoPanelWest;
	private MetabPPIPanel myCytoPanel;	
	
	public MenuActInvokePanel(CySwingApplication desktopApp,
			MetabPPIPanel myCytoPanel){
		// Add a menu item to Apps pull-down menu
		super("Start rsMetabPPI v1.10.5");
		setPreferredMenu("Apps");

		this.desktopApp = desktopApp;

		//Note: myCytoPanel is bean we defined and registered as a service
		this.cytoPanelWest = this.desktopApp.getCytoPanel(CytoPanelName.WEST);
		this.myCytoPanel = myCytoPanel;
	}	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		// If the state of the cytoPanelWest is HIDE, show it
		if (cytoPanelWest.getState() == CytoPanelState.HIDE) {
			cytoPanelWest.setState(CytoPanelState.DOCK);
		}	

		// Select my panel
		int index = cytoPanelWest.indexOfComponent(myCytoPanel);
		if (index == -1) {
			return;
		}
		cytoPanelWest.setSelectedIndex(index);
		
	}

}
