package org.cytoscape.rsCy3App.internal;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;

public class Generate_sub_MetabPPI1_1 {

	private RegServiceBag1_3 rSB = null;
	
	public Generate_sub_MetabPPI1_1(RegServiceBag1_3 rSB){
		
		this.rSB = rSB;

	}
	
	public void do_it(String[] lines){
			
		Build_metabPPI1_4 build_metabPPI = Build_metabPPI1_4.getInstance(rSB);
		CyNetwork metabPPI_cynet         = build_metabPPI.build_net();
		CyNetworkView metabPPI_cynetview = rsCy3App_Usefuls1.take_first_view(rSB, metabPPI_cynet);
		VizMap1_3 metabPPI_vizmap = VizMap1_3.getInstance(rSB);
		metabPPI_vizmap.apply_VStyle_I(metabPPI_cynet);
		
		Set<CyNode> metabnodeset  = rsCy3App_Usefuls1.getNodesWithValue(
				metabPPI_cynet, metabPPI_cynet.getDefaultNodeTable(), "Node type", "Metabolite");			
		
		CyNode[] selCyNodes     = rsCy3App_Usefuls1.get_cynodes_from_their_names(metabPPI_cynet, lines);
		if(selCyNodes.length == 0){
			throw new IllegalStateException("No matching metabolite found.");
		} 		
		
		
		HashSet<CyNode> selCyNodesSet = new HashSet<CyNode>(Arrays.asList(selCyNodes));	
		CyNode[] selCyNodes_nei = rsCy3App_Usefuls1.first_neighbors(metabPPI_cynet, selCyNodes); // First neighbors
		selCyNodes_nei          = rsCy3App_Usefuls1.first_neighbors(metabPPI_cynet, selCyNodes_nei); // to the second neighbors
		
		// for (final CyNode node : metabPPI_cynet.getNodeList()){ metabPPI_cynet.getRow(node).set(CyNetwork.SELECTED, false); }
		
		for (final CyNode node : selCyNodes_nei){
			if(!metabnodeset.contains(node) || selCyNodesSet.contains(node)){
				metabPPI_cynet.getRow(node).set(CyNetwork.SELECTED, true);
			}
			else {
				metabPPI_cynet.getRow(node).set(CyNetwork.SELECTED, false);
				/* Metabolites not in the target will NOT be selected. */
			}
		}		

		metabPPI_vizmap.apply_VStyle_I(metabPPI_cynet);
		metabPPI_cynetview.updateView();		
		
		CyNet_LayoutTaskFactory1 layouttaskFactory = 
				new CyNet_LayoutTaskFactory1(rSB, metabPPI_cynetview);		
		rSB.taskManager.execute(layouttaskFactory.createTaskIterator());		
		
		SubgCreationTaskFactory1 taskFactory = 
				new SubgCreationTaskFactory1(rSB);		
		rSB.taskManager.execute(taskFactory.createTaskIterator());		
		
	}
	
	
}
