package rsCy3App.rsMetabPPI1_11_2.internal;


import java.awt.event.ActionEvent;

import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.application.swing.CytoPanel;
import org.cytoscape.application.swing.CytoPanelName;
import org.cytoscape.application.swing.CytoPanelState;

public class MenuActInvokePanel1_2 extends AbstractCyAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3704744068925993359L;

	private final RegServiceBag1_4 rSB;
	private final CytoPanel cytoPanelWest;
	private MetabPPIPanel1_2 myCytoPanel;	
	
	public MenuActInvokePanel1_2(RegServiceBag1_4 regServiceBag, String ver,
			MetabPPIPanel1_2 myCytoPanel){
		// Add a menu item to Apps pull-down menu
		super("Start rsMetabPPI " + ver);
		setPreferredMenu("Apps");

		this.rSB = regServiceBag;

		//Note: myCytoPanel is bean we defined and registered as a service
		this.cytoPanelWest = this.rSB.cytoscapeDesktopService.getCytoPanel(CytoPanelName.WEST);
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
