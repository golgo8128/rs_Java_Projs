package rsCy3App.Cy3NetTSVAutoImport1_2.internal;

// This class is highly dependent on SwingPoller1_2.


import java.awt.Color;
import java.io.File;
import java.util.HashSet;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.layout.CyLayoutAlgorithm;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskMonitor;

import rsCy3App.Cy3NetTSVAutoImport1_2.internal.rs_Java_Proj4.general.datastruct.RSTable1;
import rsCy3App.Cy3NetTSVAutoImport1_2.internal.rs_Java_Proj4.general.strproc.SumUpUntilAvail1;

public class NewFileAutoRead_task1 extends AbstractTask {

	private HashSet<String> newfiles_wo_ends;
	private TaskMonitor taskMonitor;
	
	public NewFileAutoRead_task1(HashSet<String> newfiles_wo_ends){

		// this.swpl = swingpoller;
		this.newfiles_wo_ends = newfiles_wo_ends;

	}

	@Override
	public void run(TaskMonitor taskMonitor) throws Exception {
		// TODO Auto-generated method stub

		SwingPoller1_2.polltimer.stop();
		this.taskMonitor = taskMonitor;
		import_nettsv();
		SwingPoller1_2.polltimer.start();
		
	}

	public void import_nettsv(){
		// _ok.txt may not be complete at the time of polling.

		SwingPoller1_2.polltimer.stop();	
		SwingPoller1_2.indic_label.setBackground(Color.RED);
		
		for(String istr:newfiles_wo_ends){
			System.out.println(istr);

			String bsname_wo_ends = (new File(istr)).getName();

			// Creates a new network
			CyNetwork newCynet = SwingPoller1_2.rSB.cyNetworkFactory.createNetwork();
			SumUpUntilAvail1 suua = new SumUpUntilAvail1(bsname_wo_ends + " #%d");
			String newNetName = suua.get_avail(rsCy3App_Usefuls1.getNetNames(SwingPoller1_2.rSB));
			newCynet.getRow(newCynet).set(CyNetwork.NAME, newNetName);		
			SwingPoller1_2.rSB.cyNetworkManager.addNetwork(newCynet);				

			// Creates a new network view				
			CyNetworkView cyNetView = SwingPoller1_2.rSB.networkViewFactory.createNetworkView(newCynet);
			SwingPoller1_2.rSB.networkViewManager.addNetworkView(cyNetView);


			// Reads node attribute table	
			RSTable1 rstbl_nodes = new RSTable1();
			rstbl_nodes.read_table(istr + SwingPoller1_2.filnam_ends[1], "\\t", false, true);

			// Reads edge attribute table	
			RSTable1 rstbl_edges = new RSTable1();
			rstbl_edges.read_table(istr + SwingPoller1_2.filnam_ends[2], "\\t", false, true);

			// Puts node and edge attributes onto the network	
			NetReadTSV1 netreadtsv = new NetReadTSV1(SwingPoller1_2.rSB);
			netreadtsv.set_taskMonitor(taskMonitor);
			netreadtsv.make_node_table(newCynet, rstbl_nodes);
			netreadtsv.make_edge_table(newCynet, rstbl_edges);


			// Applies visual style			
			VizMap1_3_II vizmap = VizMap1_3_II.getInstance(SwingPoller1_2.rSB);
			vizmap.apply_VStyle_I(newCynet);


			// Layout
			CyLayoutAlgorithm layout_force_directed = SwingPoller1_2.rSB.cyLayoutManager.getLayout("force-directed");
			TaskIterator taskIterator = new TaskIterator();

			taskIterator.append(
					layout_force_directed.createTaskIterator(
							cyNetView,
							layout_force_directed.getDefaultLayoutContext(),
							CyLayoutAlgorithm.ALL_NODE_VIEWS, null));

			SwingPoller1_2.rSB.taskManager.execute(taskIterator);			

			// cyNetView.updateView();				

		}

	}


}	
	
	

