package org.cytoscape.rsCy3App.internal;

import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskIterator;

public class CyNet_LayoutTaskFactory1 extends AbstractTaskFactory {

	private RegServiceBag1_3 regServiceBag;
	private CyNetworkView icynetview;
	
	public CyNet_LayoutTaskFactory1(RegServiceBag1_3 regServiceBag, CyNetworkView icynetview){
		
		this.regServiceBag = regServiceBag;
		this.icynetview    = icynetview;
		
	}	
	
	@Override
	public TaskIterator createTaskIterator() {
		// TODO Auto-generated method stub		
		return new TaskIterator(new CyNet_LayoutTask1(regServiceBag, icynetview));	
	}

}
