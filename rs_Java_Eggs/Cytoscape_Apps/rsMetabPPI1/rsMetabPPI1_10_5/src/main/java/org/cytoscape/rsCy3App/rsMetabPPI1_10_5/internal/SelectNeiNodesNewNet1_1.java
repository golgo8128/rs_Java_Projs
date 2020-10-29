package org.cytoscape.rsCy3App.rsMetabPPI1_10_5.internal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.Task;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskMonitor;

public class SelectNeiNodesNewNet1_1 extends AbstractTask {

	private RegServiceBag1_3 rSB;
	// private CyNetwork metabPPI_cynet = null;
	private HashMap<String, Object> tsi_h;
	private	String[] lines;
	
	public SelectNeiNodesNewNet1_1(RegServiceBag1_3 regServiceBag,
			HashMap<String, Object> task_share_info_h,
			String[] lines){	
	
		this.rSB   = regServiceBag;
		this.tsi_h = task_share_info_h;
		this.lines = lines;
		
	}
		
		
	@Override
	public void run(TaskMonitor taskMonitor) throws Exception {

		CyNetwork  metabPPI_cynet        = Build_metabPPI1_4.getInstance().build_net();
		CyNetworkView metabPPI_cynetview = rsCy3App_Usefuls1.take_first_view(rSB, metabPPI_cynet);

		Set<CyNode> metabnodeset  = rsCy3App_Usefuls1.getNodesWithValue(
				metabPPI_cynet, metabPPI_cynet.getDefaultNodeTable(), "Node type", "Metabolite");			
		
		CyNode[] selCyNodes     = rsCy3App_Usefuls1.get_cynodes_from_their_names(metabPPI_cynet, lines);
		if(selCyNodes.length == 0){
			throw new IllegalStateException("No matching metabolite found.");
		} 		
		
		
		HashSet<CyNode> selCyNodesSet = new HashSet<CyNode>(Arrays.asList(selCyNodes));	
		CyNode[] selCyNodes_nei = rsCy3App_Usefuls1.first_neighbors(metabPPI_cynet, selCyNodes); // First neighbors
		selCyNodes_nei          = rsCy3App_Usefuls1.first_neighbors(metabPPI_cynet, selCyNodes_nei); // to the second neighbors
		
		for (final CyNode node : metabPPI_cynet.getNodeList()){ metabPPI_cynet.getRow(node).set(CyNetwork.SELECTED, false); }
		
		for (final CyNode node : selCyNodes_nei){
			if(!metabnodeset.contains(node) || selCyNodesSet.contains(node)){
				metabPPI_cynet.getRow(node).set(CyNetwork.SELECTED, true);
			}
			else {
				metabPPI_cynet.getRow(node).set(CyNetwork.SELECTED, false);
				/* Metabolites not in the target will NOT be selected. */
			}
		}		

		VizMap1_3 metabPPI_vizmap = VizMap1_3.getInstance();
		metabPPI_vizmap.apply_VStyle_I(metabPPI_cynet);
		metabPPI_cynetview.updateView();			
			
		
		TaskIterator itrNn = rSB.newNetfromSelNodesTFactory.createTaskIterator(metabPPI_cynet);
		// Task newNet_Sel_task = itrNn.next();
		// this.insertTasksAfterCurrentTask(newNet_Sel_task);
		rSB.taskManager.execute(itrNn); // Run using another thread?
		
	}

}
