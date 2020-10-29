package rsCy3App.NodeUnselectTest1.internal;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JOptionPane;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.event.CyEventHelper;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyTableUtil;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.view.vizmap.VisualStyle;


/**
 * Creates a new menu item under Apps menu section.
 *
 */
public class MenuAction extends AbstractCyAction {

	private final CyApplicationManager cyApplicationManager;
	private final CyNetworkViewFactory networkViewFactory;
	private final VisualMappingManager vmmServiceRef;
	private final CyEventHelper eventHelper;
	
	public MenuAction(CyApplicationManager cyApplicationManager,
			CyNetworkViewFactory networkViewFactory,
			VisualMappingManager vmmServiceRef,
			CyEventHelper eventHelper,
			final String menuTitle){
		
		super(menuTitle, cyApplicationManager, null, null);
		setPreferredMenu("Apps");
		
		this.cyApplicationManager = cyApplicationManager;
		this.networkViewFactory = networkViewFactory;
		this.vmmServiceRef = vmmServiceRef;
		this.eventHelper = eventHelper;
		
	}

	public void actionPerformed(ActionEvent e) {

		CyNetwork net1 = cyApplicationManager.getCurrentNetwork();
		CyNetworkView netview = networkViewFactory.createNetworkView(net1);
		VisualStyle vs = vmmServiceRef.getCurrentVisualStyle();
		
		// List<CyNode> selnodes = CyTableUtil.getNodesInState(net1, "selected", true);
		List<CyNode> allnodes = net1.getNodeList();
		
		// for(final CyNode enode: allnodes){
		// 	net1.getRow(enode).set(CyNetwork.SELECTED, false);
		// }

		CyNode firstselnode = allnodes.get(0);
		net1.getRow(firstselnode).set(CyNetwork.SELECTED, true);
		
		vs.apply(netview);
		eventHelper.flushPayloadEvents();
		netview.updateView();
		
	}
}
