
package rsCy3App.SubgraphCreateTest4.internal;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.task.create.NewNetworkSelectedNodesOnlyTaskFactory;
import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskIterator;

/**
 * The TaskFactory is a Singleton object whose sole purpose is to
 * create new instances of a specific kind of Task.  TaskFactory objects
 * are meant to be registered as OSGi services for the specific TaskFactory
 * interface they implement (in this case NetworkTaskFactory). This object 
 * is defined as a service in the Spring XML configuration file called
 * bundle-context-osgi.xml in the src/main/resources/META-INF/spring
 * directory of this project. 
 * <br/>
 * 
 */
public class SubgCreationTaskFactory extends AbstractTaskFactory {

	/**
	 * This method should return a new instance of SampleTask each
	 * time it gets called.  The variable "net" is set as part of 
	 * the {@link org.cytoscape.task.NetworkTaskFactory} implementation
	 * of {@link AbstractNetworkTaskFactory}.  Whoever uses this
	 * TaskFactory (e.g. the a Swing MenuItem) and calls the getTask() 
	 * method is <i>assumed</i> to have already called the setNetwork() 
	 * method which will set the "net" variable.
	 */

	@Override
	public TaskIterator createTaskIterator() {
		// TODO Auto-generated method stub
		return null;
	} 		
	
	public TaskIterator createTaskIterator(CyApplicationManager cyApplicationManager,
			NewNetworkSelectedNodesOnlyTaskFactory fromSelection) {
		
		return new TaskIterator(new SubgCreationTask(cyApplicationManager, fromSelection));
	
	}
	
}
