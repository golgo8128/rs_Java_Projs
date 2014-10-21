package org.cytoscape.rsCy3App.rsMetabPPI1_12_1.internal;

import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JButton;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyTable;
import org.cytoscape.view.layout.CyLayoutAlgorithm;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskMonitor;

public class Generate_sub_MetabPPI_task1_4 extends AbstractTask {
	
	private final String SubnetName = "MetabPPI subnet.";
	private RegServiceBag1_4 rSB = null;
	private HashMap<String, Object> tsi_h;
	private String[] lines;
	
	public Generate_sub_MetabPPI_task1_4(RegServiceBag1_4 rSB,
			HashMap<String, Object> task_share_info_h, String[] lines){
		
		this.rSB   = rSB;
		this.tsi_h = task_share_info_h;
		this.lines = lines;
		
	}
	
	public void run(final TaskMonitor taskMonitor) throws Exception {
			
		taskMonitor.setTitle("rsMetabPPI running");
		
		taskMonitor.setStatusMessage("Building rsMetabPPI network ...");
		
		Build_metabPPI1_5 build_metabPPI = Build_metabPPI1_5.getInstance(rSB);
		CyNetwork metabPPI_cynet         = build_metabPPI.build_net(taskMonitor);

		taskMonitor.setStatusMessage("Selecting given nodes ...");
		
		SelectNeiNodes1_2 selneinodes = new SelectNeiNodes1_2(rSB);
		HashSet<CyNode> selnode_set = selneinodes.doit(lines);

		taskMonitor.setStatusMessage("Generating the new subnetwork ...");		
		
		NewNetSelNodesAllEdges1_2 newNetSel = new NewNetSelNodesAllEdges1_2(rSB);
		CyNetworkView newnetview = newNetSel.CreateNewNetandView(metabPPI_cynet, selnode_set, SubnetName);

		taskMonitor.setStatusMessage("Calculating p-values for proteins ...");
		
		CyTable nodetable_netsub = newnetview.getModel().getDefaultNodeTable();
		
		AddHygecdf_pvals1 addhygepvals = new AddHygecdf_pvals1(rSB, metabPPI_cynet, newnetview.getModel());
		addhygepvals.write_hygecdf_pvals();
		
		taskMonitor.setStatusMessage("Creating network layout ...");	
		
		CyLayoutAlgorithm layout_grid = rSB.cyLayoutManager.getLayout("grid");			
		CyLayoutAlgorithm layout_force_directed = rSB.cyLayoutManager.getLayout("force-directed");		

/* Available layouts:
		for(CyLayoutAlgorithm cylayalgo: rSB.cyLayoutManager.getAllLayouts()){
				System.out.println(cylayalgo.getName());		
		}

grid
isom
fruchterman-rheingold
degree-circle
circular
kamada-kawai
hierarchical
attribute-circle
force-directed
attributes-layout
stacked-node-layout

*/			
		
		taskMonitor.setStatusMessage("Visual style control ...");
		VizMap1_3 metabPPI_vizmap        = VizMap1_3.getInstance(rSB);
		metabPPI_vizmap.apply_VStyle_I(metabPPI_cynet);		
		metabPPI_vizmap.apply_VStyle_I(newnetview.getModel());	
		
		TaskIterator taskIterator = new TaskIterator();
		if(!Build_metabPPI1_5.done_layout_mboard){
			taskIterator.append(
					layout_grid.createTaskIterator(
							rsCy3App_Usefuls1.take_first_view(rSB, metabPPI_cynet),
							layout_grid.getDefaultLayoutContext(),
							CyLayoutAlgorithm.ALL_NODE_VIEWS, null));
			Build_metabPPI1_5.done_layout_mboard = true;
		} else {
			rsCy3App_Usefuls1.take_first_view(rSB, metabPPI_cynet).updateView();
		}
		
		taskIterator.append(
		    layout_force_directed.createTaskIterator(
		    		newnetview,
					layout_force_directed.getDefaultLayoutContext(),
					CyLayoutAlgorithm.ALL_NODE_VIEWS, null));

		rSB.taskManager.execute(taskIterator);
		
		JButton runbutton = (JButton)tsi_h.get("runbutton");
		runbutton.setEnabled(true); // This does not wait for the completion of network layout.
		
	}
	
	
}
