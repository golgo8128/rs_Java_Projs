package rsCy3App.rsMetabPPI1_10_2.internal;


import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskIterator;


public class SubgCreationTaskFactory extends AbstractTaskFactory {

	private final RegServiceBag1_3 regServiceBag;
	private final String[] targetNodeNames;
	
	public SubgCreationTaskFactory(RegServiceBag1_3 regServiceBag, String[] targetNodeNames){
		
		this.regServiceBag = regServiceBag;
		this.targetNodeNames = targetNodeNames;
		
	}
	
	@Override
	public TaskIterator createTaskIterator() {
		// TODO Auto-generated method stub
		return new TaskIterator(new SubgCreationTask(regServiceBag, targetNodeNames));		
	} 		

}
