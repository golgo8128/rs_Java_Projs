package org.cytoscape.rsCy3App.rsMetabPPI1_10_5.internal;

import java.util.HashMap;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.layout.CyLayoutAlgorithm;
import org.cytoscape.work.Task;
import org.cytoscape.work.TaskIterator;

public class Generate_sub_MetabPPI1_3 {

	private RegServiceBag1_3 rSB = null;
	private HashMap<String, Object> tsi_h;
	
	public Generate_sub_MetabPPI1_3(RegServiceBag1_3 rSB,
			HashMap<String, Object> task_share_info_h){
		
		this.rSB   = rSB;
		this.tsi_h = task_share_info_h;
		
	}
	
	public void do_it(String[] lines){
			
		Task build_metabPPI_task   = new Build_metabPPI_task1_1(rSB, tsi_h);
		Task selneinodenewnet_task = new SelectNeiNodesNewNet1_1(rSB, tsi_h, lines);
		Task recnetnames_task      = new Rec_NetNames1(rSB, tsi_h);
		Task subgDecoration_task   = new SubgDecorationTask1_3(rSB, tsi_h);
		
		TaskIterator mainTaskIterator
			= new TaskIterator(
					build_metabPPI_task,
					selneinodenewnet_task,
					recnetnames_task,
					subgDecoration_task);

		rSB.taskManager.execute(mainTaskIterator);
		
	}
	
	
}
