package org.cytoscape.rsCy3App.rsMetabPPI1_10_4.internal;


import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskIterator;


public class SubgCreationTaskFactory1 extends AbstractTaskFactory {

	private final RegServiceBag1_3 regServiceBag;
	
	public SubgCreationTaskFactory1(RegServiceBag1_3 regServiceBag){
		
		this.regServiceBag = regServiceBag;
		
	}
	
	@Override
	public TaskIterator createTaskIterator() {
		// TODO Auto-generated method stub
		return new TaskIterator(new SubgCreationTask1_2(regServiceBag));		
	} 		

}
