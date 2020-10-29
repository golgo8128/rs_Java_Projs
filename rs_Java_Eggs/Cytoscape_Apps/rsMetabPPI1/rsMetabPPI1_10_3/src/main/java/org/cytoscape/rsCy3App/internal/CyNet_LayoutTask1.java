package org.cytoscape.rsCy3App.internal;

import org.cytoscape.view.layout.CyLayoutAlgorithm;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.Task;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskMonitor;

public class CyNet_LayoutTask1 extends AbstractTask {

	private RegServiceBag1_3 rSB;
	private CyNetworkView icynetview;
	
	public CyNet_LayoutTask1(RegServiceBag1_3 regServiceBag, CyNetworkView icynetview){
		
		this.rSB        = regServiceBag;
		this.icynetview = icynetview;
		
	}
	
	@Override
	public void run(TaskMonitor taskMonitor) throws Exception {

		// Layout network
		CyLayoutAlgorithm cylayoutAlgorithm = rSB.cyLayoutManager.getLayout("force-directed");
		TaskIterator itrLay = cylayoutAlgorithm.createTaskIterator(icynetview,
				cylayoutAlgorithm.getDefaultLayoutContext(),
				CyLayoutAlgorithm.ALL_NODE_VIEWS, null);
		Task selectionTaskLay  = itrLay.next();
		selectionTaskLay.run(taskMonitor);
	
		icynetview.updateView();		
		
	}

}
