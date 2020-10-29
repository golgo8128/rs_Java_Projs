package org.cytoscape.rsCy3App.sample22.internal;

import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskIterator;


public class Sample22TaskFactory extends AbstractTaskFactory {

	public Sample22TaskFactory(){

	}

	public TaskIterator createTaskIterator() {
		return new TaskIterator(new MyTask());
	}

}
