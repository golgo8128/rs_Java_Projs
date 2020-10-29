package org.cytoscape.rsCy3App.rsMetabPPI1_10_5.internal;

import java.util.HashMap;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.layout.CyLayoutAlgorithm;
import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.Task;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskMonitor;

public class Build_metabPPI_task1_1 extends AbstractTask {

	private RegServiceBag1_3 rSB;
	// private CyNetwork metabPPI_cynet = null;
	private HashMap<String, Object> tsi_h;
	
	public Build_metabPPI_task1_1(RegServiceBag1_3 regServiceBag,
			HashMap<String, Object> task_share_info_h){

		this.rSB   = regServiceBag;
		this.tsi_h = task_share_info_h;
		
	}
		
/*	public CyNetwork get_metabPPI_cynet(){
		
		return metabPPI_cynet;
	}*/
	
	@Override
	public void run(TaskMonitor taskMonitor) throws Exception {

		taskMonitor.setStatusMessage("Building rsMetabPPI network ...");
		
		Build_metabPPI1_4 build_metabPPI = Build_metabPPI1_4.getInstance(rSB);
		CyNetwork metabPPI_cynet         = build_metabPPI.build_net();
		VizMap1_3 metabPPI_vizmap = VizMap1_3.getInstance(rSB);
		metabPPI_vizmap.apply_VStyle_I(metabPPI_cynet);

		
		CyLayoutAlgorithm cylayoutAlgorithm = rSB.cyLayoutManager.getLayout("force-directed");		
		TaskIterator itrLayWhole
		= cylayoutAlgorithm.createTaskIterator(
				rsCy3App_Usefuls1.take_first_view(rSB, metabPPI_cynet),
				cylayoutAlgorithm.getDefaultLayoutContext(),
				CyLayoutAlgorithm.ALL_NODE_VIEWS, null);
		// Task layWhole_task = itrLayWhole.next();		
		// this.insertTasksAfterCurrentTask(layWhole_task);
		rSB.taskManager.execute(itrLayWhole); // Run using another thread?
		
	}

}
