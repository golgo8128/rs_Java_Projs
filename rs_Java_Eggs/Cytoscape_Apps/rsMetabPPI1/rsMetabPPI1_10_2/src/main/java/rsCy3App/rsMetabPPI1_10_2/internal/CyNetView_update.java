package rsCy3App.rsMetabPPI1_10_2.internal;

import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;

public class CyNetView_update extends AbstractTask {

	private CyNetworkView icynetview;	
	
	public CyNetView_update(CyNetworkView icynetview){
		
		this.icynetview = icynetview;
		
	}
		
	
	@Override
	public void run(TaskMonitor taskMonitor) throws Exception {
		// TODO Auto-generated method stub

		this.icynetview.updateView();
		
	}

}
