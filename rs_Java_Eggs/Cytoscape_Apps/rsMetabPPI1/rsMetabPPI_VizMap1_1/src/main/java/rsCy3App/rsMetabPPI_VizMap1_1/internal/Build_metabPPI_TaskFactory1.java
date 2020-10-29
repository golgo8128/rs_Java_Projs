package rsCy3App.rsMetabPPI_VizMap1_1.internal;

import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskIterator;

public class Build_metabPPI_TaskFactory1 extends AbstractTaskFactory {

	private final RegServiceBag1_2 rSB;
	
	public Build_metabPPI_TaskFactory1(RegServiceBag1_2 rSB){
		
		this.rSB = rSB;
		
	}
	
	@Override
	public TaskIterator createTaskIterator() {

		return new TaskIterator(new Build_metabPPI_Task1_2(this.rSB));	

	}

}
