package org.cytoscape.rsCy3App.NewNetSelNodes1_1.internal;

import java.awt.event.ActionEvent;
import java.util.List;

import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyTableUtil;
import org.cytoscape.view.model.CyNetworkView;


/**
 * Creates a new menu item under Apps menu section.
 *
 */
public class MenuAction extends AbstractCyAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final RegServiceBag1_3 rSB;	
	
	public MenuAction(RegServiceBag1_3 regServiceBag, final String menuTitle) {

		super(menuTitle, regServiceBag.cyApplicationManager, null, null);				
		setPreferredMenu("Apps");
		
		this.rSB = regServiceBag;
		
	}

	public void actionPerformed(ActionEvent e) {

        final CyNetworkView networkView = rSB.cyApplicationManager.getCurrentNetworkView();
        final CyNetwork cnetwork = networkView.getModel();		
		
		List<CyNode> selnodes = CyTableUtil.getNodesInState(cnetwork, "selected", true);
		
		NewNetSelNodesAllEdges1_1 newNetSel = new NewNetSelNodesAllEdges1_1(rSB);
		newNetSel.CreateNewNetandView(cnetwork, selnodes);
		
	}
}
