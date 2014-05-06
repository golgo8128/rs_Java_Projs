package rsCy3App.Cy3AutoReadTSV1_1.internal;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.model.CyNetworkView;

import rsCy3App.Cy3AutoReadTSV1_1.internal.rs_Java_Proj4_cp.general.datastruct.RSTable1;


/**
 * Creates a new menu item under Apps menu section.
 *
 */
public class MenuAction extends AbstractCyAction {

	private final RegServiceBag1_4 rSB;
	
	public MenuAction(RegServiceBag1_4 regServiceBag, final String menuTitle) {
		
		super(menuTitle, regServiceBag.cyApplicationManager, null, null);
		setPreferredMenu("Apps");
	
		rSB = regServiceBag;
		
	}

	public void actionPerformed(ActionEvent e) {

		// Write your own function here.
		JOptionPane.showMessageDialog(null, "NetReadTSV test ...");
		setName("Changed name title"); // This will change menu title.
		
		CyNetwork cynet = rSB.cyNetworkFactory.createNetwork();
		cynet.getRow(cynet).set(CyNetwork.NAME, "Destroy me");		
		rSB.cyNetworkManager.addNetwork(cynet);			
		
		CyNetworkView cynetview = rSB.networkViewFactory.createNetworkView(cynet);
		rSB.networkViewManager.addNetworkView(cynetview);
	
		
		RSTable1 rstbl_nodes = new RSTable1();
		rstbl_nodes.read_table(rstbl_nodes.maketesttablefile(2), "\\t", false, true);

		RSTable1 rstbl_edges = new RSTable1();
		rstbl_edges.read_table(rstbl_nodes.maketesttablefile(3), "\\t", false, true);
		
		NetReadTSV1 netreadtsv = new NetReadTSV1(rSB);
		netreadtsv.make_node_table(cynet, rstbl_nodes);
		netreadtsv.make_edge_table(cynet, rstbl_edges);
		
		VizMap1_3_II vizmap = VizMap1_3_II.getInstance(rSB);
		vizmap.apply_VStyle_I(cynet);
		
		cynetview.updateView();

		SwingPoller1 swingpoller = SwingPoller1.getInstance();
		swingpoller.turn_on();
		
	}
}
