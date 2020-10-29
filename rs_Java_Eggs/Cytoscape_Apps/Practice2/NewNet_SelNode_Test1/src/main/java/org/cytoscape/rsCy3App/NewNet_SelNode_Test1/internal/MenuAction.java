package org.cytoscape.rsCy3App.NewNet_SelNode_Test1.internal;

import java.awt.Color;

import java.awt.event.ActionEvent;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.event.CyEventHelper;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.model.CyNode;
import org.cytoscape.task.select.InvertSelectedNodesTaskFactory;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.presentation.property.BasicVisualLexicon;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.view.vizmap.VisualStyle;
import org.cytoscape.work.TaskManager;

public class MenuAction extends AbstractCyAction {

	
	private final CyNetworkFactory cyNetworkFactory;
	private final CyNetworkManager cyNetworkManager;
	private final CyNetworkViewFactory networkViewFactory;
	private final CyNetworkViewManager networkViewManager;
	private final VisualMappingManager vmmServiceRef;
	private final CyEventHelper eventHelper;
	private final TaskManager<?,?> taskManager;
	private final InvertSelectedNodesTaskFactory invertSelectedNodesTaskFactory;
	
	public MenuAction(CyApplicationManager cyApplicationManager,
			CyNetworkFactory cyNetworkFactory,
			CyNetworkManager cyNetworkManager,
			CyNetworkViewFactory networkViewFactory,
			CyNetworkViewManager networkViewManager,
			VisualMappingManager vmmServiceRef,
			CyEventHelper eventHelper,
			TaskManager<?,?> taskManager,
			InvertSelectedNodesTaskFactory invertSelectedNodesTaskFactory,
			final String menuTitle) {
		
		super(menuTitle, cyApplicationManager, null, null);
		setPreferredMenu("Apps");
		
		this.cyNetworkFactory   = cyNetworkFactory;
		this.cyNetworkManager   = cyNetworkManager;
		this.networkViewFactory = networkViewFactory;
		this.networkViewManager = networkViewManager;
		this.vmmServiceRef      = vmmServiceRef;
		this.eventHelper        = eventHelper;
		this.taskManager        = taskManager;
		this.invertSelectedNodesTaskFactory = invertSelectedNodesTaskFactory;
		
	}

	public void actionPerformed(ActionEvent e) {

		CyNetwork net         = cyNetworkFactory.createNetwork();
		net.getRow(net).set(CyNetwork.NAME, "Test network");
		cyNetworkManager.addNetwork(net);	
		CyNetworkView netview = networkViewFactory.createNetworkView(net);
		netview.updateView();
		networkViewManager.addNetworkView(netview);		
		eventHelper.flushPayloadEvents();
		
		CyNode node1 = net.addNode();		
		// CyNode node2 = net.addNode();
		// CyNode node3 = net.addNode();
		
		net.getRow(node1).set(CyNetwork.NAME, "Node 1");
		// net.getRow(node2).set(CyNetwork.NAME, "Node 2");
		// net.getRow(node3).set(CyNetwork.NAME, "Node 3");	
		eventHelper.flushPayloadEvents();
		
		VisualStyle vstyle = vmmServiceRef.getDefaultVisualStyle();
		vstyle.apply(netview);	
		
		// net.getRow(node1).set(CyNetwork.SELECTED, true);
		
		taskManager.execute(invertSelectedNodesTaskFactory.createTaskIterator(net));			
		// taskManager.execute(invertSelectedNodesTaskFactory.createTaskIterator(net));	
			
		// netview.getNodeView(node1).setVisualProperty(BasicVisualLexicon.NODE_SELECTED, true);
		// netview.getNodeView(node1).setVisualProperty(BasicVisualLexicon.NODE_SELECTED_PAINT, new Color(0x88,0x88,0x88));

		netview.getNodeView(node1).setVisualProperty(BasicVisualLexicon.NODE_FILL_COLOR, new Color(0x88,0x88,0x88));
		eventHelper.flushPayloadEvents();		
		
		netview.updateView();
		
		
	}
}
