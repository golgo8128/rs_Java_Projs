package org.cytoscape.rsCy3App.sample22.internal;

import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;

public class MyTask extends AbstractTask {

	public MyTask(){
	}

    @Override
	public void run(final TaskMonitor taskMonitor) {
		// Give the task a title.
		taskMonitor.setTitle("My task");

		taskMonitor.setProgress(0.1);

		try {
			Thread.sleep(4000);
		}
		catch ( InterruptedException e){
		}

		taskMonitor.setProgress(0.5);

		try {
			Thread.sleep(4000);
		}
		catch ( InterruptedException e){
		}
		taskMonitor.setProgress(1.0);
    }
}

